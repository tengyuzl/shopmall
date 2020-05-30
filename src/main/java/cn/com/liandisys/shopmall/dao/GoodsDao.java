package cn.com.liandisys.shopmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.com.liandisys.shopmall.entity.Goods;

public interface GoodsDao extends JpaRepository<Goods, String>,
    JpaSpecificationExecutor<Goods> {
}
