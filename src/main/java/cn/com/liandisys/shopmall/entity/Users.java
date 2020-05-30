package cn.com.liandisys.shopmall.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the goods database table.
 *
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT t FROM Users t")
public class Users implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private String id;

  private String uname;

  private String upwd;

  @Column(name = "short_name")
  private String shortName;

  private Integer locked;

  private String phone;

  private Integer utype;

  private String paypwd;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getUpwd() {
    return upwd;
  }

  public void setUpwd(String upwd) {
    this.upwd = upwd;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public Integer getLocked() {
    return locked;
  }

  public void setLocked(Integer locked) {
    this.locked = locked;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getUtype() {
    return utype;
  }

  public void setUtype(Integer utype) {
    this.utype = utype;
  }

  public String getPaypwd() {
    return paypwd;
  }

  public void setPaypwd(String paypwd) {
    this.paypwd = paypwd;
  }

}
