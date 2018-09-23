package com.unesco.core.controllerWeb;

import com.unesco.core.controller.NewsController;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IUserService userService;

    @GetMapping("/all")
    public ResponseStatusDTO getAllNews() {
        return newsController.getAllNews();
    }

    @GetMapping("/last")
    public ResponseStatusDTO getLast() {
        return newsController.getLast();
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseStatusDTO get(@PathVariable("id") long id) {
        return newsController.get(id);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseStatusDTO delete(@PathVariable("id") long id) {
        return newsController.delete(id);
    }

    @RequestMapping(value = "/save")
    public ResponseStatusDTO save(@RequestBody NewsDTO news) {
        UserDTO user = userService.getCurrentUser();
        return newsController.save(user, news);
    }
}
