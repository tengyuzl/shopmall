package cn.com.liandisys.shopmall.response.form;

import java.io.Serializable;


public class GoodsResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String name;
    
    private String priceUnit;
    
    private double price;
    
    private String info;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getPriceUnit() {
      return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
      this.priceUnit = priceUnit;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public String getInfo() {
      return info;
    }

    public void setInfo(String info) {
      this.info = info;
    }
}
