package com.task.manager.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.manager.dto.TaskDTO;
import com.task.manager.model.TaskModel;
import com.task.manager.service.TaskServiceImpl;
import com.task.manager.utils.SortingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping(produces = { "application/json"} , path = "/")
    public ResponseEntity<List<TaskDTO>> listTasks(@RequestParam(value = "sort", required = false) String sortType,
                                                   TaskModel model, HttpServletRequest request) throws IOException {

        if(sortType == null) {
            sortType = "asc";
        }
        List<TaskModel> tasks = taskService.getAllTasks();
        if(sortType.equals("id")) {
            tasks = SortingUtils.quickSortById(tasks);
        } else if (sortType.equals("title")) {
            tasks = SortingUtils.mergeSortByTitle(tasks);
        } else if (sortType.equals("dueDate")) {
            tasks = SortingUtils.bubbleSortByDueDate(tasks);
        }else if(sortType.equals("description")) {
            tasks = SortingUtils.heapSortByDescription(tasks);
        }
        List<TaskDTO> taskUrls = new ArrayList<>();
        for(TaskModel task : tasks) {
            String url = request.getRequestURL().toString() + task.getId();
            taskUrls.add(new TaskDTO(task.getId(), task.getTitle(),url));

        }
        return new ResponseEntity<>(taskUrls, HttpStatus.OK);
    }
    @GetMapping(produces = { "application/json"} , path = "/{id}")
    public ResponseEntity<TaskModel> getTask(@PathVariable("id") int id, TaskModel model) {
        TaskModel task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping(path="/", produces = {"application/json"})
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskModel task, HttpServletRequest request) {

        taskService.createTask(task);
        return new ResponseEntity<>(new TaskDTO( task.getTitle(), request.getRequestURL().toString()), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable("id") int id, HttpServletRequest request) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(new TaskDTO(request.getRequestURL().toString()), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id, @RequestBody TaskModel updatedTask, HttpServletRequest request) {
        TaskModel existingTask = taskService.getTaskById(id);

        if (existingTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(updatedTask.getTitle() == null){
            updatedTask.setTitle(existingTask.getTitle());
        }
        if(updatedTask.getDescription() == null){
            updatedTask.setDescription(existingTask.getDescription());
        }
        if(updatedTask.getStatus() == null){
            updatedTask.setStatus(existingTask.getStatus());
        }
        if(updatedTask.getDueDate() == null){
            updatedTask.setDueDate(existingTask.getDueDate());
        }

        updatedTask.setId(id);

        taskService.updateTask(updatedTask);
        String url = request.getRequestURL().toString();
        return new ResponseEntity<>(new TaskDTO(updatedTask.getId(), updatedTask.getTitle(), url), HttpStatus.OK);
    }
}