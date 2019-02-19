package pay.dora.gz.com.pay.entity;


/**
 * Created by camming on 2019\2\14 0014.
 * code is data  data is code
 *
 * 微信支付
 */

public class WechatBean extends HttpModel {


    private WechatPayBean data;

    public WechatPayBean getData() {
        return data;
    }

    public void setData(WechatPayBean data) {
        this.data = data;
    }
}
