package pay.dora.gz.com.pay.entity;

import java.io.Serializable;

public class VipData implements Serializable {

    private int id;
    private String title;
    private String normal_price;
    private String original_price;
    private String sell_des;
    private String study_coin_use;
    private String normal_price_title;
    private String original_price_title;
    private String study_coin_proportion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNormal_price() {
        return normal_price;
    }

    public void setNormal_price(String normal_price) {
        this.normal_price = normal_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getSell_des() {
        return sell_des;
    }

    public void setSell_des(String sell_des) {
        this.sell_des = sell_des;
    }

    public String getStudy_coin_use() {
        return study_coin_use;
    }

    public void setStudy_coin_use(String study_coin_use) {
        this.study_coin_use = study_coin_use;
    }

    public String getNormal_price_title() {
        return normal_price_title;
    }

    public void setNormal_price_title(String normal_price_title) {
        this.normal_price_title = normal_price_title;
    }

    public String getOriginal_price_title() {
        return original_price_title;
    }

    public void setOriginal_price_title(String original_price_title) {
        this.original_price_title = original_price_title;
    }

    public String getStudy_coin_proportion() {
        return study_coin_proportion;
    }

    public void setStudy_coin_proportion(String study_coin_proportion) {
        this.study_coin_proportion = study_coin_proportion;
    }
}
