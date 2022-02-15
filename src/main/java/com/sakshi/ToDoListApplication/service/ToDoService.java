package com.sakshi.ToDoListApplication.service;

import com.sakshi.ToDoListApplication.entity.ToDo;
import com.sakshi.ToDoListApplication.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repo;
    public void createToDo(ToDo toDo){
        repo.save(toDo);
    }

    public ToDo getById(String id){
        return repo.getById(id);
    }

    public List<ToDo> listAllToDos() {
        return repo.findAll();
    }

    public List<ToDo> listAllCompletedToDos(String taskStatus) {
        if(taskStatus=="Completed"){
            return repo.findByTaskStatus(taskStatus);
        }

        else{
            return null;
        }
    }

    public void deleteByToDoId(String taskNumber) {
        repo.deleteById(taskNumber);
    }

}
