package com.sakshi.ToDoListApplication.service;

import com.sakshi.ToDoListApplication.entity.PersonalToDos;
import com.sakshi.ToDoListApplication.entity.SubTask;
import com.sakshi.ToDoListApplication.entity.User;
import com.sakshi.ToDoListApplication.repository.PersonalToDosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalToDosService{
    @Autowired
    private PersonalToDosRepository repo;

    public void savePersonalToDos(PersonalToDos personalToDos){
        repo.save(personalToDos);
    }

    public List<PersonalToDos> personalToDosList(String userName){
        return repo.findByUserUserName(userName);
    }

    public List<PersonalToDos> completedToDosList(String userName,String status){
        if(status=="Completed")
        return repo.findByUserUserNameAndToDoStatus(userName,status);
        else
            return null;
    }
    public List<PersonalToDos> pendingToDosList(String userName,String status){
        if(status=="Not Completed")
            return repo.findByUserUserNameAndToDoStatus(userName,status);
        else
            return null;
    }
    public Boolean existsById(Long id){
        return repo.existsById(id);
    }

    public PersonalToDos getById(Long id){
        return repo.getById(id);
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }


}
