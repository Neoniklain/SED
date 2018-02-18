package com.unesco.core.controller;

import com.unesco.core.ViewModel.DisciplineViewModel;
import com.unesco.core.ViewModel.FilterQuery;
import com.unesco.core.ViewModel.UserViewModel;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.User;
import com.unesco.core.repositories.DisciplineRepository;
import com.unesco.core.repositories.NewsRepository;
import com.unesco.core.repositories.UserRepository;
import com.unesco.core.srvices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

   @RequestMapping(value = "/users")
   public List<UserViewModel> GetUserList(@RequestBody FilterQuery filter) {
      List<UserViewModel> usersViewModel= new ArrayList<>();
      Iterable<User> users = _UserRepository.findAll();
      for (User u: users) {
         usersViewModel.add(new UserViewModel(u));
      }
      return usersViewModel;
   }

   @RequestMapping(value = "/disciplines")
   public List<DisciplineViewModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      List<DisciplineViewModel> disciplineViewModel= new ArrayList<>();
      Iterable<Discipline> discipline = _DisciplineRepository.findAll();
      for (Discipline d: discipline) {
         disciplineViewModel.add(new DisciplineViewModel(d));
      }
      return disciplineViewModel;
   }
}
