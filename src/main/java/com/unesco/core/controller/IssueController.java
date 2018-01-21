package com.unesco.core.controller;


import com.unesco.core.entities.Issue;
import com.unesco.core.entities.News;
import com.unesco.core.entities.User;
import com.unesco.core.repositories.IssueRepository;
import com.unesco.core.repositories.UserRepository;
import com.unesco.core.srvices.CustomUserDetails;
import com.unesco.core.srvices.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/issue")
public class IssueController {

    @Autowired
    private UserRepository _UserRepository;
    @Autowired
    private IssueRepository _IssuesRepository;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping(value = "/list")
    public Iterable<Issue> GetList() {
        User user = _UserRepository.findByUsername(_CustomUserDetailsService.getUserDetails().getUsername());
        Iterable<Issue> result = null;
        switch (user.getRole().getRoleName()) {
            case "ADMIN":
                result = _IssuesRepository.findAll();
            break;
            case "MANAGER":
                result = _IssuesRepository.findByCreator(user.getId());
            break;
            case "USER":

                break;
        }
        return result;
    }

    @RequestMapping(value = "/create")
    public String Create(@RequestBody String issueName) {
        Issue issue = new Issue();
        issue.setName(issueName);
        User creator = _UserRepository.findByUsername(_CustomUserDetailsService.getUserDetails().getUsername());
        issue.setCreator(creator);
        _IssuesRepository.save(issue);
        return "OK";
    }

    @RequestMapping(value = "/get/{id}")
    public Issue Get(@PathVariable("id") long id) {
        Issue issue = _IssuesRepository.findById(id);
        return issue;
    }

    @RequestMapping(value = "/delete/{id}")
    public String Delete(@PathVariable("id") long id) {
        _IssuesRepository.delete(id);
        return "OK";
    }
}
