package cn.com.liandisys.shopmall.enumerat;

/**
 * 用户锁定枚举.（0：未锁定 1：已锁定无法登陆）
 *
 * @author zhuht
 */
public enum LockedEnum {

  // 未锁定
  LOCKED(0),
  // 已锁定无法登陆
  NOTLOCK(1);

  // 成员变量
  private int value;

  // 构造方法
  private LockedEnum(int value) {
    this.value = value;
  }

  // get 方法
  public int getEnumValue() {
    return value;
  }

}
