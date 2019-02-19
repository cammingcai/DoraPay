package pay.dora.gz.com.pay.data;

import java.io.Serializable;

/**
 * Created by camming on 2019\2\16 0016.
 * code is data  data is code
 */

public class PayData implements Serializable{

    private int subjectTypeId;
    private int months;
    private float price;
    private float originalPrice;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(int subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }
}
