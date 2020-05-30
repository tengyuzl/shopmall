package cn.com.liandisys.shopmall.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cn.com.liandisys.shopmall.entity.OrderInfo;
import cn.com.liandisys.shopmall.response.OrderResult;

public interface OrderDao extends JpaRepository<OrderInfo, String>,
    JpaSpecificationExecutor<OrderInfo> {
  
  @Query(value = "select new cn.com.liandisys.shopmall.response.OrderResult(order.orderNo,goods.name as goodsName,order.goodsNum,order.totalPrice,order.payStatus,order.payType,order.paytime,order.orderStatus,order.file01,order.file25) from  OrderInfo order left  join Goods goods on order.goodsId=goods.id where order.createUser =:userId ")
  List<OrderResult> findOrderByUserId(@Param("userId") String userId);

}
