package com.unesco.core.controller;


import com.unesco.core.models.TaskDescriptionModel;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.taskDataService.TaskDescriptionDataService;
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
    private TaskDescriptionDataService _IssueDataService;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;

    @GetMapping(value = "/list")
    public Iterable<TaskDescriptionModel> GetList() {
        UserModel user = new UserModel(_CustomUserDetailsService.getUserDetails());
        Iterable<TaskDescriptionModel> result =  new ArrayList<>();
        List<String> role = new ArrayList<RoleModel>(user.getRoles())
                .stream()
                .map(RoleModel::getRoleName)
                .collect(Collectors.toList());

        if(role.contains("ADMIN")) {
            result = _IssueDataService.getAllIssues();
        }
        else if(role.contains("MANAGER")) {
            result = _IssueDataService.getIssueByCreator(user.getId());
        }
        else {
            result = _IssueDataService.getIssuesByCollaborator(user.getId());
        }
        return result;
    }

    @RequestMapping(value = "/create")
    public String Create(@RequestBody TaskDescriptionModel newIssue) {
        newIssue.setCreator(new UserModel(_CustomUserDetailsService.getUserDetails()));
        _IssueDataService.createNewIssue(newIssue);
        return JSONResponseStatus.OK;
    }

    @RequestMapping(value = "/get/{id}")
    public TaskDescriptionModel Get(@PathVariable("id") long id) {
        return _IssueDataService.getIssueById(id);
    }

    @RequestMapping(value = "/update")
    public TaskDescriptionModel Update(@RequestBody TaskDescriptionModel newIssue) {
        _IssueDataService.updateIssue(newIssue);
        return newIssue;
    }

    @RequestMapping(value = "/delete/{id}")
    public String Delete(@PathVariable("id") long id) {
        _IssueDataService.deleteIssue(id);
        return JSONResponseStatus.OK;
    }
}
