package com.task.manager.interfaces;

import com.task.manager.model.TaskModel;

import java.util.List;

public interface TaskService {

    List<TaskModel> getAllTasks();

    TaskModel getTaskById(int id);

    void createTask(TaskModel task);

    void updateTask(TaskModel task);

    void deleteTask(int id);
}