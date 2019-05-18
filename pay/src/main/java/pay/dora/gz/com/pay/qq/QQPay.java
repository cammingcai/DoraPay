package pay.dora.gz.com.pay.qq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.api.OpenApiFactory;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.mobileqq.openpay.data.base.BaseResponse;
import com.tencent.mobileqq.openpay.data.pay.PayApi;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.entity.QQPayBean;

/**
 * Created by camming on 2019\2\19 0019.
 * code is data  data is code
 *
 * qq支付
 */

public class QQPay {
    private static final String TAG = "QQPay";
    @SuppressLint("StaticFieldLeak")
    private static QQPay mQQPay;
    private Context mContext;

    private IOpenApi openApi;

    int paySerial = 1;
    private String callbackScheme = "qwallet+商户id";
    public static final String QQ_APP_ID = "+商户id";
    // 签名步骤建议不要在app上执行，要放在服务器上执行
    public static final String APP_KEY = "商户key";

    private JPay.JPayListener mJPayListener;

    public static QQPay getInstance(Context context){
        if (mQQPay == null) {
            synchronized(QQPay.class){
                if (mQQPay == null) {
                    mQQPay = new QQPay(context);
                }
            }
        }
        return mQQPay;
    }
    private QQPay(Context context) {
        mContext = context;
        if(openApi==null)
            openApi = OpenApiFactory.getInstance(mContext, QQ_APP_ID);
    }

    /**手机QQ是否已经安装*/
    public boolean isMqqInstalled() {
        if(openApi==null)
            return false;
        else
            return openApi.isMobileQQInstalled();
    }
    /**是否支持手机qq支付*/
    public boolean isMqqSupportPay() {
        if(openApi==null)
            return false;
        else
            return openApi.isMobileQQSupportApi(OpenConstants.API_NAME_PAY);
    }
//    public void startQQPay(String payResult, JPay.JPayListener listener){

//        startQQPay(resultBean,listener);
//    }
    /**
     * 开始QQ支付
     *
     * */
    public void startQQPay(String pay, JPay.JPayListener listener){
        this.mJPayListener = listener;

        QQPayBean payBean = JSONObject.parseObject(pay,new TypeReference<QQPayBean>(){});
        Log.i("qqpay","payBean="+payBean);
//        ToastUtils.show(mContext,payBean);
        if(!isMqqInstalled()){
            Toast.makeText(mContext,"QQ未安装！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isMqqSupportPay()){
            Toast.makeText(mContext,"该QQ版本不支持QQ支付！",Toast.LENGTH_SHORT).show();
            return;
        }
        PayApi api = new PayApi();
        api.appId = payBean.getAppid();
        api.serialNumber = "" + paySerial++;//支付序号
        api.callbackScheme = callbackScheme;
        api.tokenId = payBean.getPrepay_id();// QQ钱包支付生成的token_id
        api.pubAcc = "";
        api.pubAccHint = "";
        api.nonce = payBean.getNonce_str();
        api.timeStamp = System.currentTimeMillis() / 1000;
        api.bargainorId = payBean.getMch_id();

        //直接使用后台返回的签名数据  如果使用服务端的签名，把
        //下面的signApi()方法注释掉
//        api.sig = "";
//        api.sigType = "HMAC-SHA1";

        try {
            //为了测试，这里直接签名，此步骤放在服务端
            signApi(api);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext,"签名异常！",Toast.LENGTH_SHORT).show();
            return;
        }

        if (api.checkParams()) {
            openApi.execApi(api);
        }else{
            Toast.makeText(mContext,"支付参数有误！",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 签名步骤建议不要在app上执行，要放在服务器上执行.
     */
    public void signApi(PayApi api) throws Exception {
        // 按key排序 ASCII
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("appId=").append(api.appId);
        stringBuilder.append("&bargainorId=").append(api.bargainorId);
        stringBuilder.append("&nonce=").append(api.nonce);
        stringBuilder.append("&pubAcc=").append("");
        stringBuilder.append("&tokenId=").append(api.tokenId);

        byte[] byteKey = (APP_KEY+"&").getBytes("UTF-8");
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(byteKey, "HmacSHA1");
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance("HmacSHA1");
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] byteSrc = stringBuilder.toString().getBytes("UTF-8");
        // 完成 Mac 操作
        byte[] dst = mac.doFinal(byteSrc);
        // Base64
        api.sig = Base64.encodeToString(dst, Base64.NO_WRAP);
        api.sigType = "HMAC-SHA1";
    }

    /**
     *
     * QQ支付结果状态
     * */
    public void onQQResponse(BaseResponse response){

        Log.i("QQPay","response.retCode="+response.retCode+",response.retMsg="+response.retMsg);

        if(response!=null){
            switch (response.retCode){
                case 0://支付成功
                    if(mJPayListener!=null)
                        mJPayListener.onPaySuccess();
                    break;
                case -1://支付取消 用户主动取消
                    if(mJPayListener!=null)
                        mJPayListener.onPayCancel();
                    break;
                case -2://登录超时
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"登录超时");
                    break;
                case -3://重复提交订单
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"重复提交订单");
                    break;
                case -4:
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"快速注册用户手机号不一致");
                    break;
                case -5:
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"账号被冻结");
                    break;
                case -6:
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"支付密码输入超限");
                    break;
                case -100:
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"网络异常错误");
                    break;
                case -101:
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"支付参数错误");
                    break;
                default:
                    if(mJPayListener!=null)
                        mJPayListener.onPayError(response.retCode,"未知错误");

            }
        }


    }
}
