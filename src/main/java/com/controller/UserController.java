package com.controller;

import com.model.User;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ModelAndView formLogin(){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(@Validated @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors() || !userService.findUserByEmail(user.getEmail()).isPresent()){
            return new ModelAndView("/login");
        }
        else {
           return new ModelAndView("/home");
        }
    }
    @GetMapping("/signup")
    public ModelAndView formSignup(){
        ModelAndView modelAndView = new ModelAndView("/signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/signup")
    public ModelAndView signup(@Validated @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("/signup");
        }
        userService.save(user);
        return new ModelAndView("redirect:/login");
    }
}
