package com.sakshi.ToDoListApplication.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name="toDos")
public class ToDo {
  @Id
  private String taskNumber;
  @Column(nullable = false)
  private String taskName;
  @Column(nullable = false)
  private String taskStatus;
  @Column(nullable = false)
  private Date startDate;
  @Column(nullable = false)
  private Date endDate;
  @OneToMany(mappedBy = "toDos",cascade=CascadeType.ALL,orphanRemoval = true)
  private List<SubTask> subTasks;



  public ToDo(){
  }
  public ToDo(String taskNumber,String taskName,String taskStatus,Date startDate,Date endDate){
    this.taskNumber = taskNumber;
    this.taskName=taskName;
    this.taskStatus=taskStatus;
    this.startDate=startDate;
    this.endDate=endDate;
  }

  public String getTaskNumber() {
    return taskNumber;
  }

  public String getTaskName() {
    return taskName;
  }

  public String getTaskStatus() {
    return taskStatus;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public List<SubTask> getSubTasks() {
    return subTasks;
  }

  public void setTaskNumber(String taskNumber) {
    this.taskNumber = taskNumber;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setSubTasks(List<SubTask> subTasks) {
    this.subTasks = subTasks;
  }

//  public ToDo removeSubTasks(SubTask subTask){
//    subTasks.remove(subTask);
//    subTask.setToDo(null);
//    return this;
//  }
}
