package com.potoyang.learn.unittestsample.service;

import com.potoyang.learn.unittestsample.model.ToDo;
import com.potoyang.learn.unittestsample.repository.ToDoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/8 23:50
 * Modified:
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ToDoServiceTest {
    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoServiceImpl toDoService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllToDo() {
        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(new ToDo(1, "ToDo Sample 1.", true));
        toDoList.add(new ToDo(2, "ToDo Sample 2.", true));
        toDoList.add(new ToDo(3, "ToDo Sample 3.", false));
        when(toDoRepository.findAll()).thenReturn(toDoList);

        List<ToDo> result = toDoService.getAllToDo();

        assertEquals(3, result.size());
    }

    @Test
    public void testGetToDoById() {
        ToDo toDo = new ToDo(1, "ToDo Sample 1.", true);
        when(toDoRepository.findById(1L).get()).thenReturn(toDo);
        ToDo result = toDoService.getToDoById(1L);
        assertEquals(1, result.getId());
        assertEquals("ToDo Sample 1.", result.getText());
        assertTrue(result.isCompleted());
    }

    @Test
    public void testSaveToDo() {
        ToDo toDo = new ToDo(8, "ToDo Sample 8.", false);
        when(toDoRepository.save(toDo)).thenReturn(toDo);
        ToDo result = toDoService.saveToDo(toDo);
        assertEquals(8, result.getId());
        assertEquals("ToDo Sample 8.", result.getText());
        assertTrue(result.isCompleted());
    }

    @Test
    public void removeToDo() {
        ToDo toDo = new ToDo(8, "ToDo Sample 8.", false);
        toDoService.removeToDo(toDo);
        verify(toDoRepository, times(1)).delete(toDo);
    }
}
