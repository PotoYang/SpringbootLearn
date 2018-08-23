package com.potoyang.learn.getsomenovel.dao;

import com.potoyang.learn.getsomenovel.entity.NaviBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/10 11:57
 * Modified By:
 * Description:
 */
public interface NaviBarRepository extends JpaRepository<NaviBar, Integer>, JpaSpecificationExecutor {
    NaviBar findNaviBarById(Integer id);
}
