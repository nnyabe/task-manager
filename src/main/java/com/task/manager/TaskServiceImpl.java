package com.task.manager.service;


import com.task.manager.interfaces.TaskService;
import com.task.manager.model.TaskModel;
import com.task.manager.repository.TaskRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepositoryImpl taskRepository;


    @Autowired
    public TaskServiceImpl(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskModel getTaskById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public void createTask(TaskModel task) {
        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskModel task) {
        taskRepository.update(task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.delete(id);
    }
}
