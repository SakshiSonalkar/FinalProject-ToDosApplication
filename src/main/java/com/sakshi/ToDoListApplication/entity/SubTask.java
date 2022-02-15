package com.sakshi.ToDoListApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class SubTask {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String status;
    @Column(nullable=false)
    private Date dueDate;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    private ToDo toDos;

    public SubTask(){}
    public SubTask(String description,String name,String email,String status,Date dueDate){
        this.description=description;
        this.name=name;
        this.email=email;
        this.status=status;
        this.dueDate=dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ToDo getToDo() {
        return toDos;
    }

    public void setToDo(ToDo toDo) {
        this.toDos = toDo;
    }
}
