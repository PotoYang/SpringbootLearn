package com.potoyang.learn.unittestsample.service;

import com.potoyang.learn.unittestsample.model.ToDo;

import java.util.List;
import java.util.Optional;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:07
 * Modified:
 * Description:
 */
public interface ToDoService {
    List<ToDo> getAllToDo();

    ToDo getToDoById(long id);

    ToDo saveToDo(ToDo toDo);

    void removeToDo(ToDo toDo);
}
