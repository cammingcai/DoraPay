package pay.dora.gz.com.pay.entity;

/**
 * Created by camming on 2019\1\31 0031.
 * code is data  data is code
 *
 * 微信支付签名信息实体类
 */

public class WechatPayBean {

    /**微信开放平台应用APPid*/
    private String appid;
    /**商户ID*/
    private String mch_id;
    /**预付订单ID*/
    private String prepay_id;
    /**随机字符串*/
    private String nonce_str;
    /**时间戳*/
    private String timestamp;
    /**订单签名信息 这个不知道干嘛的*/
    private String sign;
    /**订单签名信息*/
    private String signB;

    public String getSignB() {
        return signB;
    }

    public void setSignB(String signB) {
        this.signB = signB;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
