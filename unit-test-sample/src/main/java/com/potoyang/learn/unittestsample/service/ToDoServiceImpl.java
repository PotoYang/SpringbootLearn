package com.potoyang.learn.unittestsample.service;

import com.potoyang.learn.unittestsample.model.ToDo;
import com.potoyang.learn.unittestsample.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:06
 * Modified:
 * Description:
 */
@Service("toDoService")
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> getAllToDo() {
        return toDoRepository.findAll();
    }

    @Override
    public ToDo getToDoById(long id) {
        return toDoRepository.findById(id).get();
    }

    @Override
    public ToDo saveToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @Override
    public void removeToDo(ToDo toDo) {
        toDoRepository.delete(toDo);
    }
}
