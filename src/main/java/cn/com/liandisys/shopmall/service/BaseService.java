package cn.com.liandisys.shopmall.service;

import java.util.UUID;

public class BaseService {

  public String getUUID() {
    return UUID.randomUUID().toString().replaceAll("-","");
  }
}
