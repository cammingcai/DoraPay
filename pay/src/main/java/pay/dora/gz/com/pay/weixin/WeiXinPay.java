/**
 * Copyright (c) 2015-2017, javen205  (javendev@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package pay.dora.gz.com.pay.weixin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.entity.WechatPayBean;


/**
 * 微信支付
 * Created by camming on 2016/11/20.
 *
 *
 * 注意
 * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
 * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
 * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
 * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
 * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
 * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
 *
 *
 */

public class WeiXinPay {

    private static final String TAG = "WeiXinPay";
    private static WeiXinPay mWeiXinPay;
    private Context mContext;
    private IWXAPI mIWXAPI;
    private JPay.JPayListener mJPayListener;

    //未安装微信或微信版本过低
    public static final int WEIXIN_VERSION_LOW = 0x001;
    //支付参数异常
    public static final int PAY_PARAMETERS_ERROE = 0x002;
    //支付失败
    public static final int PAY_ERROR= 0x003;

    private WeiXinPay(Context context) {
        mContext = context;
        mIWXAPI= WXAPIFactory.createWXAPI(mContext, null);
    }

    public static WeiXinPay getInstance(Context context){
        if (mWeiXinPay == null) {
            synchronized(WeiXinPay.class){
                if (mWeiXinPay == null) {
                    mWeiXinPay = new WeiXinPay(context);
                }
            }
        }
        return mWeiXinPay;
    }

    /**
     * 初始化微信支付接口
     * @param appId
     */
    public void registerApp(String appId){

        boolean bool = mIWXAPI.registerApp(appId);
    }

    /**
     * 获取微信接口
     * @return
     */
    public IWXAPI getWXApi() {
        return mIWXAPI;
    }

    /**
     * 调起支付
     */
    public void startWXPay(WechatPayBean payBean, JPay.JPayListener listener){
        mJPayListener = listener;
        //将APP注册到微信
        registerApp(payBean.getAppid());
        if (!checkWx()) {
            if(listener != null) {
                listener.onPayError(WEIXIN_VERSION_LOW,"未安装微信或者微信版本过低");
            }
            return;
        }


        PayReq req = new PayReq();
        req.appId			= payBean.getAppid();
        req.partnerId		= payBean.getMch_id();
        req.prepayId		= payBean.getPrepay_id();
        req.nonceStr		= payBean.getNonce_str();
        req.timeStamp		= payBean.getTimestamp();
        req.packageValue	= "Sign=WXPay";
        req.sign			= payBean.getSignB();//服务端的签名 正式使用
        //req.sign			= genAppSign(payBean).toUpperCase();//客户端的签名  测试

        mIWXAPI.sendReq(req);
    }


    /***
     * 本地签名 签名操作需在服务端完成
     * 这里是测试签名 不要在客户端签名
     *  //7个参数 缺一不可
     *
     * */
//    private String genAppSign(WechatPayBean payBean) {
//
//        List<NameValuePair> signParams = new LinkedList<>();
//        signParams.add(new NameValuePair("appid", payBean.getAppid()));
//        signParams.add(new NameValuePair("noncestr", payBean.getNonce_str()));
//        signParams.add(new NameValuePair("package", "Sign=WXPay"));
//        signParams.add(new NameValuePair("partnerid",payBean.getMch_id()));
//        signParams.add(new NameValuePair("prepayid", payBean.getPrepay_id()));
//        signParams.add(new NameValuePair("timestamp", payBean.getTimestamp()));
//
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < signParams.size(); i++) {
//            sb.append(signParams.get(i).getName());
//            sb.append('=');
//            sb.append(signParams.get(i).getValue());
//            sb.append('&');
//        }
//        sb.append("key=");
//        sb.append("CG2K4MBWc9emJpqyydowiRvmjesJ80xx");
//        String appSign = MD5Utils.getMessageDigest(sb.toString().getBytes());
//        return appSign;
//    }
    /**
     * 响应支付回调
     */
    public void onResp(BaseResp baseResp) {

        int error_code = baseResp.errCode;

        Log.i(TAG,"error_code="+baseResp.errCode+",message="+baseResp.errStr);
        Log.i(TAG,"onResp= mJPayListener"+mJPayListener);
        if(mJPayListener == null) {
            return;
        }
        if(error_code == 0) {
            //支付成功
            mJPayListener.onPaySuccess();
        } else if(error_code == -1) {
            //支付异常
            mJPayListener.onPayError(PAY_ERROR,"errStr="+baseResp.errStr);
        } else if(error_code == -2) {
            //支付取消
            mJPayListener.onPayCancel();
        }

     //   mJPayListener = null;
    }



    //检测微信客户端是否支持微信支付
    private boolean checkWx() {
        return isWeixinAvilible() && mIWXAPI.isWXAppInstalled() && mIWXAPI.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
    /**
     * 判断微信是否安装
     * @return
     */
    private  boolean isWeixinAvilible() {
        return appIsAvilible("com.tencent.mm");
    }

    /**
     * 判断app是否安装
     * @param packageName
     * @return
     */
    private  boolean appIsAvilible(String packageName) {
        final PackageManager packageManager = mContext.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
