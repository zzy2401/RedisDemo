# Spring Boot整合Redis的操作示例
### 简介
一个基于Spring Boot的Redis操作示例，包含基本的增删改查功能。
### 功能
- 设置和获取用户信息
- 字符串操作
- 列表操作
- 集合操作
- 有序集合操作
- 哈希表操作
### 接口
- `/test/setUser`：设置用户信息（POST）
- `/test/getUser/{key}`：获取用户信息（GET）
- `/test/deletUser/{key}`：删除用户信息（GET）
- `/test/setString`：设置字符串（GET）
- `/test/setList`：设置列表（GET）
- `/test/setSet`：设置集合（GET）
- `/test/setZSet`：设置有序集合（GET）
- `/test/setHash`：设置哈希表（GET）

### 环境要求

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 功能实现

```java
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private RedisTemplate redisTemplate;
    @PostMapping("/setUser")
    public void set(@RequestBody User user) {
        redisTemplate.opsForValue().set(user.getName(), user);
        // Outputs: User object is set in Redis with key as user's name
    }
    @GetMapping("/getUser/{key}")
    public void get(@PathVariable String key) {
        System.out.println(redisTemplate.opsForValue().get(key));
        // Outputs: User object retrieved from Redis with provided key
    }
    @GetMapping("/deletUser/{key}")
    public void delete(@PathVariable String key) {
        redisTemplate.delete(key);
        System.out.println(redisTemplate.hasKey(key));
        // Outputs: true if key was deleted, false if key did not exist
    }
    @GetMapping("/setString")
    public void setString() {
        redisTemplate.opsForValue().set("str","Hello World!");
        System.out.println(redisTemplate.opsForValue().get("str"));
        // Outputs: Hello World!
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
        // Outputs: [5, 4, 3, 2, 1]
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
        // Outputs: [1, 2, 3, 4, 5]
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
        // Outputs: [1, 2, 3, 4, 5, 6, 7, 8, 9] with scores
    }
    @GetMapping("/setHash")
    public void setHash() {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        ops.put("hashmap", "key1", "value1");
        ops.put("hashmap", "key2", "value2");
        ops.put("hashmap", "key3", "value3");

        System.out.println(ops.entries("hashmap"));
        // Outputs: {key1=value1, key2=value2, key3=value3}

        System.out.println("Value of 'key1': " + ops.get("hashmap", "key1"));
        // Outputs: Value of 'key1': value1
        System.out.println("Value of 'key2': " + ops.get("hashmap", "key2"));
        // Outputs: Value of 'key2': value2
        System.out.println("Value of 'key3': " + ops.get("hashmap", "key3"));
        // Outputs: Value of 'key3': value3
    }
    
}


```

