package pay.dora.gz.com.dorapay;

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
//        pay.payDoraVip(this,"JAVA","ALI","{\n" +
//                "  \"code\": 0,\n" +
//                "  \"data\": \"alipay_sdk=alipay-sdk-java-3.4.49.ALL&app_id=2018121262533254&biz_content=%7B%22out_trade_no%22%3A%22be781e661550541449188%22%2C%22subject%22%3A%221111111%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fxxx.baidu.com&sign=oomgnA4NugrCLbEGxL5v0WxM89FkFg16pK1uBJrmdOhCSpNOruVW03r5xWGpbulQ%2F5VkR3Oh8Er%2B4CCbyegxl1qVKXwUykJHWGl6TZb3mhAw4hoS35SDXlkGBHnr8sQvkVeBlNpyfgmMjcq6FIVI1%2B6r2%2B2YnMjYBYL8ORW6e3bdLcFt9ZmnR4K%2B3pJAc0J9zwj3Hz86F%2FCz%2F%2Bh7BZVOmTIF%2BI%2FaW%2BAId6hwBiS%2B%2FmJ7zha1m4r1AP2yw96F1NquXIuwsqk63OqOU6apMuFqNU3Fvp9XpU3HU3fODkka9ZWXjm3ZklGvYx0c83yuV670kdgkr6ZUXtgtC9Rh7bH7VA%3D%3D&sign_type=RSA2&timestamp=2019-02-19+09%3A57%3A29&version=1.0\",\n" +
//                "  \"success\": true,\n" +
//                "  \"message\": \"请求成功\"\n" +
//                "}");
//        pay.payDoraVip(this,"PHP","ALI","app_id=2019022263273481&format=JSON&charset=utf-8&sign_type=RSA2&version=1.0&notify_url=https%3A%2F%2Fdapi.tfwangs.com%2Fapi%2Falipay%2Fverify&timestamp=2019-04-02+11%3A57%3A40&biz_content=%7B%22out_trade_no%22%3A%2220190402115740608266%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%227%5Cu5929%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&method=alipay.trade.app.pay&sign=JluBANaRVUF9gY4NyLP84eJ4WBggJRXskkDFFBspxxrXpmH5W7ikYRyKbmUCrvxurjz3qSoMQpjXgO6mAJ%2BfSVbJhNlW%2BSKF%2FkjG3qDRT1ISTfbDaPSxappBdiB52nlLFEn0SH4rbmvwL1rwUMh%2Bp%2BuOUwDWPBaqwEqnlNOCHjJ8SVMKLnfDHuu0e9jf5ebDZemP36VY6Yxrh67EUgHv2RCkr0ait%2B8wXaKwabcgM71JKNafdpoY%2B2hq3thnt5L%2Bphwr8dtsBTTWQ2r%2FJxoR9RZtYrdcEBm0HpnAHwnYhi37iKBhe%2FDkpK5DJ9DGsJ9eNRBeUh%2Bt66EDNJ17gYNpoA%3D%3D");

    }
}
