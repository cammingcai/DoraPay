package pay.dora.gz.com.pay.asyncTask;

import android.app.Activity;
import android.os.AsyncTask;

import pay.dora.gz.com.pay.JPay;

/**
 * Created by camming on 2019\2\16 0016.
 * code is data  data is code
 *
 * 异步调起支付 基类
 */

public abstract class PayTask extends AsyncTask<Object, Integer, String> {
    protected Activity mContext;

    protected boolean isPay = false;
    protected JPay.JPayListener mJPayListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {

     //   payOrder((String) params[0]);
        return (String) params[0];
    }

    /**根据订单信息进行付款*/
   // public abstract void payOrder(String order);

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
