package pay.dora.gz.com.pay.pay;

import android.app.Activity;

import pay.dora.gz.com.pay.JPay;


/**
 * Created by camming on 2019\2\18 0018.
 * code is data  data is code
 *
 * 定义一个支付的策略接口
 */

public interface PayStrategy {
    void pay(Activity context,String serverType, String data);
    void setPayListener(JPay.JPayListener payListener);


}
