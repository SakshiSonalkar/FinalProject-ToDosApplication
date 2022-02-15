package com.sakshi.ToDoListApplication.controller;

import com.sakshi.ToDoListApplication.entity.SubTask;
import com.sakshi.ToDoListApplication.entity.ToDo;
import com.sakshi.ToDoListApplication.service.SubTaskService;
import com.sakshi.ToDoListApplication.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService service;

    @Autowired
    private SubTaskService subTaskService;

    @RequestMapping("/addTask")
    public String addTask(Principal principal, Model model){
        if(principal!=null){
           String username= principal.getName();
            model.addAttribute("userName",username);
        }
        return "/addTask";
    }

    @PostMapping("/addTask")
    public String addTaskDetails(Principal principal,HttpServletRequest request,Model model){
        if(principal!=null){
            String username= principal.getName();
            model.addAttribute("userName",username);
        }
        String taskNumber= request.getParameter("taskNumber");
        String taskName = request.getParameter("taskName");
        String taskStatus = request.getParameter("taskStatus");
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate= Date.valueOf(request.getParameter("endDate"));
        ToDo toDo = new ToDo(taskNumber,taskName,taskStatus,startDate,endDate);
        service.createToDo(toDo);
        model.addAttribute("message","Successfully added!!!");
        return "addSubTask";
    }

    @RequestMapping("/addSubTask")
    public String addSubTask(Principal principal, Model model){
        if(principal!=null){
            String username= principal.getName();
            model.addAttribute("userName",username);
        }
        return "/addSubTask";
    }

    @RequestMapping("/adminViewTask")
    public String adminViewTask(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("toDos",service.listAllToDos());
        return "adminViewTask";
    }

//    @RequestMapping("/adminHome")
//    public String adminHome(Principal principal,Model model){
//        if(principal!=null){
//            String username= principal.getName();
//            model.addAttribute("userName",username);
//        }
//        return "/adminHome";
//    }

    @RequestMapping("/updateTask/{id}")
    public String updateTaskPage(Principal principal,@PathVariable String id, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("toDo", service.getById(id));
        return "updateTask";
    }

    @PostMapping("/updateTask/{id}")
    public String updateTask(Principal principal, @PathVariable String id, Model model, HttpServletRequest request){
        ToDo toDo = service.getById(id);
        String taskNumber=request.getParameter("taskNumber");
        String taskName = request.getParameter("taskName");
        String taskStatus = request.getParameter("taskStatus");
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate= Date.valueOf(request.getParameter("endDate"));
        toDo.setTaskNumber(taskNumber);
        toDo.setTaskName(taskName);
        toDo.setTaskStatus(taskStatus);
        toDo.setStartDate(startDate);
        toDo.setEndDate(endDate);
        service.createToDo(toDo);
        model.addAttribute("message","Successfully updated!!!");
        return "redirect:/adminViewTask";
    }

    @RequestMapping("/viewTasks")
    public String viewTasks(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("toDos",service.listAllToDos());
        return "viewTasks";
    }


    @PostMapping("/addSubTask")
    public String addSubTaskDetails(Principal principal,HttpServletRequest request,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        String taskNumber = request.getParameter("taskNumber");
        String description= request.getParameter("description");
        String name=request.getParameter("name");
        String email = request.getParameter("email");
        String status=request.getParameter("status");
        Date dueDate= Date.valueOf(request.getParameter("dueDate"));
        SubTask subTask = new SubTask(description,name,email,status,dueDate);
        subTask.setToDo(service.getById(taskNumber));
        subTaskService.saveSubTask(subTask);
        model.addAttribute("message", "Successfully added");
        return "addSubTask";
    }

    @RequestMapping("/tasksList")
    public String tasksList(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("toDos",service.listAllCompletedToDos("Completed"));
        return "tasksList";
    }

    @RequestMapping("/{id}/deleteTask")
    public String deleteAdminTask(@PathVariable String id, Principal principal, Model model){

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        if(subTaskService.existsByToDo(id)){
            ToDo toDo = service.getById(id);
        }
        service.deleteByToDoId(id);
        return "redirect:/adminViewTask";
    }

}
