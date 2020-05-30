package cn.com.liandisys.shopmall.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the goods_type database table.
 *
 */
@Entity
@NamedQuery(name = "GoodsType.findAll", query = "SELECT t FROM GoodsType t")
public class GoodsType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    private Integer level;

    @Column(name = "parent_id")
    private String parentId;

    private String name;
    
    private Integer rank;
    
    @Column(name = "is_deleted")
    private Integer isDeleted;
    
    @Column(name = "is_vip")
    private Integer isVip;
    
    @Column(name = "create_user")
    private String createUser; 
    
    @Column(name = "create_time")
    private Date createTime; 
    
    @Column(name = "update_user")
    private String updateUser; 
    
    @Column(name = "update_time")
    private Date updateTime; 

    public GoodsType() {}

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public Integer getLevel() {
      return level;
    }

    public void setLevel(Integer level) {
      this.level = level;
    }

    public String getParentId() {
      return parentId;
    }

    public void setParentId(String parentId) {
      this.parentId = parentId;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getRank() {
      return rank;
    }

    public void setRank(Integer rank) {
      this.rank = rank;
    }

    public Integer getIsDeleted() {
      return isDeleted;
    }

    public Integer getIsVip() {
      return isVip;
    }

    public void setIsVip(Integer isVip) {
      this.isVip = isVip;
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
