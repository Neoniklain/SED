package com.unesco.core.controller;


import com.unesco.core.ViewModel.JSONResponseStatus;
import com.unesco.core.entities.Issue;
import com.unesco.core.entities.Role;
import com.unesco.core.entities.User;
import com.unesco.core.repositories.IssueRepository;
import com.unesco.core.repositories.UserRepository;
import com.unesco.core.srvices.CustomUserDetailsService;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
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
        List<String> role = new ArrayList<Role>(user.getRoles())
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        if(role.contains("ADMIN"))
            result = _IssuesRepository.findAll();
        if(role.contains("MANAGER"))
            result = _IssuesRepository.findByCreator(user.getId());
        /*if(role.contains(new Role("USER")))
            result = _IssuesRepository.findAll();*/
        return result;
    }

    @RequestMapping(value = "/create")
    public String Create(@RequestBody Issue newIssue) {
        Issue issue = new Issue();
        issue.setName(newIssue.getName());
        User creator = _UserRepository.findByUsername(_CustomUserDetailsService.getUserDetails().getUsername());
        issue.setCreator(creator);
        issue.setCollaborators(newIssue.getCollaborators());
        _IssuesRepository.save(issue);
        return JSONResponseStatus.OK;
    }

    @RequestMapping(value = "/get/{id}")
    public Issue Get(@PathVariable("id") long id) {
        Issue issue = _IssuesRepository.findById(id);
        return issue;
    }

    @RequestMapping(value = "/update")
    public String Update(@RequestBody Issue newIssue) {
        Issue issue = _IssuesRepository.findById(newIssue.getId());
        issue.setName(newIssue.getName());
        User creator = _UserRepository.findByUsername(_CustomUserDetailsService.getUserDetails().getUsername());
        issue.setCreator(creator);
        issue.setCollaborators(newIssue.getCollaborators());
        _IssuesRepository.save(issue);
        return JSONResponseStatus.OK;
    }

    @RequestMapping(value = "/delete/{id}")
    public String Delete(@PathVariable("id") long id) {
        _IssuesRepository.delete(id);
        return JSONResponseStatus.OK;
    }
}
