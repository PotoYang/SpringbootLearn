package com.potoyang.learn.shopee.catagory.repository;

import com.potoyang.learn.shopee.catagory.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 10:21
 * Modified:
 * Description:
 */
@Repository
public interface ShopeeCategoryRepository extends CrudRepository<Category, Integer> {
    /**
     * 根据 catid 查找类别
     *
     * @param catid 类别 id
     * @return 类别
     */
    Optional<Category> findByCatid(Integer catid);

    /**
     * 根据显示的类别名称查找类别
     *
     * @param displayName 显示的类别名称
     * @return 类别
     */
    Optional<Category> findByDisplayName(String displayName);
}
