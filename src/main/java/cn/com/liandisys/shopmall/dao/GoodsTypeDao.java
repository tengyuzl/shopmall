package cn.com.liandisys.shopmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.com.liandisys.shopmall.entity.GoodsType;

public interface GoodsTypeDao extends JpaRepository<GoodsType, String>,
    JpaSpecificationExecutor<GoodsType> {
}
