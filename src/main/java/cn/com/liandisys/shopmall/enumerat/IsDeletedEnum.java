package cn.com.liandisys.shopmall.enumerat;

/**
 * 状态枚举.（0：未删除  1：已删除）
 *
 * @author zhuht
 */
public enum IsDeletedEnum {

	// 未删除
	NOTDEL(0),
	// 已删除
	HASDEL(1);

	// 成员变量
	private int value;

	// 构造方法
	private IsDeletedEnum(int value) {
		this.value = value;
	}

	// get 方法
	public int getEnumValue() {
		return value;
	}

}
