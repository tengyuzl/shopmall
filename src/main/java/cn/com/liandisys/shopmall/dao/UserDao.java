package cn.com.liandisys.shopmall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import cn.com.liandisys.shopmall.entity.Users;

public interface UserDao extends JpaRepository<Users, String>,
    JpaSpecificationExecutor<Users> {
}
