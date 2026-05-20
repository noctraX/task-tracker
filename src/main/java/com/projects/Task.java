package com.projects;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public Task(){};

    public Task(int id, String description, TaskStatus status){
        this.id = id;
        this.description = description;
        this.status = status;

        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    // ID get-set

    public int getId(){
        return id;
    }

    // Description get-set

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    // Status get-set

    public TaskStatus getStatus(){
        return status;
    }
    public void setStatus(TaskStatus status){
        this.status = status;
    }

    // Created-at and Update-at

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdateAt(){
        return updateAt;
    }
}
