package pay.dora.gz.com.pay;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import pay.dora.gz.com.pay.alipay.Alipay;
import pay.dora.gz.com.pay.entity.WechatPayBean;
import pay.dora.gz.com.pay.unionpay.UPPay;
import pay.dora.gz.com.pay.weixin.WeiXinPay;


/***
 *
 * Create by camming 2019/02/14
 *
 * */
public class JPay {

    private static final String TAG = "JPay";
    private static JPay mJPay;
    private Activity mContext;

    private JPay(Activity context) {
        mContext = context;
    }

    public static JPay getIntance(Activity context) {
        if (mJPay == null) {
            synchronized (JPay.class) {
                if (mJPay == null) {
                    mJPay = new JPay(context);
                }
            }
        }
        return mJPay;
    }

    public interface JPayListener {
        //支付成功
        void onPaySuccess();
        //支付失败
        void onPayError(int error_code, String message);
        //支付取消
        void onPayCancel();
        //银联支付结果回调
        void onUUPay(String dataOrg, String sign, String mode);
    }

    public enum PayMode {
        WXPAY, ALIPAY, UUPAY
    }

    /**
     * 调起支付
     *
     * 根据类型
     * */
    public void toPay(PayMode payMode, String payParameters, JPayListener listener) {
        if (payMode.name().equalsIgnoreCase(PayMode.WXPAY.name())) {
            toWxPay(payParameters, listener);
        } else if (payMode.name().equalsIgnoreCase(PayMode.ALIPAY.name())) {
            toAliPay(payParameters, listener);
        } else if (payMode.name().equalsIgnoreCase(PayMode.UUPAY.name())) {
            toUUPay(payParameters,listener);
        }
    }


    /**
     * 微信支付
     * */
    public void toWxPay(String payParameters, JPayListener listener) {
        if (payParameters != null) {
            JSONObject param = null;
            try {
                param = new JSONObject(payParameters);
            } catch (JSONException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onPayError(WeiXinPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            if (TextUtils.isEmpty(param.optString("appid")) || TextUtils.isEmpty(param.optString("partnerId"))
                    || TextUtils.isEmpty(param.optString("prepayId")) || TextUtils.isEmpty(param.optString("nonceStr"))
                    || TextUtils.isEmpty(param.optString("timeStamp")) || TextUtils.isEmpty(param.optString("sign"))) {
                if (listener != null) {
                    listener.onPayError(WeiXinPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
           /* toWxPay(param.optString("appid"),
                    param.optString("partnerId"), param.optString("prepayId"),
                    param.optString("nonceStr"), param.optString("timeStamp"),
                    param.optString("sign"), listener);*/

        } else {
            if (listener != null) {
                listener.onPayError(WeiXinPay.PAY_PARAMETERS_ERROE, "参数异常");
            }
        }
    }
    /**
     * 微信支付
     * */
   /* public void toWxPay(String appId, String partnerId, String prepayId,
                        String nonceStr, String timeStamp, String sign, JPayListener listener) {
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(partnerId)
                || TextUtils.isEmpty(prepayId) || TextUtils.isEmpty(nonceStr)
                || TextUtils.isEmpty(timeStamp) || TextUtils.isEmpty(sign)) {
            if (listener != null) {
                listener.onPayError(WeiXinPay.PAY_PARAMETERS_ERROE, "参数异常");
            }
            return;
        }
        WeiXinPay.getInstance(mContext).startWXPay(appId, partnerId, prepayId, nonceStr, timeStamp, sign, listener);
    }*/
    /**
     * 微信支付
     * */
    public void toWxPay(WechatPayBean bean, JPayListener listener) {
       // Log.i(TAG,"bean="+ com.alibaba.fastjson.JSONObject.toJSONString(bean));
        String appid = bean.getAppid();
        String partnerId = bean.getMch_id();
        String prepayId = bean.getPrepay_id();
        String nonceStr = bean.getNonce_str();
        String timeStamp = bean.getTimestamp();
        String sign = bean.getSignB();
        Log.i(TAG,"appid="+appid);
        Log.i(TAG,"partnerId="+partnerId);
        Log.i(TAG,"prepayId="+prepayId);
        Log.i(TAG,"nonceStr="+nonceStr);
        Log.i(TAG,"timeStamp="+timeStamp);
        Log.i(TAG,"sign="+sign);
        if (TextUtils.isEmpty(appid) || TextUtils.isEmpty(partnerId)
                || TextUtils.isEmpty(prepayId) || TextUtils.isEmpty(nonceStr)
                || TextUtils.isEmpty(sign) || TextUtils.isEmpty(timeStamp)) {
            if (listener != null) {
                listener.onPayError(WeiXinPay.PAY_PARAMETERS_ERROE, "参数异常");
            }
            return;
        }
        WeiXinPay.getInstance(mContext).startWXPay(bean, listener);
    }



    public void toAliPay(String payParameters, JPayListener listener) {
        if (payParameters != null) {
            if (listener != null) {
                Alipay.getInstance(mContext).startAliPay(payParameters, listener);
            }
        } else {
            if (listener != null) {
                listener.onPayError(Alipay.PAY_PARAMETERS_ERROE, "参数异常");
            }
        }
    }

    public void toUUPay(String payParameters, JPayListener listener) {
        if (payParameters != null) {
            JSONObject param = null;
            try {
                param = new JSONObject(payParameters);
            } catch (JSONException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            if (TextUtils.isEmpty(param.optString("mode")) || TextUtils.isEmpty(param.optString("tn"))){
                if (listener != null) {
                    listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            toUUPay(param.optString("mode"),
                    param.optString("tn"), listener);
        } else {
            if (listener != null) {
                listener.onPayError(WeiXinPay.PAY_PARAMETERS_ERROE, "参数异常");
            }
        }
    }

    public void toUUPay(String mode, String tn, JPayListener listener) {
        if (listener == null) {
            listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
            return;
        }
        if (TextUtils.isEmpty(mode)) {
            mode = "00";
        }
        if (TextUtils.isEmpty(tn)) {
            listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
            return;
        }
        UPPay.getInstance(mContext).startUPPay(mode, tn, listener);
    }
}
