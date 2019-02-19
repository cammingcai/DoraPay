package pay.dora.gz.com.dorapay.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

//import com.tencent.mm.opensdk.constants.ConstantsAPI;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import pay.dora.gz.com.pay.weixin.WeiXinPay;


/**
 * Created by camming on 2018/11/20.
 *
 * 微信支付
 *
 */

public class WXPayEntryActivity extends Activity  {
//public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(WeiXinPay.getInstance(this)!=null){
//            WeiXinPay.getInstance(this).getWXApi().handleIntent(getIntent(), this);
//        }else {
//            finish();
//        }
//
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        if (!WeiXinPay.getInstance(this).getWXApi().handleIntent(intent, this)) {
//            finish();
//        }
////        if(WeiXinPay.getInstance(this) != null) {
////            WeiXinPay.getInstance(this).getWXApi().handleIntent(intent, this);
////        }
//    }
//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }
//
//    @Override
//    public void onResp(BaseResp baseResp) {
//        //4、支付结果回调 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5
////        Log.e("WXPayEntryActivity", "baseResp.getType()=" + baseResp.getType());
////        Log.e("WXPayEntryActivity", "baseResp.errStr=" + baseResp.errStr);
//        if(baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            if(WeiXinPay.getInstance(this) != null) {
//                if(baseResp.errStr != null) {
//                    Log.e("WXPayEntryActivity", "errStr=" + baseResp.errStr);
//                }
//                WeiXinPay.getInstance(this).onResp(baseResp);
//                finish();
//            }
//        }
//    }
}
