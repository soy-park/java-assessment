package example.com.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import example.com.assessment.entity.User;
import example.com.assessment.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AuthController {
  @Autowired
  UserRepository userRepository;

  @GetMapping("/register")
  public String showRegistrationForm(
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String password,
      @RequestParam(required = false) String firstname,
      @RequestParam(required = false) String lastname,
      HttpSession session) {
    if (username != null) {
      User user = new User(username, password, firstname, lastname);

      try {
        userRepository.save(user);
      } catch (Exception excp) {

        return "register.html";
      }

      return "redirect:/login";
    }
    return "register.html";
  }

  @GetMapping("/login")
  public String showLoginForm(
    @RequestParam(required = false) String username, 
    @RequestParam(required = false) String password,
    HttpSession session
  ) {
    Optional<User> user = userRepository.findByUsername(username);

    if (!user.isPresent()) {
      return "login.html";
    }

    User _user = user.get();

    if (_user.getPassword().equals(password)) {
      session.setAttribute("userId", _user.getId());
      
      return "redirect:/";
    }
    
    return "login.html";
  }

  @PostMapping("/login")
  @ResponseBody
  public boolean processLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
    return true;
  }

  @GetMapping("/logout")
  public String getMethodName(HttpSession session) {
    session.removeAttribute("userId");

    return "redirect:/";
  }
  

  @PostMapping("/register")
  @ResponseBody
  public String processRegister(@RequestParam String username, @RequestParam String password,
      @RequestParam String firstname, @RequestParam String lastname) {
        return "";
  }

}