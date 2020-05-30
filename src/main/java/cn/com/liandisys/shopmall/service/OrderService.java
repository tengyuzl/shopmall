package cn.com.liandisys.shopmall.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.liandisys.shopmall.dao.GoodsDao;
import cn.com.liandisys.shopmall.dao.OrderDao;
import cn.com.liandisys.shopmall.entity.OrderInfo;
import cn.com.liandisys.shopmall.enumerat.IsDeletedEnum;
import cn.com.liandisys.shopmall.enumerat.PayOrderStatusEnum;
import cn.com.liandisys.shopmall.response.OrderResult;

@Service
public class OrderService extends BaseService {

  // 商品默认购买数量1
  private final static double DEFAULT_GOODSNUM = 1;

  @Autowired
  private OrderDao orderDao;

  @Autowired
  private GoodsDao goodsDao;
  
  /**
   * 新增订单
   * 
   * @return
   */
  public OrderInfo insertOrder(String orderNo,String goodsId, String file01Name, String file25Name,String curUserId) {
    OrderInfo order = new OrderInfo();
    order.setOrderNo(orderNo);
    order.setGoodsId(goodsId);
    order.setGoodsNum(DEFAULT_GOODSNUM);
    
    double goodPrice = goodsDao.findById(goodsId).get().getPrice();
    double totalPrice = goodPrice*DEFAULT_GOODSNUM;
    order.setTotalPrice(totalPrice);
    order.setPayStatus(PayOrderStatusEnum.WAIT.getEnumValue());
    order.setOrderStatus(PayOrderStatusEnum.WAIT.getEnumValue());
    order.setFile01(file01Name);
    order.setFile25(file25Name);
    order.setIsDeleted(IsDeletedEnum.NOTDEL.getEnumValue());
    order.setCreateUser(curUserId);

    return orderDao.save(order);
  }
  
  /**
   * 根据创建id查询订单
   * @param userId 用户id
   * @return List<OrderInfo>
   */
  public List<OrderResult> findOrderListByUserId(String userId){
    return orderDao.findOrderByUserId(userId);
  }
  
  /**
   * 根据订单编号查询订单
   * @param orderNo 订单编号
   * @return OrderInfo
   */
  public OrderInfo findOrderById(String orderNo) {
    return orderDao.findById(orderNo).get();
  }
}
