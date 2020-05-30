package cn.com.liandisys.shopmall.enumerat;

/**
 * 订单状态枚举.（0：商品不存在  1：vip用户 ）
 *
 * @author zhuht
 */
public enum OrderErrorEnum {

	// 商品不存在
	NOTGOODS("0"),
	// vip用户
	VIPGOODS("1"),
	// 上传订单文件失败
	FILEERROR("2"),
    // 创建订单失败
    CREATEORDERERROR("3");

	// 成员变量
	private String value;

	// 构造方法
	private OrderErrorEnum(String value) {
		this.value = value;
	}

	// get 方法
	public String getEnumValue() {
		return value;
	}

}
