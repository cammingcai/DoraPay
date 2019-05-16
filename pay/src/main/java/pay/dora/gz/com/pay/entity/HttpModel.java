package pay.dora.gz.com.pay.entity;

/**
 * Created by camming on 2019\01\26 0026.
 * code is data  data is code
 * 网络请求回调父类，必有参数success message code
 */

public class HttpModel {

    private boolean success;
    private String message;
    private int code;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
