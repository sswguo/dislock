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
import java.util.Optional;
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
            Item item = itemRepository.findById( Long.valueOf( 1 ) ).orElse( null );

            if ( item == null )
            {
                item = new Item( Long.valueOf( 1 ), "football", Integer.valueOf( 10 ), "World Cup Ball!" );
                itemRepository.save( item );
            }

            item = itemRepository.findById( Long.valueOf( 1 ) ).orElse( null );
            item.setCount( item.getCount().intValue() - 1 );
            itemRepository.save( item );

            item = itemRepository.findById( Long.valueOf( 1 ) ).orElse( null );
            System.out.println( Thread.currentThread().getName() + "/Item: " + item.toString() );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
        return itemRepository.findAll();
    }

}