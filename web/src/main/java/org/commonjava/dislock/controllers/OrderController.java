package org.commonjava.dislock.controllers;

import org.commonjava.dislock.model.Item;
import org.commonjava.dislock.model.ItemRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping( method = RequestMethod.GET, value = "/index" )
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping( method = RequestMethod.GET, value = "/items" )
    public List<Item> getAllItems()
    {
        RLock lock = redissonClient.getLock( "Mylock" );
        lock.lock( );
        try
        {
            //TODO get entry from the db
            System.out.println( "enter into the lock part." );
        }
        catch ( Exception e )
        {
            //
        }
        finally
        {
            lock.unlock();
        }
        return itemRepository.findAll();
    }

}