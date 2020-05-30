package cn.com.liandisys.shopmall.enumerat;

/**
 * 支付类型枚举.（1.银联支付 2余额支付）
 *
 * @author zhuht
 */
public enum PayTypeEnum {

  // 银联支付
  UNION(1),
  // 余额支付
  BALANCE(2);

  // 成员变量
  private int value;

  // 构造方法
  private PayTypeEnum(int value) {
    this.value = value;
  }

  // get 方法
  public int getEnumValue() {
    return value;
  }

}
