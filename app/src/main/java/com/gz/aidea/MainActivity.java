package com.gz.aidea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.PayUnityUtils;

public class MainActivity extends AppCompatActivity {


    //预付订单
    private String orderAli;
    private String orderWechat;
    private String orderQQ;

    private Button wechatBtn,aliBtn,qqBtn;
    PayUnityUtils pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pay = new PayUnityUtils();
        wechatBtn = findViewById(R.id.btn_wechat);
        aliBtn = findViewById(R.id.btn_ali);
        qqBtn = findViewById(R.id.btn_qq);
        wechatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderWechat = "{\n" +
                        "  \"appid\": \"wxca304224e344cf01\",\n" +
                        "  \"partnerid\": \"1526564001\",\n" +
                        "  \"prepayid\": \"wx161841565666311a08953e792375942151\",\n" +
                        "  \"timestamp\": \"1558003316\",\n" +
                        "  \"noncestr\": \"c8J2CnD7qDx15dus\",\n" +
                        "  \"package\": \"Sign=WXPay\",\n" +
                        "  \"sign\": \"34CBBA4190511EB8E919F80A0F59775F\"\n" +
                        "}";
                if(TextUtils.isEmpty(orderWechat)){
                    Toast.makeText(MainActivity.this,"预付订单为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                //微信支付
//        * 注意
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
                pay.payDoraVip(MainActivity.this,"PHP",JPay.PayMode.WXPAY,orderWechat);
            }
        });
        aliBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAli ="app_id=2019022263273481&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&notify_url=https%3A%2F%2Fdapi.tfwangs.com%2Fapi%2Falipay%2Fverify&timestamp=2019-05-16+18%3A26%3A25&biz_content=%7B%22out_trade_no%22%3A%2220190516182625272185%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%227%5Cu5929%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&method=alipay.trade.app.pay&sign=Ip3vSdgDI1F39VSdmvuNwmu31Zmg90OAgMh29Bay461vvh4lUZvAsfxT7YOv6CXNqR%2Bh70YnNk03Vtkke3Hr5w5AdGYBujVxKUT2VyKUswBHzdF9TCHfF5zIA5NHZMvOAt%2FT1V0tkfeowoMrlqj0dsWGeq%2B2DYBuiky4dqCgC1Fua3wP%2FwaeKoeQNjk7O%2FBLN5H12F8B4srpTdFDhOSOpfU%2FS5YkN3fxFltU36vecnFiROPRgmRlbKqNjhfdKnp4GGZGkhLkewCoPZ%2BvPYUG%2Bzcq8iF9EvCw2guPxPrcWWnCQssnNDFtZlkOoMSUSJ569Xgs8oH82KPUjh8jaL3BxQ%3D%3D";
                if(TextUtils.isEmpty(orderAli)){
                    Toast.makeText(MainActivity.this,"预付订单为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                //支付宝OK
                pay.payDoraVip(MainActivity.this,"PHP", JPay.PayMode.ALIPAY,orderAli);
            }
        });
        qqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderQQ = "{\"appid\":\"101538445\",\"mch_id\":\"1526262271\",\"nonce_str\":\"58d76a4cc35d4b3aa5229b407a55b4af\",\"prepay_id\":\"6M620875063a96c8c1a02c3b1920e6c4\",\"result_code\":\"SUCCESS\",\"retcode\":\"0\",\"retmsg\":\"ok\",\"return_code\":\"SUCCESS\",\"return_msg\":\"SUCCESS\",\"sign\":\"638E98F20FD0D389E05BDAA1C4B13F7F\",\"trade_type\":\"APP\"}";
                //{"appid":"101538445","mch_id":"1526262271","nonce_str":"58d76a4cc35d4b3aa5229b407a55b4af","prepay_id":"6M620875063a96c8c1a02c3b1920e6c4","result_code":"SUCCESS","retcode":"0","retmsg":"ok","return_code":"SUCCESS","return_msg":"SUCCESS","sign":"638E98F20FD0D389E05BDAA1C4B13F7F","trade_type":"APP"}
                if(TextUtils.isEmpty(orderQQ)){
                    Toast.makeText(MainActivity.this,"预付订单为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                pay.payDoraVip(MainActivity.this,"PHP", JPay.PayMode.QQPAY,orderQQ);
            }
        });






    }
}
