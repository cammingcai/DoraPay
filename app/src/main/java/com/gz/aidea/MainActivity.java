package com.gz.aidea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pay.dora.gz.com.pay.PayUnityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PayUnityUtils pay = new PayUnityUtils();
//            pay.payDoraVip(this,"PHP","{\n" +
//                "  \"code\": 0,\n" +
//                "  \"data\": {\n" +
//                "    \"return_code\": \"SUCCESS\",\n" +
//                "    \"return_msg\": \"OK\",\n" +
//                "    \"appid\": \"wxdd38f78279f0d3d1\",\n" +
//                "    \"mch_id\": \"1510699871\",\n" +
//                "    \"device_info\": null,\n" +
//                "    \"nonce_str\": \"qKvpX5LLZcvMtROW\",\n" +
//                "    \"sign\": \"E065A3A4BF9264DCFEC030DAEE0CACA7\",\n" +
//                "    \"result_code\": \"SUCCESS\",\n" +
//                "    \"err_code\": null,\n" +
//                "    \"err_code_des\": null,\n" +
//                "    \"trade_type\": \"APP\",\n" +
//                "    \"prepay_id\": \"wx190950118732882fc63cd8a30614442233\",\n" +
//                "    \"timestamp\": \"1550541012\",\n" +
//                "    \"signB\": \"BC6EC31B659159D918EFE55E30010844\"\n" +
//                "  },\n" +
//                "  \"success\": true,\n" +
//                "  \"message\": \"ok\"\n" +
//                "}");

        //支付宝OK
//        pay.payDoraVip(this,"PHP","ALI","app_id=2019022263273481&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&notify_url=https%3A%2F%2Fdapi.tfwangs.com%2Fapi%2Falipay%2Fverify&timestamp=2019-05-16+15%3A32%3A06&biz_content=%7B%22out_trade_no%22%3A%2220190516153206213935%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%227%5Cu5929%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&method=alipay.trade.app.pay&sign=f97L4UsMKE1YSIDi80MGdPDuQ7inBMt05REtowLL8rySdVKRYzJNc32oO143kozOvAdu0LI6VoGIDa%2FfYd1GhKgmd33tZXXT79CbxwSYYvxk9%2FKJGxWc2G8mGN0cNTdtCrdMJJryVPa9IvkwkpdIcfz8FyzcLK79s8t%2FZEVlCZxRXFUJKFUAAgkyO82w8ODg4Pn6NyxE5ERZ2WKKVmupJhyEI9gfRsL6CpJTE5Gsk2ltUt%2B%2BgziRAZIBhsPCHk%2FRT360FzKh8WyDfA8cGLXsG5BH8R0rHBlN4fdQGW59eYvPV7ArplGN7jlO0nIiF3xZfoxlq2QQcOMzkK8DUw%2BTQg%3D%3D");

      //微信支付
//        * 注意
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
//                * 微信的APPID 和签名信息及报名和微信开放平台填写的要一致
//        pay.payDoraVip(this,"PHP","WECHAT",
//                "{\n" +
//                        "  \"appid\": \"wxca304224e344cf01\",\n" +
//                        "  \"partnerid\": \"1526564001\",\n" +
//                        "  \"prepayid\": \"wx161606220306711aa5947e3d3745941868\",\n" +
//                        "  \"timestamp\": \"1557993983\",\n" +
//                        "  \"noncestr\": \"g9M7Gg90egsHEc2u\",\n" +
//                        "  \"package\": \"Sign=WXPay\",\n" +
//                        "  \"sign\": \"3B6582FA181B1319051454BC044815C6\"\n" +
//                        "}");

        //{"appid":"101538445","mch_id":"1526262271","nonce_str":"58d76a4cc35d4b3aa5229b407a55b4af","prepay_id":"6M620875063a96c8c1a02c3b1920e6c4","result_code":"SUCCESS","retcode":"0","retmsg":"ok","return_code":"SUCCESS","return_msg":"SUCCESS","sign":"638E98F20FD0D389E05BDAA1C4B13F7F","trade_type":"APP"}


        pay.payDoraVip(this,"PHP","QQ",
                "{\"appid\":\"101538445\",\"mch_id\":\"1526262271\",\"nonce_str\":\"58d76a4cc35d4b3aa5229b407a55b4af\",\"prepay_id\":\"6M620875063a96c8c1a02c3b1920e6c4\",\"result_code\":\"SUCCESS\",\"retcode\":\"0\",\"retmsg\":\"ok\",\"return_code\":\"SUCCESS\",\"return_msg\":\"SUCCESS\",\"sign\":\"638E98F20FD0D389E05BDAA1C4B13F7F\",\"trade_type\":\"APP\"}");
    }
}
