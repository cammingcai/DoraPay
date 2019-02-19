package pay.dora.gz.com.pay.asyncTask;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONObject;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.entity.WechatBean;
import pay.dora.gz.com.pay.entity.WechatPayBean;
import pay.dora.gz.com.pay.paymanager.PayUtils;

/**
 * create by camming 2019/02/14
 *
 * 微信支付
 *
 *
 * */
public class WXPayTask extends PayTask {



	//private boolean isPay = false;

	public WXPayTask(Activity context) {
		this.mContext = context;
	}
	public WXPayTask(Activity context, JPay.JPayListener listener) {
		this.mContext = context;
		this.mJPayListener =listener;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}


//	@Override
//	protected String doInBackground(Object... params) {
//		//Log.i("WXPayTask","getAppId="+((Order)params[0]).getAppId());
//
//		return null;
//	}
	
	@Override
	protected void onPostExecute(String result) {
	//	Log.i("WXPayTask","isPay="+isPay);
//		if(!isPay)
//			return;
		//WechatBean resultBean = JSONObject.parseObject(result,new TypeReference<WechatBean>(){});
		Log.i("WXPayTask","result="+result);
		try {
			JSONObject jsonObject=new JSONObject(result);
			int code  = jsonObject.getInt("code");
			if(code==0){
				JSONObject data = jsonObject.getJSONObject("data");
				WechatPayBean payBean = new WechatPayBean();
				payBean.setAppid(data.getString("appid"));
				payBean.setMch_id(data.getString("mch_id"));
				payBean.setNonce_str(data.getString("nonce_str"));
				payBean.setPrepay_id(data.getString("prepay_id"));
				payBean.setSign(data.getString("sign"));
				payBean.setSignB(data.getString("signB"));
				payBean.setTimestamp(data.getString("timestamp"));
				if (payBean!=null) {
					//System.out.println("TestPayPrepay result>"+result);
					Log.i("WXPayTask","mJPayListener="+mJPayListener);
					PayUtils.getIntance(mContext).startWXPay(payBean,mJPayListener);
//				//微信支付  签名  应用id 订单号 时间 商户号
				}else {
					//System.out.println("get  prepayid exception, is null");
					Toast.makeText(mContext,"没有该订单",Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(mContext,"支付出错！",Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			Log.e("PAY_GET", "异常："+e.getMessage());
			Toast.makeText(mContext,"异常："+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
		super.onPostExecute(result);
	}

}
