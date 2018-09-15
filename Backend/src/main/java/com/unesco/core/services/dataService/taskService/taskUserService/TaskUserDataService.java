package com.unesco.core.services.dataService.taskService.taskUserService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.repositories.task.TaskRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskUserDataService implements ITaskUserDataService
{
   @Autowired
   private TaskRepository _taskRepository;
   @Autowired
   private IMapperService _mapperService;
}
