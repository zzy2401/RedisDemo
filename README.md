# RedisDemo
## 简介
一个基于Spring Boot的Redis操作示例，包含基本的增删改查功能。
## 功能
- 设置和获取用户信息
- 字符串操作
- 列表操作
- 集合操作
- 有序集合操作
- 哈希表操作
## 接口
- `/test/setUser`：设置用户信息（POST）
- `/test/getUser/{key}`：获取用户信息（GET）
- `/test/deletUser/{key}`：删除用户信息（GET）
- `/test/setString`：设置字符串（GET）
- `/test/setList`：设置列表（GET）
- `/test/setSet`：设置集合（GET）
- `/test/setZSet`：设置有序集合（GET）
- `/test/setHash`：设置哈希表（GET）

## 环境要求

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
