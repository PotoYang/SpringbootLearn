package com.potoyang.learn.getsomenovel.dao;

import com.potoyang.learn.getsomenovel.entity.CateAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/10 11:57
 * Modified By:
 * Description:
 */
public interface CateFirstRepository extends JpaRepository<CateAll, Integer>, JpaSpecificationExecutor {
}
