package com.sakshi.ToDoListApplication;

import com.sakshi.ToDoListApplication.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

     @Autowired
    private MyUserDetailsService userDetailsService;

    @Override// for authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        //auth.userDetailsService is predefined, userDetailsService is used to retrieve user related data
        //userDetailsService inside the bracket is we autowired
    }

    @Override// for authorization
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/addTask","/viewUsers","/*/delete","/tasksList","/updateTask/*","/adminViewSubTask/*","/updateSubTask/*","/addSubTask","/adminViewTask").hasRole("ADMIN")
                .antMatchers("/viewProfile","/updateProfile","/completedUserTask","/viewTasks","/updateSubTaskStatus/*","/viewSubTasks/*").hasRole("USER")
                .antMatchers("/").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/success")
                .usernameParameter("userName")
                .passwordParameter("password");

    }

    @Bean
    //it saves the password without encoding, not safe
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();

    }

}
