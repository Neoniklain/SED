package com.unesco.core.controllerWeb;

import com.unesco.core.controller.AccountController;
import com.unesco.core.controller.NewsController;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.RoleType;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.repositories.news.NewsRepository;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.news.newsService.INewsDataService;
import com.unesco.core.utils.StatusTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerWebTest extends Assert {

    @Autowired
    private AccountController accountController;
    @Autowired
    private IRoleDataService roleDataService;
    @Autowired
    private IUserDataService userDataService;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsController newsController;
    @Autowired
    private INewsDataService newsDataService;

    private UserDTO testUser = new UserDTO();
    private NewsDTO news = new NewsDTO();

    @Before
    public void setUp() throws Exception {

        testUser = getTestUser();
        // Создание преподавателя - профессора
        List<RoleDTO> roles = new ArrayList<>();
        roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
        testUser.setRoles(roles);
        accountController.Registration(testUser);
        testUser = userDataService.GetByUsername(testUser.getUsername());

        Date day = new Date();

        news.setDate(day);
        news.setAuthor(testUser);
        news.setContent(Math.random()+"");
        news.setHeader(Math.random()+"");
        news.setImage("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
        news.setTags("1, 2, 3");
        news = newsDataService.Save(news);
    }

    @After
    public void tearDown() throws Exception {
        // Удаление новости
        newsDataService.Delete(news.getId());
        // Удаление пользователя
        if(userDataService.GetByUsername(testUser.getUsername())!=null)
            userDataService.Delete(testUser.getId());
    }

    @Test
    public void getAllNews() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = newsController.GetAllNews();
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getLast() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = newsController.GetLast();
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void get() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = newsController.Get(news.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void Save() {
        ResponseStatusDTO resp = new ResponseStatusDTO();
        NewsDTO newsT = new NewsDTO();
        try{
            newsT.setContent("Тестирование");
            newsT.setHeader("Тестирование");
            newsT.setImage("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
            newsT.setTags("1, 2, 3");
            resp = newsController.Save(testUser, newsT);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        newsRepository.delete(newsRepository.findByHeader(newsT.getHeader()));
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    UserDTO getTestUser() {
        UserDTO testUser = new UserDTO();
        testUser.setPassword("12345");
        List<RoleDTO> roles = new ArrayList<>();
        roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
        testUser.setRoles(roles);
        testUser.setEmail(Math.random()+"@mail.ru");
        testUser.setPhoto("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
        testUser.setUserFIO("TEst User Testovich");
        testUser.setUsername(Math.random()+"");
        return testUser;
    }
}