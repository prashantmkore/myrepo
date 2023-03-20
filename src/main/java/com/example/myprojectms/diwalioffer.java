package com.example.myprojectms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class diwalioffer {
    @GetMapping("/diwalioffer")
    public String getData(){
        return "We have Big bellion day for you this diwali, Enjoy your shopping";
    }
}

Test