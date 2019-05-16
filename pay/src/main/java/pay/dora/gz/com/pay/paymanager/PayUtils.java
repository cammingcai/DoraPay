package pay.dora.gz.com.pay.paymanager;

import android.app.Activity;
import android.widget.Toast;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.entity.WechatPayBean;
import pay.dora.gz.com.pay.pay.AlipayStrategy;
import pay.dora.gz.com.pay.pay.PayStrategy;
import pay.dora.gz.com.pay.pay.WeChatPayStrategy;


/***
 *
 * Create by camming 2019/02/14
 * 支付工具类
 *
 * 获取预付订单信息及调起
 *
 * */
public class PayUtils {
    public static final String TAG = "PayUtils";
    private static PayUtils mPayUtils;
    private Activity mContext;

    private PayUtils(Activity context) {
        mContext = context;
    }

    public static PayUtils getIntance(Activity context) {
        if (mPayUtils == null) {
            synchronized (PayUtils.class) {
                if (mPayUtils == null) {
                    mPayUtils = new PayUtils(context);
                }
            }
        }
        return mPayUtils;
    }



    //调起支付宝支付
    public void startAliPay(final String orderInfo, JPay.JPayListener listener) {
        JPay.getIntance(mContext).toPay(JPay.PayMode.ALIPAY, orderInfo, listener);
    }


    /**
     * 调起支付
     *
     * @param payBean
     */
    public void startWXPay(WechatPayBean payBean, JPay.JPayListener listener) {
        JPay.getIntance(mContext).toWxPay(payBean,listener);

    }

    /**调起银联支付*/
    public void startUPPay(String tn) {

        JPay.getIntance(mContext).toUUPay("01",tn, new JPay.JPayListener() {
            @Override
            public void onPaySuccess() {
                Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPayError(int error_code, String message) {
                Toast.makeText(mContext, "支付失败>" + error_code + " " + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPayCancel() {
                Toast.makeText(mContext, "取消了支付", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUUPay(String dataOrg, String sign, String mode) {
                Toast.makeText(mContext, "支付成功>需要后台查询订单确认>"+dataOrg+" "+mode, Toast.LENGTH_SHORT).show();
            }
        });
    }




//    /**
//     *  策略模式 or  工厂模式
//     *
//     *  支付的方式有很多 微信支付 支付宝支付 qq支付 银联支付等等
//     *
//     * 开始购买VIP
//     * */
//    public void payDoraVip(PayType payType, String data) {
//
//        PayStrategy strategy = null;
//        switch (payType){
//            case ALI_PAY:
//                strategy = new AlipayStrategy();
//                break;
//            case WECHAT_PAY:
//                strategy = new WeChatPayStrategy();
//                break;
//            default:
//                //showTip("支付类型有误！");
//        }
//        strategy.pay(mContext,data);
//    }




    /**
     *  策略模式 or  工厂模式
     *
     *  支付的方式有很多 微信支付 支付宝支付 qq支付 银联支付等等
     *
     * 开始购买VIP
     * */
    public void payDoraVip(String serverType,String payType, String data, JPay.JPayListener listener) {

        PayStrategy strategy = null;
        switch (payType){
            case "ALI":
                strategy = new AlipayStrategy();
                break;
            case "WECHAT":
                strategy = new WeChatPayStrategy();
                break;
            default:
                Toast.makeText(mContext,"支付类型有误！",Toast.LENGTH_SHORT).show();
                // showTip("支付类型有误！");
        }
        if(strategy!=null){
            strategy.setPayListener(listener);
            strategy.pay(mContext,serverType,data);
        }
    }

}
