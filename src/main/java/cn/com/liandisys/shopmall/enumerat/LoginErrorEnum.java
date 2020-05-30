package cn.com.liandisys.shopmall.enumerat;

/**
 * 登陆状态枚举.（0：用户名密码不正确 1：验证码不正确 2:用户名不能未空 3:密码不能为空 4 验证码不能为空 5用户不存在 6后台管理用户）
 *
 * @author zhuht
 */
public enum LoginErrorEnum {

  // 用户名密码不正确
  UPWD("0"),
  // 验证码不正确
  VCODE("1"),
  // 用户名不能未空
  NULLUNAME("2"),
  // 验证码不正确
  NULLUPWD("3"),
  // 验证码不正确
  NULLVCODE("4"),
  // 用户不存在
  ERRORUSER("5"),
  // 后台管理用户
  ADMINUSER("6");

  // 成员变量
  private String value;

  // 构造方法
  private LoginErrorEnum(String value) {
    this.value = value;
  }

  // get 方法
  public String getEnumValue() {
    return value;
  }

}
