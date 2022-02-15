package com.sakshi.ToDoListApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    private String userName;
    private String name;
    private String address;
    @Column(nullable = false,unique = true)
    private Long phoneNumber;
    @Column(nullable=false,unique = true)
    private String password;
    private String role="ROLE_USER";
    private Boolean active=true;
    @OneToMany(mappedBy = "user")
    private List<PersonalToDos> personalToDos;


    public User() {
    }

    public User(String userName, String name, String address,Long phoneNumber, String password,String role) {
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.phoneNumber=phoneNumber;
        this.password = password;
        this.role=role;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public Long getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber){this.phoneNumber=phoneNumber;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<PersonalToDos> getPersonalToDos() {
        return personalToDos;
    }

    public void setPersonalToDos(List<PersonalToDos> personalToDos) {
        this.personalToDos = personalToDos;
    }
}
