package com.sakshi.ToDoListApplication.repository;

import com.sakshi.ToDoListApplication.entity.PersonalToDos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalToDosRepository extends JpaRepository<PersonalToDos,Long> {
    public List<PersonalToDos> findByUserUserName(String userName);
    public List<PersonalToDos> findByUserUserNameAndToDoStatus(String userName,String toDoStatus);
}
