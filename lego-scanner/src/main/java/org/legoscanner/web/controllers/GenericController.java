package org.legoscanner.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class GenericController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("items", Arrays.asList("item1", "item2"));
        System.out.println("index");
        return mv;
    }
}
