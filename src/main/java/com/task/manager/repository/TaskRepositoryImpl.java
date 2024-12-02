package com.task.manager.repository;


import com.task.manager.interfaces.TaskRepository;
import com.task.manager.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSource dataSource;

    @Autowired
    public TaskRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TaskModel> findAll() {
        String query = "SELECT * FROM tasks";
        List<TaskModel> tasks = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                TaskModel task = mapRowToTask(resultSet);
                tasks.add(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching tasks from the database", e);
        }

        return tasks;
    }

    @Override
    public TaskModel findById(int id) {
        String query = "SELECT * FROM tasks WHERE id = ?";
        TaskModel task = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    task = mapRowToTask(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching task by ID from the database", e);
        }

        return task;
    }

    @Override
    public void save(TaskModel task) {
        String query = "INSERT INTO tasks (title, description, due_date, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getDueDate()));
            preparedStatement.setString(4, task.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving task to the database", e);
        }
    }

    @Override
public void update(TaskModel task) {
        String query = "UPDATE tasks SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getDueDate()));
            preparedStatement.setString(4, task.getStatus());
            preparedStatement.setInt(5, task.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating task in the database", e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting task from the database", e);
        }
    }

    private TaskModel mapRowToTask(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();
        String status = resultSet.getString("status");

        return new TaskModel(id, title, description, dueDate, status);
    }
}
