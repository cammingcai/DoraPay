package pay.dora.gz.com.pay;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pay.dora.gz.com.pay.paymanager.PayUtils;


/**
 * Created by camming on 2019\2\18 0018.
 * code is data  data is code
 */

public class PayUnityUtils {


    /**
     * unity项目启动时的的上下文
     */
    private Activity unityActivity;
    /**
     * 获取unity项目的上下文
     * @return
     */
    Activity getActivity(){
        if(null == unityActivity) {
            try {
                Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");
                Activity activity = (Activity) classtype.getDeclaredField("currentActivity").get(classtype);
                unityActivity = activity;
            } catch (ClassNotFoundException e) {

            } catch (IllegalAccessException e) {

            } catch (NoSuchFieldException e) {

            }
        }
        return unityActivity;
    }

    /**
     * 调用Unity的方法
     * @param gameObjectName    调用的GameObject的名称
     * @param functionName      方法名
     * @param args              参数
     * @return                  调用是否成功
     */
    boolean callUnity(String gameObjectName, String functionName, String args){
        try {
            Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");
            Method method =classtype.getMethod("UnitySendMessage", String.class,String.class,String.class);
            method.invoke(classtype,gameObjectName,functionName,args);
            return true;
        } catch (ClassNotFoundException e) {

        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        return false;
    }

//    /**
//     * 这个是提供给unity的
//     * @param payType 支付类型 （ALI、WECHAT）
//     * */
//    public void payDoraVip(String payType,String httpResult){
//        PayUtils.getIntance(getActivity()).payDoraVip(payType,httpResult,payListener);
//    }

    /**
     * 这个是提供给unity的
     * @param serverType 后台接口类型分别是：PHP、JAVA
     * @param payType    支付类型分别是：ALI、WECHAT
     * @param httpResult 后台返回的json结果
     * */
    public void payDoraVip(String serverType,String payType,String httpResult){
        PayUtils.getIntance(getActivity()).payDoraVip(serverType,payType,httpResult,payListener);
    }
    /**
     * 这个是我自己测试用的
     * */
    public void payDoraVip(Activity context,String serverType,String payType,String httpResult){
        PayUtils.getIntance(context).payDoraVip(serverType,payType,httpResult,payListener);
    }

    JPay.JPayListener payListener = new JPay.JPayListener() {
        @Override
        public void onPaySuccess() {
            Log.i("JPayListener","onPaySuccess=");
           //  Toast.makeText(mContext,"支付onPaySuccess！",Toast.LENGTH_SHORT).show();
            callUnity("UnityAndroidCommunicationObj","payStatus", "success");
        }

        @Override
        public void onPayError(int error_code, String message) {
            Log.i("JPayListener","onPayError=");
            //Toast.makeText(mContext,"支付onPayError！",Toast.LENGTH_SHORT).show();
            callUnity("UnityAndroidCommunicationObj","payStatus", "error");
        }

        @Override
        public void onPayCancel() {
            Log.i("JPayListener","onPayCancel=");
            //Toast.makeText(mContext,"支付onPayCancel！",Toast.LENGTH_SHORT).show();
            callUnity("UnityAndroidCommunicationObj","payStatus", "cancel");
        }

        @Override
        public void onUUPay(String dataOrg, String sign, String mode) {

        }
    };

}
