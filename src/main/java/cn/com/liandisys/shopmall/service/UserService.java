package cn.com.liandisys.shopmall.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import cn.com.liandisys.shopmall.dao.UserDao;
import cn.com.liandisys.shopmall.entity.Users;

@Service
public class UserService extends BaseService{
  
  @Autowired
  private UserDao userDao;
  
  /**
   * 根据用户名获取用户信息
   * @param uname 用户名
   * @return User
   */
  public Users getUserByUname(String uname) {
    Users userFilter = new Users();
    userFilter.setUname(uname);
    Example<Users> example = Example.of(userFilter);
    List<Users> userList = userDao.findAll(example);
    if(Objects.isNull(userList)) {
      return null;
    }
    if(userList.isEmpty()) {
      return null;
    }
    return userList.get(0);
  }
}
