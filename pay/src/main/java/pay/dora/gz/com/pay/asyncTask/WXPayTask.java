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
	public WXPayTask(Activity context,String type, JPay.JPayListener listener) {
		this.mContext = context;
		this.mJPayListener =listener;
		this.serverType = type;
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

			if(serverType.equals(API_TYPE_PHP)){
				startWXPay(jsonObject.getString("appid"),jsonObject.getString("partnerid"),
						jsonObject.getString("noncestr"),jsonObject.getString("prepayid"),
						jsonObject.getString("sign"),jsonObject.getString("sign"),
						jsonObject.getString("timestamp"));
			}else{
				int code  = jsonObject.getInt("code");
				if(code==0){
					JSONObject data = jsonObject.getJSONObject("data");

					startWXPay(data.getString("appid"),data.getString("mch_id"),
							data.getString("nonce_str"),data.getString("prepay_id"),
							data.getString("sign"),data.getString("signB"),
							data.getString("timestamp"));

				}else{
					Toast.makeText(mContext,"支付出错！",Toast.LENGTH_SHORT).show();
				}
			}


		} catch (Exception e) {
			Log.e("PAY_GET", "异常："+e.getMessage());
			Toast.makeText(mContext,"异常："+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
		super.onPostExecute(result);
	}

	private void startWXPay(String appid, String mch_id, String nonce_str, String prepay_id, String sign, String signB, String timestamp) {
		WechatPayBean payBean = new WechatPayBean();
		payBean.setAppid(appid);
		payBean.setMch_id(mch_id);
		payBean.setNonce_str(nonce_str);
		payBean.setPrepay_id(prepay_id);
		payBean.setSign(sign);
		payBean.setSignB(signB);
		payBean.setTimestamp(timestamp);
		if (payBean!=null) {
			//System.out.println("TestPayPrepay result>"+result);
			Log.i("WXPayTask","mJPayListener="+mJPayListener);
			PayUtils.getIntance(mContext).startWXPay(payBean,mJPayListener);
//				//微信支付  签名  应用id 订单号 时间 商户号
		}else {
			//System.out.println("get  prepayid exception, is null");
			Toast.makeText(mContext,"没有该订单",Toast.LENGTH_SHORT).show();
		}
	}

}
