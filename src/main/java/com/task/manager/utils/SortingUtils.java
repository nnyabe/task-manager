package com.task.manager.utils;

import com.task.manager.model.TaskModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingUtils {

    // Quick Sort by ID
    public static List<TaskModel> quickSortById(List<TaskModel> tasks) {
        if (tasks.size() <= 1) {
            return tasks;
        }

        TaskModel pivot = tasks.get(tasks.size() / 2);
        List<TaskModel> less = new ArrayList<>();
        List<TaskModel> equal = new ArrayList<>();
        List<TaskModel> greater = new ArrayList<>();

        for (TaskModel task : tasks) {
            if (task.getId() < pivot.getId()) {
                less.add(task);
            } else if (task.getId() == pivot.getId()) {
                equal.add(task);
            } else {
                greater.add(task);
            }
        }

        List<TaskModel> sorted = new ArrayList<>();
        sorted.addAll(quickSortById(less));
        sorted.addAll(equal);
        sorted.addAll(quickSortById(greater));

        return sorted;
    }

    // Merge Sort by Title
    public static List<TaskModel> mergeSortByTitle(List<TaskModel> tasks) {
        if (tasks.size() <= 1) {
            return tasks;
        }

        int mid = tasks.size() / 2;
        List<TaskModel> left = mergeSortByTitle(new ArrayList<>(tasks.subList(0, mid)));
        List<TaskModel> right = mergeSortByTitle(new ArrayList<>(tasks.subList(mid, tasks.size())));

        return mergeByTitle(left, right);
    }

    private static List<TaskModel> mergeByTitle(List<TaskModel> left, List<TaskModel> right) {
        List<TaskModel> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getTitle().compareToIgnoreCase(right.get(j).getTitle()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));

        return result;
    }

    // Bubble Sort by Due Date
    public static List<TaskModel> bubbleSortByDueDate(List<TaskModel> tasks) {
        List<TaskModel> sortedTasks = new ArrayList<>(tasks);
        int n = sortedTasks.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedTasks.get(j).getDueDate().isAfter(sortedTasks.get(j + 1).getDueDate())) {
                    Collections.swap(sortedTasks, j, j + 1);
                }
            }
        }

        return sortedTasks;
    }
}
