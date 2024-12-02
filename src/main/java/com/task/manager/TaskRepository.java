package com.task.manager.interfaces;

import com.task.manager.model.TaskModel;

import java.util.List;

public interface TaskRepository {
    List<TaskModel> findAll();

    TaskModel findById(int id);

    void save(TaskModel task);

    void update(TaskModel task);

    void delete(int id);
}
