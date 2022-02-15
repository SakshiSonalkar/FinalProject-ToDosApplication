package com.sakshi.ToDoListApplication.repository;

import com.sakshi.ToDoListApplication.entity.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo,String> {
    public List<ToDo> findByTaskStatus(String taskStatus);
//    Page<ToDo> findPaginated(int pageNo, int pageSize);
}
