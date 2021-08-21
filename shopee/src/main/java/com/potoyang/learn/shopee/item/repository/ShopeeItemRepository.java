package com.potoyang.learn.shopee.item.repository;

import com.potoyang.learn.shopee.item.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 18:41
 * Modified:
 * Description:
 */
@Repository
public interface ShopeeItemRepository extends CrudRepository<Item, Integer> {
}
