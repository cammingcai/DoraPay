package pay.dora.gz.com.pay.pay;

import android.app.Activity;


import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.asyncTask.QQPayTask;

/**
 * Created by camming on 2019\2\19 0019.
 * code is data  data is code
 */

public class QQPayStrategy implements PayStrategy {
    JPay.JPayListener jPayListener;
    @Override
    public void pay(Activity context, String serverType, String data) {
        new QQPayTask(context,serverType,jPayListener).execute(data);
    }

    @Override
    public void setPayListener(JPay.JPayListener payListener) {
        jPayListener = payListener;
    }


}
