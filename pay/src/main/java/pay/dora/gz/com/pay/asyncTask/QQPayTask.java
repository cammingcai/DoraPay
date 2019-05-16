package pay.dora.gz.com.pay.asyncTask;

import android.app.Activity;
import android.util.Log;

import pay.dora.gz.com.pay.JPay;


/**
 * Created by camming on 2019\2\19 0019.
 * code is data  data is code
 *
 * qq支付
 */

public class QQPayTask extends PayTask  {

    public QQPayTask(Activity context,String type, JPay.JPayListener listener) {
        this.mContext = context;
        this.mJPayListener =listener;
        this.serverType = type;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String qqResult) {
        Log.e("QQPayTask", "qqResult="+qqResult);

        JPay.getIntance(mContext).startPay(JPay.PayMode.QQPAY,qqResult);
    }

}
