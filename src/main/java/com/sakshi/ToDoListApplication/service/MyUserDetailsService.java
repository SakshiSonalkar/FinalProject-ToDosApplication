package com.sakshi.ToDoListApplication.service;



import com.sakshi.ToDoListApplication.entity.MyUserDetails;
import com.sakshi.ToDoListApplication.entity.User;

import com.sakshi.ToDoListApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService service;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findById(username);
        //UserDetails is another entity spring knows, predefined in spring, we don't have to create
        return new MyUserDetails(user);

    }



}
