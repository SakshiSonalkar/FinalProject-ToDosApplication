package com.sakshi.ToDoListApplication.repository;

import com.sakshi.ToDoListApplication.entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask,Long> {
    public List<SubTask> findByToDosTaskNumber(String taskNumber);
    public List<SubTask> findByEmailAndStatus(String email,String status);
    public List<SubTask> findByStatus(String status);
    public SubTask getByToDosTaskNumber(String taskNumber);
    public void deleteByToDosTaskNumber(String taskNumber);
    public Boolean existsByToDosTaskNumber(String taskNumber);
}

