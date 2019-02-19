package pay.dora.gz.com.pay.asyncTask;

import android.app.Activity;
import android.util.Log;

import org.json.JSONObject;

/**
 * Create by Camming 2019/02/14
 * 银联支付
 * */
public class UPPayTask extends PayTask {
	public UPPayTask(Activity context) {
		this.mContext = context;
	}
	
//	@Override
//	protected String doInBackground(Object... params) {
//		try{
//
//		}catch (Exception e){
//		}
//		return null;
//	}

	@Override
	protected void onPostExecute(String result) {
		if(!isPay)
			return;
		try {
			if (result!=null) {
				//System.out.println("UPPay result>"+result);
				JSONObject data = new JSONObject(result);
				String message = data.getString("message");
				int code = data.getInt("code");

				if(code == 0){
					String orderInfo = data.getString("data");
                   // System.out.println("获取到的tn>>>"+orderInfo);
//					ToastUtils.show(mContext,"正在支付");
//					PayUtils.getIntance(mContext).startUPPay(orderInfo);
				}else{

		       // 	Log.d("PAY_GET", "返回错误"+message);
//		        	Toast.makeText(mContext, "返回错误:"+message, Toast.LENGTH_SHORT).show();
				}
			}else {
				System.out.println("get  UPPay exception, is null");
			}
		} catch (Exception e) {
			Log.e("PAY_GET", "异常："+e.getMessage());
//			ToastUtils.show(mContext,"异常："+e.getMessage());
        }
		super.onPostExecute(result);
	}

}
