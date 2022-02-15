package com.sakshi.ToDoListApplication.service;

import com.sakshi.ToDoListApplication.entity.User;
import com.sakshi.ToDoListApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> userList(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Boolean existsById(String userName){
        return userRepository.existsById(userName);
    }

    public User findById(String userName){
        return userRepository.findById(userName).get();
    }

//    public Page<User> findPaginated(int pageNo,int pageSize){
//        Pageable pageable= PageRequest.of(pageNo-1,pageSize);  //pageable interface,pageRequest class,spring data JPA accepts page no. starting as 0
//        return this.userRepository.findAll(pageable); //provides support for pagination
//    }

}
