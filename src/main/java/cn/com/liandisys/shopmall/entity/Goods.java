package cn.com.liandisys.shopmall.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the goods database table.
 *
 */
@Entity
@NamedQuery(name = "Goods.findAll", query = "SELECT t FROM Goods t")
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    private String name;

    private String info;

    @Column(name = "goods_type_id")
    private String goodsTypeId;
    
    private Double price;
    
    @Column(name = "price_unit")
    private String priceUnit;
    
    @Column(name = "sell_status")
    private Integer sellStatus;
    
    @Column(name = "create_user")
    private String createUser; 
    
    @Column(name = "create_time")
    private Date createTime; 
    
    @Column(name = "update_user")
    private String updateUser; 
    
    @Column(name = "update_time")
    private Date updateTime; 

    public Goods() {}

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

    public String getInfo() {
      return info;
    }

    public void setInfo(String info) {
      this.info = info;
    }

    public String getGoodsTypeId() {
      return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
      this.goodsTypeId = goodsTypeId;
    }

    public Double getPrice() {
      return price;
    }

    public void setPrice(Double price) {
      this.price = price;
    }

    public String getPriceUnit() {
      return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
      this.priceUnit = priceUnit;
    }

    public Integer getSellStatus() {
      return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
      this.sellStatus = sellStatus;
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
