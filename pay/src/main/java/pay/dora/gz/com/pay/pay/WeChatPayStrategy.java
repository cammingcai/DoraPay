package pay.dora.gz.com.pay.pay;

import android.app.Activity;
import android.util.Log;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.asyncTask.WXPayTask;


/**
 * Created by camming on 2019\2\18 0018.
 * code is data  data is code
 */

public class WeChatPayStrategy implements PayStrategy {

    JPay.JPayListener jPayListener;
//    WeChatPayStrategy(JPay.JPayListener listener){
//        this.jPayListener = listener;
//    }
//
    @Override
    public void pay(Activity context, String data) {
//        Order order  = new Order();
//        order.setBody(data.getTitle());
//        order.setTotal_fee(data.getPrice());
//        order.setMonths(data.getMonths());
//        order.setSubjectTypeId(data.getSubjectTypeId());
//        order.setActualPayPrice(data.getPrice());
        Log.i("WeChatPayStrategy","jPayListener="+jPayListener);
        new WXPayTask(context,jPayListener).execute(data);
    }
    @Override
    public void setPayListener(JPay.JPayListener payListener) {
        jPayListener = payListener;
    }
}
