package org.commonjava.dislock.controllers;

import org.commonjava.dislock.model.Item;
import org.commonjava.dislock.model.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    ItemRepository itemRepository;

    @RequestMapping( method = RequestMethod.GET, value = "/index" )
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping( method = RequestMethod.GET, value = "/items" )
    public List<Item> getAllItems()
    {
        System.out.println( "find all." );
        return itemRepository.findAll();
    }

}