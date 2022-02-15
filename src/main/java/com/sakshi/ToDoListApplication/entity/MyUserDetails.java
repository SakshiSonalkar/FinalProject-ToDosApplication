package com.sakshi.ToDoListApplication.entity;

import com.sakshi.ToDoListApplication.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
     private final String username;
     private final String password;
     private final String role;
     private final Boolean active;
    /*
         User
         --id
         --username
         --password
         --name,gender,age(personal details)
         --role

     */

    public MyUserDetails(User user){
        username=user.getUserName();
        password=user.getPassword();
        role =user.getRole();
        active=user.getActive();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//for roles, collections is for one user can have multiple roles
        return List.of(new SimpleGrantedAuthority(role));
        //for multiple roles, new SimpleGrantedAuthority(role1), new SimpleGrantedAuthority(role2),...
    }



    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {//to check if active or not
        return active;
    }

}
