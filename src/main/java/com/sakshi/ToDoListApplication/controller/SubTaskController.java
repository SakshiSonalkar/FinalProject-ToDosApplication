package com.sakshi.ToDoListApplication.controller;

import com.sakshi.ToDoListApplication.entity.SubTask;
import com.sakshi.ToDoListApplication.entity.ToDo;
import com.sakshi.ToDoListApplication.service.SubTaskService;
import com.sakshi.ToDoListApplication.service.ToDoService;
import com.sakshi.ToDoListApplication.service.UserService;
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
public class SubTaskController {
   @Autowired
    private ToDoService service;

   @Autowired
   private UserService userService;

   @Autowired
    private SubTaskService subTaskService;

   @RequestMapping("/adminViewSubTask/{taskNumber}")
   public String adminViewSubTask(Principal principal,@PathVariable String taskNumber,Model model){
       if (principal != null) {
           String username = principal.getName();
           model.addAttribute("userName", username);
       }
       model.addAttribute("subTasks",subTaskService.subTaskDetails(taskNumber));
       ToDo toDo = service.getById(taskNumber);
       model.addAttribute("taskName", toDo.getTaskName());
       return "adminViewSubTask";
   }

    @RequestMapping("/viewSubTasks/{taskNumber}")
    public String viewSubTasks(Principal principal,@PathVariable String taskNumber,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("subTasks",subTaskService.subTaskDetails(taskNumber));
        ToDo toDo = service.getById(taskNumber);
        model.addAttribute("taskName", toDo.getTaskName());
        return "viewSubTasks";
    }

    @RequestMapping("/updateSubTask/{id}")
    public String updateSubTaskPage(Principal principal,@PathVariable Long id, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("users",userService.userList());
        model.addAttribute("subTask", subTaskService.getById(id));
        return "updateSubTask";

    }

    @PostMapping("/updateSubTask/{id}")
    public String updateSubTask(Principal principal, @PathVariable Long id, Model model, HttpServletRequest request){
        SubTask subTask= subTaskService.getById(id);
        String description= request.getParameter("description");
        String name=request.getParameter("name");
        String email = request.getParameter("email");
        String status=request.getParameter("status");
        Date dueDate= Date.valueOf(request.getParameter("dueDate"));
        subTask.setDescription(description);
        subTask.setName(name);
        subTask.setEmail(email);
        subTask.setStatus(status);
        subTask.setDueDate(dueDate);
        subTaskService.saveSubTask(subTask);
        return "redirect:/adminViewSubTask/"+ subTask.getToDo().getTaskNumber();
    }

    @RequestMapping("/updateSubTaskStatus/{id}")
    public String updateSubTaskStatusPage(Principal principal,@PathVariable Long id, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("subTask", subTaskService.getById(id));
        return "updateSubTaskStatus";
    }

    @PostMapping("/updateSubTaskStatus/{id}")
    public String updateSubTaskStatus(Principal principal, @PathVariable Long id, Model model, HttpServletRequest request){
        SubTask subTask= subTaskService.getById(id);
        String status=request.getParameter("status");
        subTask.setStatus(status);
        subTaskService.saveSubTask(subTask);
        return "redirect:/viewSubTasks/"+ subTask.getToDo().getTaskNumber();
    }

//    @GetMapping("/adminViewSubTask")
//    public String afterDelete(Principal principal,Model model){
//        if (principal != null) {
//            String username = principal.getName();
//            model.addAttribute("username", username);
//        }
//        return "adminViewSubTask";
//    }

    @RequestMapping("/{id}/delete")
    public String deleteAdminSubTask(@PathVariable Long id, Principal principal, Model model){

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        SubTask subTask = subTaskService.getById(id);
        String taskNumber = subTask.getToDo().getTaskNumber();
        if (subTaskService.existsById(id)) {
            subTaskService.deleteById(id);
        }
        return "redirect:/adminViewSubTask/"+ taskNumber;
    }

    @RequestMapping("/completedUserTask")
    public String completedUserTask(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("subTasks",subTaskService.userCompletedTasks(principal.getName(),"Completed"));
        return "completedUserTask";
    }

    @RequestMapping("/incomplete")
    public String pendingUserTask(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("subTasks",subTaskService.userPendingTasks(principal.getName(),"Not Completed"));
        return "incomplete";
    }

//    @RequestMapping("/completedUserTask/{id}")
//    public String completedUserTask(Principal principal,@PathVariable Long id,Model model){
//        if (principal != null) {
//            String username = principal.getName();
//            model.addAttribute("userName", username);
//        }
//        SubTask subTask = subTaskService.getById(id);
//        model.addAttribute("subTasks", subTaskService.userCompletedTasks(subTask.getEmail(), "Completed"));
//        return "redirect:/completedUserTask/" + subTask.getId();
//    }
     @RequestMapping("/done")
     public String done(Principal principal,Model model){
         if (principal != null) {
             String username = principal.getName();
             model.addAttribute("userName", username);
         }

         model.addAttribute("subTasks",subTaskService.completedTasks("Completed"));
         return "done";
    }

    @RequestMapping("/notDone")
    public String notDone(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("subTasks",subTaskService.pendingTasks("Not Completed"));
        return "notDone";
    }

}
