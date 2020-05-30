package cn.com.liandisys.shopmall.enumerat;

/**
 * 支付状态枚举.（0.未支付,1.支付成功,-1:支付失败,2.处理成功,-2:处理失败）
 *
 * @author zhuht
 */
public enum PayOrderStatusEnum {

  // 未支付
  WAIT(0),
  // 支付成功
  OK(1),
  // 支付失败
  FAIL(-1),
  // 处理成功
  EDNOK(2),
  // 处理失败
  ENDFAIL(-2);

  // 成员变量
  private int value;

  // 构造方法
  private PayOrderStatusEnum(int value) {
    this.value = value;
  }

  // get 方法
  public int getEnumValue() {
    return value;
  }

}
