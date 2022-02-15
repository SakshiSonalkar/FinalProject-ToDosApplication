package com.sakshi.ToDoListApplication.controller;

import com.sakshi.ToDoListApplication.entity.PersonalToDos;
import com.sakshi.ToDoListApplication.entity.SubTask;
import com.sakshi.ToDoListApplication.entity.ToDo;
import com.sakshi.ToDoListApplication.entity.User;
import com.sakshi.ToDoListApplication.service.PersonalToDosService;
import com.sakshi.ToDoListApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;

@Controller
public class PersonalToDosController {

    @Autowired
    private PersonalToDosService personalToDosService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addPersonalToDo")
    public String addPersonalToDo(Principal principal, Model model){
        if(principal!=null){
            String username= principal.getName();
            model.addAttribute("userName",username);
        }
        return "addPersonalToDo";
    }

    @PostMapping("/addPersonalToDo")
    public String addPersonalTaskDetails(Principal principal, HttpServletRequest request,Model model){
        if(principal!=null){
            String username= principal.getName();
            model.addAttribute("userName",username);
        }
        User user=userService.findById(principal.getName());
        String title= request.getParameter("title");
        String toDoDescription = request.getParameter("toDoDescription");
        String toDoStatus = request.getParameter("toDoStatus");
        Date date = Date.valueOf(request.getParameter("date"));
        PersonalToDos personalToDos = new PersonalToDos(title,toDoDescription,toDoStatus,date);
        personalToDos.setUser(user);
        personalToDosService.savePersonalToDos(personalToDos);
        model.addAttribute("message","Successfully added task!!!");
        return "redirect:/viewPersonalToDo";
    }

    @RequestMapping("/viewPersonalToDo")
    public String viewPersonalToDos(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
            model.addAttribute("personalToDos", personalToDosService.personalToDosList(principal.getName()));
        return "viewPersonalToDo";
    }
    @RequestMapping("/completeToDo")
    public String completedToDo(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("personalToDos",personalToDosService.completedToDosList(principal.getName(),"Completed"));
        return "completeToDo";
    }

    @RequestMapping("/incompleteToDo")
    public String incompleteToDos(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("personalToDos",personalToDosService.pendingToDosList(principal.getName(),"Not Completed"));
        return "incompleteToDo";
    }

    @RequestMapping("/updateToDoStatus/{id}")
    public String updateToDoStatusPage(Principal principal, @PathVariable Long id, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("personalToDos", personalToDosService.getById(id));
        return "updateToDoStatus";

    }

    @PostMapping("/updateToDoStatus/{id}")
    public String updateToDoStatus(Principal principal, @PathVariable Long id, Model model, HttpServletRequest request){
        PersonalToDos personalToDos=personalToDosService.getById(id);
        String status=request.getParameter("toDoStatus");
        personalToDos.setToDoStatus(status);
        personalToDosService.savePersonalToDos(personalToDos);
        return "redirect:/viewPersonalToDo";
    }

    @RequestMapping("/{id}/deleteToDo")
    public String deleteToDos(@PathVariable Long id, Principal principal, Model model){

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        if(personalToDosService.existsById(id)) {
            personalToDosService.deleteById(id);
        }
        return "redirect:/viewPersonalToDo";
    }




}
