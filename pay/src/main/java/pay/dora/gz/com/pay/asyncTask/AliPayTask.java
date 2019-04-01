package pay.dora.gz.com.pay.asyncTask;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.TypeReference;

import org.json.JSONObject;

import pay.dora.gz.com.pay.JPay;
import pay.dora.gz.com.pay.entity.AlipayBean;
import pay.dora.gz.com.pay.paymanager.PayUtils;


/**
 *
 * create by camming 2019/02/14
 * 情人节
 * 支付宝支付
 * */
public class AliPayTask extends PayTask {

	public AliPayTask(Activity context) {
		this.mContext = context;
	}

	public AliPayTask(Activity context,String type,JPay.JPayListener listener) {
		this.mContext = context;
		this.mJPayListener =listener;
		this.serverType = type;
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
	protected void onPreExecute() {
		super.onPreExecute();
	}


	@Override
	protected void onPostExecute(String result) {
		Log.e("AideaAliPayTask", "result"+result);
	//	AlipayBean resultBean = JSONObject.parseObject(result,new TypeReference<AlipayBean>(){});
		try {
			if (result!=null){
			//	AlipayBean resultBean = JSONObject.parseObject(result,new TypeReference<AlipayBean>(){});
				if(serverType.equals(API_TYPE_PHP)){
					System.out.println("PHP后台返回的数据orderInfo="+result);
					PayUtils.getIntance(mContext).startAliPay(result,mJPayListener);
				}else{
					JSONObject jsonObject=new JSONObject(result);
					int code  = jsonObject.getInt("code");
					if(code==0){
						String resultData = jsonObject.getString("data");
						System.out.println("orderInfo="+resultData);
						///Toast.makeText(mContext, "正在调起支付", Toast.LENGTH_SHORT).show();
						PayUtils.getIntance(mContext).startAliPay(resultData,mJPayListener);
					}else{
						Toast.makeText(mContext, "错误"+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
					}

				}

			}else {

				System.out.println("get  AliPay exception, is null");
			}
		} catch (Exception e) {
			Log.e("PAY_GET", "异常："+e.getMessage());
        	Toast.makeText(mContext, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
		super.onPostExecute(result);
	}

}
