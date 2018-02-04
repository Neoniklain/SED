package com.unesco.core.controller;

import com.unesco.core.entities.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TempRestController {

    //Тестовый метод для отправки данных View
    @RequestMapping(value = "/temp")
    public User temp() {
        User temp = new User("Test", "Test@Test.Test", "****");
        return temp;
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }


    //Тестовый метод для получения данных из View
    @RequestMapping(value = "/set")
    public User set(@RequestBody User user) {
        User temp = new User(user.getUsername(), user.getEmail(), user.getPassword());
        return temp;
        //Нужно сделать шаблон, как у меня на работе.
        //Класс, с успешным или неуспешным исполнением, сообщением, и данными, если нужно
        //Но это потом
    }
}