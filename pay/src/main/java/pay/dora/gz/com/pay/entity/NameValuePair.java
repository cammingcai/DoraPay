package pay.dora.gz.com.pay.entity;

/**
 * Created by camming on 2019\2\15 0015.
 * code is data  data is code
 */

public class NameValuePair {

    private String name;
    private String value;

    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
