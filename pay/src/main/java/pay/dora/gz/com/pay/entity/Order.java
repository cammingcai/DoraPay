package pay.dora.gz.com.pay.entity;


/**
 *
 * Create by camming 2019/02/14
 * 支付订单实体类
 *
 *
 * */
public class Order {

	//对一笔交易的具体描述
	private String body;
	//商品的标题
	private String subject;
	//商户唯一订单号
	private String out_trade_no;
	//支付宝通知商户的Url
	private String nofityUrl;
	//支付宝订单金额 单位为元，精确到小数点后两位，取值范围
	private String total_amount;
	//微信支付金额 单位为元
	private float total_fee;

	private int months;
	private int subjectTypeId;
	private float actualPayPrice;

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getSubjectTypeId() {
		return subjectTypeId;
	}

	public void setSubjectTypeId(int subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}

	public float getActualPayPrice() {
		return actualPayPrice;
	}

	public void setActualPayPrice(float actualPayPrice) {
		this.actualPayPrice = actualPayPrice;
	}

	public float getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(float total_fee) {
		this.total_fee = total_fee;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}



	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getNofityUrl() {
		return nofityUrl;
	}
	public void setNofityUrl(String nofityUrl) {
		this.nofityUrl = nofityUrl;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}