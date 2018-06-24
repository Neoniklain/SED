package com.unesco.core.controllerWeb;

import com.unesco.core.controller.NewsController;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.news.NewsDTO;
import com.unesco.core.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/news")
public class NewsControllerWeb {

    @Autowired
    private NewsController newsController;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping("/all")
    public ResponseStatusDTO GetAllNews() {
        return newsController.GetAllNews();
    }

    @GetMapping("/last")
    public ResponseStatusDTO GetLast() {
        return newsController.GetLast();
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseStatusDTO Get(@PathVariable("id") long id) {
        return newsController.Get(id);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatusDTO Delete(@PathVariable("id") long id) {
        return newsController.Delete(id);
    }

    @RequestMapping(value = "/save")
    public ResponseStatusDTO Save(@RequestBody NewsDTO news) {
        UserDTO user = new UserDTO(_CustomUserDetailsService.getUserDetails());
        return newsController.Save(user, news);
    }
}
