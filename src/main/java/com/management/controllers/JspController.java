package com.management.controllers;


import com.management.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import com.management.services.CarService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspController {
    @Autowired
    CarService carService;



}
