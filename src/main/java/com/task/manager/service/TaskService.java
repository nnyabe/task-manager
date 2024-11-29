package com.task.manager.service;


import com.task.manager.model.TaskModel;
import com.task.manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel createTask(TaskModel task) {
        return taskRepository.save(task);
    }
}
