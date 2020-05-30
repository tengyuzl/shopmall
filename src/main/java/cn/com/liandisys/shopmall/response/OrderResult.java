package cn.com.liandisys.shopmall.response;

import java.io.Serializable;
import java.util.Date;


public class OrderResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String orderNo;
    
    private String goodsName;
    
    private Double goodsNum;
    
    private Double totalPrice;
    
    private Integer payStatus;
    
    private Integer payType;
    
    private Date paytime; 
    
    private Integer orderStatus;
    
    private String file01;
    
    private String file25;


    public OrderResult() {}

    

    public OrderResult(String orderNo, String goodsName, Double goodsNum, Double totalPrice,
        Integer payStatus, Integer payType, Date paytime, Integer orderStatus, String file01,
        String file25) {
      super();
      this.orderNo = orderNo;
      this.goodsName = goodsName;
      this.goodsNum = goodsNum;
      this.totalPrice = totalPrice;
      this.payStatus = payStatus;
      this.payType = payType;
      this.paytime = paytime;
      this.orderStatus = orderStatus;
      this.file01 = file01;
      this.file25 = file25;
    }



    public String getOrderNo() {
      return orderNo;
    }


    public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
    }


    public String getGoodsName() {
      return goodsName;
    }


    public void setGoodsName(String goodsName) {
      this.goodsName = goodsName;
    }


    public Double getGoodsNum() {
      return goodsNum;
    }


    public void setGoodsNum(Double goodsNum) {
      this.goodsNum = goodsNum;
    }


    public Double getTotalPrice() {
      return totalPrice;
    }


    public void setTotalPrice(Double totalPrice) {
      this.totalPrice = totalPrice;
    }


    public Integer getPayStatus() {
      return payStatus;
    }


    public void setPayStatus(Integer payStatus) {
      this.payStatus = payStatus;
    }


    public Integer getPayType() {
      return payType;
    }


    public void setPayType(Integer payType) {
      this.payType = payType;
    }


    public Date getPaytime() {
      return paytime;
    }


    public void setPaytime(Date paytime) {
      this.paytime = paytime;
    }


    public Integer getOrderStatus() {
      return orderStatus;
    }


    public void setOrderStatus(Integer orderStatus) {
      this.orderStatus = orderStatus;
    }


    public String getFile01() {
      return file01;
    }


    public void setFile01(String file01) {
      this.file01 = file01;
    }


    public String getFile25() {
      return file25;
    }


    public void setFile25(String file25) {
      this.file25 = file25;
    }
    
    
}
