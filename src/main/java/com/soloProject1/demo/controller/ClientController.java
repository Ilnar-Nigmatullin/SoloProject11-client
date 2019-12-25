package com.soloProject1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ClientController {
    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView modelAndView) {
        modelAndView.setViewName("/user/home");
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/admin");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView loginUsers(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
