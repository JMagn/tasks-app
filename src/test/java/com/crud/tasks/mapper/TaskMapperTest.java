package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task_1", "Test_task");
        Task task = new Task(1L, "Task_1", "Test_task");
        //When
        Task result = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(task, result);
    }

    @Test
    public void mapToTaskDto() {
        //Given
        TaskDto taskDto = new TaskDto(2L, "Task_2", "Test_task");
        Task task = new Task(2L, "Task_2", "Test_task");
        //When
        TaskDto result = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(taskDto, result);
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = Collections.singletonList(new Task(3L, "Task_3", "Test_task"));
        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1, result.size());
        assertEquals(Collections.singletonList(new TaskDto(3L, "Task_3", "Test_task")), result);
    }

    @Test
    public void mapNullToTask() {
        //Given
        TaskDto taskDto = null;
        //When
        Task result = taskMapper.mapToTask(taskDto);
        //Then
        assertNull(result);
    }

    @Test
    public void mapNullToTaskDto() {
        //Given
        Task task = null;
        //When
        TaskDto result = taskMapper.mapToTaskDto(task);
        //Then
        assertNull(result);
    }

    @Test
    public void mapNullToTaskDtoList() {
        //Given
        List<Task> taskList = null;
        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertNull(result);
    }
}