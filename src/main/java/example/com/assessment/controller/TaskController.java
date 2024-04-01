package example.com.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import example.com.assessment.entity.Task;
import example.com.assessment.entity.User;
import example.com.assessment.repository.TaskRepository;
import example.com.assessment.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

class TaskRequest {
  public String title;
}

class CompleteRequest {
  public boolean value;
}

@Controller
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/task")
  public String index() {
    return "index.html";
  }

  @GetMapping("/tasks")
  @ResponseBody
  public List<Task> getTasks() {
    List<Task> tasks = taskRepository.findByUserId((long)1);

    return tasks;
  }


  @PostMapping("/tasks")
  @ResponseBody
  public boolean createTask(@RequestBody TaskRequest taskRequest, HttpSession session) {
      try {
        long userId = (long)session.getAttribute("userId");
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
          return false;
        }

        Task task = new Task();
        task.setTitle(taskRequest.title);
        task.setUserId(userId);
    
        taskRepository.save(task);

      } catch (Exception excption) {
        return false;
      }

      return true;
  }
  
  @DeleteMapping("/tasks/{id}")
  @ResponseBody
  public boolean deleteTask(@PathVariable long id) {
    try {
      taskRepository.deleteById(id);
    } catch (Exception exception) {
      return false;
    }
    
    return true;
  }

  @PostMapping("/tasks/complete/{id}")
  @ResponseBody
  public boolean complete(@PathVariable long id, @RequestBody CompleteRequest completeRequest) {
    try {
      Optional<Task> task = taskRepository.findById(id);

      if (!task.isPresent()) {
        return false;
      }

      Task _task = task.get();
      _task.setDone(completeRequest.value);

      taskRepository.save(_task);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }
  

  @PutMapping("/tasks/{id}")
  @ResponseBody
  public boolean updateTask(@PathVariable long id, @RequestBody TaskRequest taskRequest) {
    try {
      Optional<Task> task = taskRepository.findById(id);

      if (!task.isPresent()) {
        return false;
      }

      Task _task = task.get();
      _task.setTitle(taskRequest.title);

      taskRepository.save(_task);
    } catch (Exception exception) {
      return false;
    }

    return true;
  }
}
