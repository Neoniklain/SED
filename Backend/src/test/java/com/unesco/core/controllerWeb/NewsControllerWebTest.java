package com.unesco.core.controllerWeb;

import com.unesco.core.controller.NewsController;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.news.NewsDTO;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.news.newsService.INewsDataService;
import com.unesco.core.utils.StatusTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerWebTest {

    @Autowired
    private NewsController newsController;
    @Autowired
    private INewsDataService newsDataService;
    @Autowired
    private IUserDataService userDataService;

    @Test
    public void getAllNews() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            newsController.GetAllNews();
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getLast() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            newsController.GetLast();
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void get() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            if(newsDataService.GetAll().size() > 0) {
                newsController.Get(newsDataService.GetAll().get(0).getId());
            }
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void SaveAndDelete() {
        ResponseStatusDTO resp = new ResponseStatusDTO();
        try{
            NewsDTO news = new NewsDTO();
            Date day = new Date();
            UserDTO user = userDataService.GetAll().get(0);

            news.setDate(day);
            news.setAuthor(user);
            news.setContent("Тестирование");
            news.setHeader("Тестирование");
            news.setImage("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
            news.setTags("1, 2, 3");
            news = newsDataService.Save(news);
            newsDataService.Delete(news.getId());
            resp =  new ResponseStatusDTO(StatusTypes.OK);

        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
        }
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }
}