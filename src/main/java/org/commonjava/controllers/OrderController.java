package org.commonjava.controllers;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping( method = RequestMethod.GET, value = "/index" )
    public String index() {
        return "Greetings from Spring Boot!";
    }

}