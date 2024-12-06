package com.task.manager.dto;

public class TaskDTO {

    private int id;
    private String title;
    private String url;

    public TaskDTO() {

    }
    public TaskDTO(String url){
        this.url = url;
    }
    public TaskDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public TaskDTO(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

