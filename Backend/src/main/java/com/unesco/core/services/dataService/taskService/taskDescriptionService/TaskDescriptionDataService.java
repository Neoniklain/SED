package com.unesco.core.services.dataService.taskService.taskDescriptionService;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.repositories.task.TaskDescriptionRepository;
import com.unesco.core.services.mapperService.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskDescriptionDataService implements ITaskDescriptionDataService
{
   @Autowired
   private MapperService _mapperService;
   @Autowired
   private TaskDescriptionRepository _taskDescriptionRepository;
}
