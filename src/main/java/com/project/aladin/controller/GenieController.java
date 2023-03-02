package com.project.aladin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenieController {

@GetMapping ("/")
public String index(Model model){
    int test1= 1;
    model.addAttribute("test", test1);
    return "page/home";
}
}
