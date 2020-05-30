package cn.com.liandisys.shopmall.enumerat;

/**
 * 商品上架状态枚举.（0-下架 1-上架）
 *
 * @author zhuht
 */
public enum SellStatusEnum {

	// 下架
	DOWN(1),
	// 上架
	UP(0);

	// 成员变量
	private int value;

	// 构造方法
	private SellStatusEnum(int value) {
		this.value = value;
	}

	// get 方法
	public int getEnumValue() {
		return value;
	}

}
