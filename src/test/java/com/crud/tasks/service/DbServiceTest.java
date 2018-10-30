package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        List<Task> taskList = Collections.singletonList(new Task(1L, "Task 1", "Test_task"));
        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        assertEquals(1, result.size());
        assertEquals(taskList, result);
    }

    @Test
    public void shouldGetEmptyTasks() {
        //Given
        List<Task> emptyList = new ArrayList<>();
        when(repository.findAll()).thenReturn(emptyList);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        assertEquals(0, result.size());
        assertNotNull(result);
    }

    @Test
    public void shouldGetTaskById() {
        //Given
        Task task = new Task(2L, "Task 2", "Test_task");
        when(repository.findOne(ArgumentMatchers.anyLong())).thenReturn(task);
        //When
        Task result = dbService.getTaskById(2L);
        //Then
        assertEquals(task, result);
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(3L, "Task 3", "Test_task");
        when(repository.save(ArgumentMatchers.any(Task.class))).thenReturn(task);
        //When
        Task result = dbService.saveTask(task);
        //Then
        assertEquals(task, result);
    }

    @Test
    public void shouldGetTask() {
        //Given
        Optional task = Optional.of(new Task(4L, "Task 4", "Test_task"));
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(task);
        //When
        Optional result = dbService.getTask(4L);
        //Then
        assertEquals(task, result);
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        //When
        dbService.deleteTask(1L);
        //Then
        verify(repository, times(1)).delete(1L);
    }
}