package cn.com.liandisys.shopmall.enumerat;

/**
 * 类别vip枚举.（0：是  1：否）
 *
 * @author zhuht
 */
public enum IsVipEnum {

	// 是
	VIP(2),
	// 否
	VIPNOT(0);

	// 成员变量
	private int value;

	// 构造方法
	private IsVipEnum(int value) {
		this.value = value;
	}

	// get 方法
	public int getEnumValue() {
		return value;
	}

}
