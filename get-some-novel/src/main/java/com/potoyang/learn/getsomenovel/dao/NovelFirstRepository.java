package com.potoyang.learn.getsomenovel.dao;

import com.potoyang.learn.getsomenovel.entity.NovelAll;
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
public interface NovelFirstRepository extends JpaRepository<NovelAll, Integer>, JpaSpecificationExecutor {
}
