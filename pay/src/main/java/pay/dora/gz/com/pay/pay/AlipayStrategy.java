package pay.dora.gz.com.pay.pay;

import android.app.Activity;
import android.util.Log;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.asyncTask.AliPayTask;


/**
 * Created by camming on 2019\2\18 0018.
 * code is data  data is code
 */

public class AlipayStrategy implements PayStrategy {

    JPay.JPayListener jPayListener;
//    AlipayStrategy(JPay.JPayListener listener){
//        this.jPayListener = listener;
//    }
    @Override
    public void pay(Activity context,  String data) {
//        Order order  = new Order();
//        order.setSubject(data.getTitle());
//        order.setTotal_amount(data.getPrice()+"");
        Log.i("AlipayStrategy","jPayListener="+jPayListener);
        new AliPayTask(context,jPayListener).execute(data);
    }

    @Override
    public void setPayListener(JPay.JPayListener payListener) {
        jPayListener = payListener;
    }
}
