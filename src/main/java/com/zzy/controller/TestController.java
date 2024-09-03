package com.zzy.controller;

import com.zzy.entity.User;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/setUser")
    public void set(@RequestBody User user) {
        redisTemplate.opsForValue().set(user.getName(), user);
    }

    @GetMapping("/getUser/{key}")
    public void get(@PathVariable String key) {
        System.out.println(redisTemplate.opsForValue().get(key));
    }

    @GetMapping("/deletUser/{key}")
    public void delete(@PathVariable String key) {
        redisTemplate.delete(key);
        System.out.println(redisTemplate.hasKey(key));
    }

    @GetMapping("/setString")
    public void setString() {
        redisTemplate.opsForValue().set("str","Hello World!");
        System.out.println(redisTemplate.opsForValue().get("str"));
    }

    @GetMapping("/setList")
    public void setList() {
        ListOperations<String,String> listOps = redisTemplate.opsForList();
        listOps.leftPush("list","1");
        listOps.leftPush("list","2");
        listOps.leftPush("list","3");
        listOps.leftPush("list","4");
        listOps.leftPush("list","5");

        System.out.println(listOps.range("list", 0, -1));
    }

    @GetMapping("/setSet")
    public void setSet() {
        SetOperations<String,String> ops = redisTemplate.opsForSet();
        ops.add("set","1");
        ops.add("set","1");
        ops.add("set","1");

        ops.add("set","2");
        ops.add("set","2");

        ops.add("set","3");
        ops.add("set","4");
        ops.add("set","5");

        System.out.println(ops.members("set"));
    }

    @GetMapping("/setZSet")
    public void setZSet() {
        ZSetOperations<String,String> ops = redisTemplate.opsForZSet();
        ops.add("zset","1",1);
        ops.add("zset","3",3);
        ops.add("zset","2",2);

        ops.add("zset","4",4);
        ops.add("zset","5",5);

        ops.add("zset","6",6);
        ops.add("zset","8",6);
        ops.add("zset","7",6);
        ops.add("zset","9",6);

        System.out.println(ops.range("zset",0,-1));
    }

    @GetMapping("/setHash")
    public void setHash() {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();

        ops.put("hashmap", "key1", "value1");
        ops.put("hashmap", "key2", "value2");
        ops.put("hashmap", "key3", "value3");

        System.out.println(ops.entries("hashmap"));

        System.out.println("Value of 'key1': " + ops.get("hashmap", "key1"));
        System.out.println("Value of 'key2': " + ops.get("hashmap", "key2"));
        System.out.println("Value of 'key3': " + ops.get("hashmap", "key3"));
    }
    
}
