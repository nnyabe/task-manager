package com.task.manager.controller;


import com.task.manager.model.TaskModel;
import com.task.manager.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping(produces = {"text/html", "application/json"})
    public String listTasks(TaskModel model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Fetch all tasks from the service
        List<TaskModel> tasks = taskService.getAllTasks();

        // Check if the client requested JSON
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains("application/json")) {
            // Set the response type to JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Convert tasks to JSON and write to the response

            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(tasks));

            // Return null to indicate no view should be rendered
            return null;
        }

        // Otherwise, render the HTML view
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
//    @GetMapping("/{id}")
//    public String getTask(@PathVariable int id, TaskModel model) {
//        model.addAttribute("task", taskService.getTaskById(id));
//        return "task";
//    }
//
//    @PostMapping
//    public String createTask(TaskModel task) {
//        taskService.createTask(task);
//        return "redirect:/tasks";
//    }
}