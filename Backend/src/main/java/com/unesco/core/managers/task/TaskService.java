package com.unesco.core.managers.task;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.enums.TaskType;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.services.dataService.account.userService.IUserDataService;
import com.unesco.core.services.mapperService.MapperService;
import com.unesco.core.services.dataService.taskService.taskDescriptionService.ITaskDescriptionDataService;
import com.unesco.core.services.dataService.taskService.taskUserService.ITaskUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITaskService
{
   @Autowired
   MapperService _mapperService;
   @Autowired
   ITaskDescriptionDataService _taskDescriptionDataService;
   @Autowired
   ITaskUserDataService _taskUserDataService;
   @Autowired
   IUserDataService _userDataService;
   @Autowired
   IUserDataService _userService;
}
