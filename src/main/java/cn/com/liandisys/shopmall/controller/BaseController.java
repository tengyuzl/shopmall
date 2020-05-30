package cn.com.liandisys.shopmall.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import cn.com.liandisys.shopmall.common.Conts;
import cn.com.liandisys.shopmall.entity.Users;

public class BaseController {

  /** UUID默认长度 */
  private final static double DEFAULT_UUID_LENGTH = 32;

  /**
   * 获取当前登陆用户
   * 
   * @param httpSession
   * @return user
   */
  protected Users getCurUser(HttpSession httpSession) {
    Users user;
    try {
      user = (Users) httpSession.getAttribute(Conts.SESSION_USER_KEY);
    } catch (Exception e) {
      return null;
    }
    return user;
  }

  /**
   * 判断用户是否登陆
   * 
   * @return true/false 登陆成功/未登陆
   */
  protected boolean checkUser(HttpSession httpSession) {
    Users user;
    try {
      user = (Users) httpSession.getAttribute(Conts.SESSION_USER_KEY);
    } catch (Exception e) {
      return false;
    }
    if (Objects.isNull(user)) {
      return false;
    }
    if (StringUtils.isEmpty(user.getId())) {
      return false;
    }
    if (user.getId().length() != DEFAULT_UUID_LENGTH) {
      return false;
    }
    return true;
  }

  /**
   * 设置用户信息到session
   * 
   * @param httpSession
   * @param user 用户
   */
  protected void setCurUser(HttpSession httpSession, Users user) {
    httpSession.setAttribute(Conts.SESSION_USER_KEY, user);
  }
}
