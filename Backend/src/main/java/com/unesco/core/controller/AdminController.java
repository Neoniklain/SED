package com.unesco.core.controller;

import com.unesco.core.models.DisciplineModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.account.User;
import com.unesco.core.repositories.plan.DisciplineRepository;
import com.unesco.core.repositories.news.NewsRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/admin")
public class AdminController {

   @Autowired
   private UserRepository _UserRepository;
   @Autowired
   private NewsRepository _NewsRepository;
   @Autowired
   private DisciplineRepository _DisciplineRepository;
   @Autowired
   private CustomUserDetailsService _CustomUserDetailsService;

   @RequestMapping(value = "page/users")
   public PageResult<UserModel> GetUserList(@RequestBody FilterQuery filter) {
      List<UserModel> usersViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : 10;
      Page<User> page = _UserRepository.findAll(new PageRequest(filter.getFirst()/rows, rows));
      for (User u: page.getContent()) {
         usersViewModel.add(new UserModel(u));
      }
      PageResult<UserModel> result = new PageResult<UserModel>(usersViewModel, _UserRepository.count());
      return result;
   }

   @RequestMapping(value = "page/disciplines")
   public PageResult<DisciplineModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      List<DisciplineModel> disciplineModel = new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : 10;
      Page<Discipline> page = _DisciplineRepository.findAll(new PageRequest(filter.getFirst()/rows, rows));
      for (Discipline d: page.getContent()) {
         disciplineModel.add(new DisciplineModel(d));
      }
      PageResult<DisciplineModel> result = new PageResult<DisciplineModel>(disciplineModel, _DisciplineRepository.count());
      return result;
   }
}
