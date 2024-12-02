package com.task.manager.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.manager.dto.TaskDTO;
import com.task.manager.model.TaskModel;
import com.task.manager.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<TaskDTO>> listTasks(TaskModel model, HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod) throws IOException {

        List<TaskModel> tasks = taskService.getAllTasks();
        List<TaskDTO> taskUrls = new ArrayList<>();
        for(TaskModel task : tasks) {
            String url = request.getRequestURL().toString() + task.getId();
            taskUrls.add(new TaskDTO(task.getId(), task.getTitle(),url));

        }
        System.out.println(request.getRequestURL().append("2/"));

        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains("application/json")) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(tasks));
            System.out.println(response.getContentType());
            return null;
        }
        return new ResponseEntity<>(taskUrls, HttpStatus.OK);
    }
    @GetMapping(produces = { "application/json"} , path = "/{id}")
    public ResponseEntity<TaskModel> getTask(@PathVariable("id") int id, TaskModel model) {
        TaskModel task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public String createTask(TaskModel task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }
}