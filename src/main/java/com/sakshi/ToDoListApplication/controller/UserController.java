package com.sakshi.ToDoListApplication.controller;

import com.sakshi.ToDoListApplication.entity.User;
import com.sakshi.ToDoListApplication.service.ToDoService;
import com.sakshi.ToDoListApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ToDoService toDoService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/register")
    public String registrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registration(HttpServletRequest request,Model model){
       String userName = request.getParameter("userName");
       String name = request.getParameter("name");
       String address = request.getParameter("address");
       Long phoneNumber= Long.parseLong(request.getParameter("phoneNumber"));
       String password = request.getParameter("password");
       User user;
       if(userName.equals("admin@gmail.com")){
             user= new User(userName,name,address,phoneNumber,password,"ROLE_ADMIN");
       }else{
           user= new User(userName,name,address,phoneNumber,password,"ROLE_USER");
        }
        if(!userService.existsById(userName)){
            userService.createUser(user);
        }
        else
        {
            model.addAttribute("error","username is already taken !!!");
            return "register";
        }
        model.addAttribute("message", "Successfully registered!!!!!!");
//        return "adminHome";
        return "login";


    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }


    @GetMapping("/success")
    public String login(Principal principal,Model model){
            String userName = principal.getName();
            if (userName.equals("admin@gmail.com")) {
                return "redirect:/addTask";
            }

            return "redirect:/viewTasks";

    }


//    @PostMapping("/login")
//    public String login(HttpServletRequest req, Model model) {
//        User user;
//        String userName = req.getParameter("userName");
//        String password = req.getParameter("password");
//        if (userService.existsById("admin@gmail.com")) {
//            user = userService.findById(userName);
//            if (password.equals("admin")) {
//                model.addAttribute("userName", userName);
//                return "redirect:/addTask";
//            } else {
//                model.addAttribute("message", "Invalid credentials!!!!!!!");
//                return "login";
//            }
//
//        } else if(!userName.equals("admin@gmail.com")) {
//
//            user = userService.findById(userName);
//            if (password.equals(user.getPassword())) {
//
//                model.addAttribute("message", "Successfully logged in!!!!!!!");
//                model.addAttribute("userName", userName);
//                return "redirect:/viewTasks";
//
//            } else {
//                model.addAttribute("message", "Invalid credentials!!!!!!!");
//                return "login";
//            }
//        } else {
//            model.addAttribute("message", "Invalid credentials!!!!!!!");
//            return "login";
//        }
//
//
//    }
//



    @RequestMapping("/viewProfile")
    public String viewProfile(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
            User user = userService.findById(principal.getName());

        model.addAttribute("name",user.getName() );
        model.addAttribute("address",user.getAddress());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        return "viewProfile";
    }


    @RequestMapping("/updateProfile")
    public String updateProfile(Principal principal,Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("user", userService.findById(principal.getName()));
        return "updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(Principal principal, HttpServletRequest req, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
       User user= userService.findById(principal.getName());
        String name=req.getParameter("name");
        String address=req.getParameter("address");
        String password=req.getParameter("password");
        Long phoneNumber=Long.parseLong(req.getParameter("phoneNumber"));

        user.setName(name);
        user.setAddress(address);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        userService.createUser(user);
        return "redirect:/viewProfile";
    }

    @RequestMapping("/viewUsers")
    public String viewUsers(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("users",userService.userList());
//        return findPaginated(1,model);
        return "viewUsers";
    }


//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable(value="pageNo") int pageNo,Model model){
//        int pageSize =5;
//        Page<User> page =userService.findPaginated(pageNo,pageSize);
//        List<User> listUsers=page.getContent(); //get list of users in paginated format
//        model.addAttribute("currentPage",pageNo); //current page
//        model.addAttribute("totalPages",page.getTotalPages()); //totalpages
//        model.addAttribute("totalItems",page.getTotalElements());//total records
//        model.addAttribute("listUsers",listUsers);
//        return "viewUsers";
//    }

}
