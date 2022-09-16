package edu.sru.thangiah.example3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.thangiah.example3.domain.Users;
import edu.sru.thangiah.example3.repository.UserRepository;

/*
 * CRUD functionality to the web application can be added through a basic web tier. The UserController
 * class is used to handling GET and POST HTTP requests. The requests are mapped to methods in the UserController.
 */

@Controller
public class UserController {
	//set up a UserRepositoty variable
	private UserRepository userRepository;
    
	//create an UserRepository instance - instantiation (new) is done by Spring
    public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-user HTML, to add a user
	@RequestMapping({"/signup"})
    public String showSignUpForm(Users user) {
        return "add-user";
    }
    
	//Mapping for the /signup URL - to add a user
    @RequestMapping({"/adduser"})
    public String addUser(@Validated Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(users);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/user URL to edit a user 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Users user = userRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("user", user);
        return "update-user";
    }
    
    //Mapping for the /update/id URL to update a user 
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Validated Users user, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
            
        userRepository.save(user);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a user     
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Users user = userRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }
}
