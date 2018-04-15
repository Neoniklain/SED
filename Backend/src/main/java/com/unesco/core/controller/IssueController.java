package com.unesco.core.controller;


import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.entities.workflow.Issue;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.repositories.issue.IssueRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.security.CustomUserDetailsService;
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
        Iterable<Issue> result =  new ArrayList<Issue>();
        List<String> role = new ArrayList<Role>(user.getRoles())
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        if(role.contains("ADMIN"))
            result = _IssuesRepository.findAll();
        if(role.contains("MANAGER"))
            result = _IssuesRepository.findByCreator(user.getId());
        if(role.contains("USER"))
            result = _IssuesRepository.findByCollaborators(user);
        return result;
    }

    @RequestMapping(value = "/create")
    public JSONResponseStatus Create(@RequestBody Issue newIssue) {
        Issue issue = new Issue();
        issue.setName(newIssue.getName());
        User creator = _UserRepository.findByUsername(_CustomUserDetailsService.getUserDetails().getUsername());
        issue.setCreator(creator);
        issue.setCollaborators(newIssue.getCollaborators());
        _IssuesRepository.save(issue);
        return JSONResponseStatus.OK();
    }

    @RequestMapping(value = "/get/{id}")
    public Issue Get(@PathVariable("id") long id) {
        Issue issue = _IssuesRepository.findById(id);
        return issue;
    }

    @RequestMapping(value = "/update")
    public JSONResponseStatus Update(@RequestBody Issue newIssue) {
        Issue issue = _IssuesRepository.findById(newIssue.getId());
        issue.setName(newIssue.getName());
        User creator = _UserRepository.findByUsername(_CustomUserDetailsService.getUserDetails().getUsername());
        issue.setCreator(creator);
        issue.setCollaborators(newIssue.getCollaborators());
        _IssuesRepository.save(issue);
        return JSONResponseStatus.OK();
    }

    @RequestMapping(value = "/delete/{id}")
    public JSONResponseStatus Delete(@PathVariable("id") long id) {
        _IssuesRepository.delete(id);
        return JSONResponseStatus.OK();
    }
}
