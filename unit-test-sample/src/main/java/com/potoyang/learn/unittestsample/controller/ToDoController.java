package com.potoyang.learn.unittestsample.controller;

import com.potoyang.learn.unittestsample.exception.ToDoException;
import com.potoyang.learn.unittestsample.model.Response;
import com.potoyang.learn.unittestsample.model.ToDo;
import com.potoyang.learn.unittestsample.service.ToDoService;
import com.potoyang.learn.unittestsample.util.PayloadValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:12
 * Modified:
 * Description:
 */
@RestController
@Slf4j
public class ToDoController {
    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/todo")
    public ResponseEntity<List<ToDo>> getAllToDo() {
        log.info("Returning all the ToDo's.");
        return new ResponseEntity<>(toDoService.getAllToDo(), HttpStatus.OK);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable("id") long id) throws ToDoException {
        log.info("ToDo id = [{}] to return.", id);
        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            throw new ToDoException("ToDo doesn't exit.");
        }
        return new ResponseEntity<>(toDo, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Response> removeToDoById(@PathVariable("id") long id) throws ToDoException {
        log.info("ToDo id = [{}] to remove.", id);
        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            throw new ToDoException("ToDo to delete doesn't exist.");
        }
        toDoService.removeToDo(toDo);
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"), HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDo> saveToDo(@RequestBody ToDo payload) throws ToDoException {
        log.info("Payload = [{}] to save.", payload);
        if (!PayloadValidator.validateCreatePayload(payload)) {
            throw new ToDoException("Payload malformed, id must not be defined.");
        }
        return new ResponseEntity<>(toDoService.saveToDo(payload), HttpStatus.OK);
    }

    @PatchMapping("/todo")
    public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo payload) throws ToDoException {
        log.info("Payload = [{}] to update.", payload);
        ToDo toDo = toDoService.getToDoById(payload.getId());
        if (toDo == null) {
            throw new ToDoException("ToDo to update doesn't exit.");
        }
        return new ResponseEntity<>(toDoService.saveToDo(payload), HttpStatus.OK);

    }
}
