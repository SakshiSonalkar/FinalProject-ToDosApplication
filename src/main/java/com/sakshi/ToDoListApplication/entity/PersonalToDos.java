package com.sakshi.ToDoListApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class PersonalToDos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private String toDoDescription;
    @Column(nullable=false)
    private String toDoStatus;
    @Column(nullable=false)
    private Date date;
    @JsonIgnore
    @ManyToOne
    private User user;

    public PersonalToDos(){
    }

    public PersonalToDos(String title, String toDoDescription, String toDoStatus, Date date){
        this.title = title;
        this.toDoDescription= toDoDescription;
        this.toDoStatus=toDoStatus;
        this.date=date;
    }

    public String getToDoDescription() {
        return toDoDescription;
    }

    public String getToDoStatus(){
        return toDoStatus;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setToDoStatus(String toDoStatus) {
        this.toDoStatus=toDoStatus;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setToDoDescription(String toDoDescription) {
        this.toDoDescription = toDoDescription;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
