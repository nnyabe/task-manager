package com.task.manager.repository;


import com.task.manager.model.TaskModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TaskModel save(TaskModel task) {
        String sql = "INSERT INTO tasks (title, description, status, due_date, priority) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus(), task.getDueDate(), task.getPriority());
        return getLastInsertedTask();
    }

    private TaskModel getLastInsertedTask() {
        String sql = "SELECT * FROM tasks ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new TaskRowMapper());
    }

    private static class TaskRowMapper implements RowMapper<TaskModel> {
        @Override
        public TaskModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaskModel task = new TaskModel();
            task.setId(rs.getLong("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setStatus(rs.getString("status"));
            task.setDueDate(rs.getString("due_date"));
            task.setPriority(rs.getInt("priority"));
            return task;
        }
    }
}
