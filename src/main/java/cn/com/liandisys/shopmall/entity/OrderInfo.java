package cn.com.liandisys.shopmall.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the order database table.
 *
 */
@Entity
@NamedQuery(name = "OrderInfo.findAll", query = "SELECT t FROM OrderInfo t")
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "order_no")
    private String orderNo;
    
    @Column(name = "goods_id")
    private String goodsId;
    
    @Column(name = "goods_num")
    private Double goodsNum;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    @Column(name = "pay_status")
    private Integer payStatus;
    
    @Column(name = "pay_type")
    private Integer payType;
    
    @Column(name = "pay_time")
    private Date paytime; 
    
    @Column(name = "order_status")
    private Integer orderStatus;
    
    @Column(name = "file01")
    private String file01;
    
    @Column(name = "file25")
    private String file25;

    @Column(name = "extra_info")
    private String extraInfo;
    
    @Column(name = "is_deleted")
    private Integer isDeleted;
    
    @Column(name = "do_user")
    private String doUser; 
    
    @Column(name = "create_user")
    private String createUser; 
    
    @Column(name = "create_time")
    private Date createTime; 
    
    @Column(name = "update_user")
    private String updateUser; 
    
    @Column(name = "update_time")
    private Date updateTime; 

    public OrderInfo() {}

    public String getOrderNo() {
      return orderNo;
    }

    public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
    }

    public Double getTotalPrice() {
      return totalPrice;
    }
    
    public String getGoodsId() {
      return goodsId;
    }

    public void setGoodsId(String goodsId) {
      this.goodsId = goodsId;
    }

    public Double getGoodsNum() {
      return goodsNum;
    }

    public void setGoodsNum(Double goodsNum) {
      this.goodsNum = goodsNum;
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

    public String getExtraInfo() {
      return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
      this.extraInfo = extraInfo;
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

    public String getDoUser() {
      return doUser;
    }

    public void setDoUser(String doUser) {
      this.doUser = doUser;
    }

    public Integer getIsDeleted() {
      return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
      this.isDeleted = isDeleted;
    }

    public String getCreateUser() {
      return createUser;
    }

    public void setCreateUser(String createUser) {
      this.createUser = createUser;
    }

    public Date getCreateTime() {
      return createTime;
    }

    public void setCreateTime(Date createTime) {
      this.createTime = createTime;
    }

    public String getUpdateUser() {
      return updateUser;
    }

    public void setUpdateUser(String updateUser) {
      this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
      return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
    }
}
