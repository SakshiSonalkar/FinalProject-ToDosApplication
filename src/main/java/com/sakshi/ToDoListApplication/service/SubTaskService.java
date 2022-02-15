package com.sakshi.ToDoListApplication.service;


import com.sakshi.ToDoListApplication.entity.SubTask;
import com.sakshi.ToDoListApplication.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {
    @Autowired
    private SubTaskRepository repo;

    public void saveSubTask(SubTask subTask){
        repo.save(subTask);
    }

    public List<SubTask> subTaskDetails(String taskNumber){
        return repo.findByToDosTaskNumber(taskNumber);
    }



    public List<SubTask> userCompletedTasks(String email,String status){
        if(status=="Completed"){
            return repo.findByEmailAndStatus(email,status);
        }

        else
            return null;
    }

    public List<SubTask> userPendingTasks(String email,String status){
        if(status=="Not Completed"){
            return repo.findByEmailAndStatus(email,status);
        }

        else
            return null;
    }

    public List<SubTask> completedTasks(String status){
        if(status=="Completed"){
            return repo.findByStatus(status);
        }

        else
            return null;
    }

    public List<SubTask> pendingTasks(String status){
        if(status=="Not Completed"){
            return repo.findByStatus(status);
        }

        else
            return null;
    }

    public SubTask findSubTaskByTaskNumber(String taskNumber){
        return repo.getByToDosTaskNumber(taskNumber);
    }

    public Boolean existsById(Long id){
        return repo.existsById(id);
    }

    public Boolean existsByToDo(String taskNumber){
        return repo.existsByToDosTaskNumber(taskNumber);
    }

    public SubTask getById(Long id){
        return repo.getById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
