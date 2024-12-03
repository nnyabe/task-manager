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
    public static List<TaskModel> heapSortByDescription(List<TaskModel> tasks) {
        List<TaskModel> sortedTasks = new ArrayList<>(tasks);
        int n = sortedTasks.size();

        // Build a max heap based on description
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(sortedTasks, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap the current root (largest) with the last element
            TaskModel temp = sortedTasks.get(0);
            sortedTasks.set(0, sortedTasks.get(i));
            sortedTasks.set(i, temp);

            // Call max heapify on the reduced heap
            heapify(sortedTasks, i, 0);
        }

        return sortedTasks;
    }

    // Heapify a subtree rooted at index i
    private static void heapify(List<TaskModel> tasks, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child index
        int right = 2 * i + 2; // Right child index

        // Check if left child exists and is greater than the root
        if (left < n) {
            String leftDescription = tasks.get(left).getDescription();
            String largestDescription = tasks.get(largest).getDescription();

            // Compare descriptions, treating null as the lowest value
            if ((leftDescription != null && (largestDescription == null || leftDescription.compareTo(largestDescription) > 0)) ||
                    (largestDescription == null && leftDescription != null)) {
                largest = left;
            }
        }

        // Check if right child exists and is greater than the current largest
        if (right < n) {
            String rightDescription = tasks.get(right).getDescription();
            String largestDescription = tasks.get(largest).getDescription();

            if ((rightDescription != null && (largestDescription == null || rightDescription.compareTo(largestDescription) > 0)) ||
                    (largestDescription == null && rightDescription != null)) {
                largest = right;
            }
        }

        // If the largest is not the root, swap them and recursively heapify the affected sub-tree
        if (largest != i) {
            TaskModel swap = tasks.get(i);
            tasks.set(i, tasks.get(largest));
            tasks.set(largest, swap);

            // Recursively heapify the affected sub-tree
            heapify(tasks, n, largest);
        }
    }
}
