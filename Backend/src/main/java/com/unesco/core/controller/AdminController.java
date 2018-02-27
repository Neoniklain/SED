package com.unesco.core.controller;

import com.unesco.core.ViewModel.DisciplineViewModel;
import com.unesco.core.ViewModel.FilterQuery;
import com.unesco.core.ViewModel.PageResult;
import com.unesco.core.ViewModel.UserViewModel;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.User;
import com.unesco.core.repositories.DisciplineRepository;
import com.unesco.core.repositories.NewsRepository;
import com.unesco.core.repositories.UserRepository;
import com.unesco.core.srvices.CustomUserDetailsService;
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
   public PageResult<UserViewModel> GetUserList(@RequestBody FilterQuery filter) {
      List<UserViewModel> usersViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : 10;
      Page<User> page = _UserRepository.findAll(new PageRequest(filter.getFirst()/rows, rows));
      for (User u: page.getContent()) {
         usersViewModel.add(new UserViewModel(u));
      }
      PageResult<UserViewModel> result = new PageResult<UserViewModel>(usersViewModel, _UserRepository.count());
      return result;
   }

   @RequestMapping(value = "page/disciplines")
   public PageResult<DisciplineViewModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      List<DisciplineViewModel> disciplineViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : 10;
      Page<Discipline> page = _DisciplineRepository.findAll(new PageRequest(filter.getFirst()/rows, rows));
      for (Discipline d: page.getContent()) {
         disciplineViewModel.add(new DisciplineViewModel(d));
      }
      PageResult<DisciplineViewModel> result = new PageResult<DisciplineViewModel>(disciplineViewModel, _DisciplineRepository.count());
      return result;
   }
}
