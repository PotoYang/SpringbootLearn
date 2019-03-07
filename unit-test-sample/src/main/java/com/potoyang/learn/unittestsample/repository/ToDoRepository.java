package com.potoyang.learn.unittestsample.repository;

import com.potoyang.learn.unittestsample.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:05
 * Modified:
 * Description:
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
