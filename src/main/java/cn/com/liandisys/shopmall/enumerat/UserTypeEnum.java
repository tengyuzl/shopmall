package cn.com.liandisys.shopmall.enumerat;

/**
 * 用户类型枚举.（0：注册用户 1：后台用户 2：vip用户）
 *
 * @author zhuht
 */
public enum UserTypeEnum {

  // 注册用户
  REGISTER(0),
  // 后台用户
  ADMIN(1),
  // vip用户
  VIP(2);

  // 成员变量
  private int value;

  // 构造方法
  private UserTypeEnum(int value) {
    this.value = value;
  }

  // get 方法
  public int getEnumValue() {
    return value;
  }

}
