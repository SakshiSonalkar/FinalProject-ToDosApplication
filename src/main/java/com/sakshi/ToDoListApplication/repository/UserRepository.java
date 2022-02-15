package com.sakshi.ToDoListApplication.repository;

import com.sakshi.ToDoListApplication.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    public List<User> findListUserByUserName(String username);
//    public Page<User> findPaginated(int pageNo, int pageSize);
}
