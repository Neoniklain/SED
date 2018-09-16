package com.unesco.core.controller;

import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.managers.task.interfaces.ITaskManager;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskController {
    @Autowired
    private ITaskManager _TaskDataService;
    @Autowired
    private IUserService userService;

    public ResponseStatusDTO getList() {
        return result;
    }

    public ResponseStatusDTO create(TaskDescriptionDTO newTask) {
        return result;
    }

    public ResponseStatusDTO answer(TaskUserDTO item) {
        return result;
    }

    public ResponseStatusDTO changeStatus(long tu_id, int status_id) {
        return result;
    }

    public ResponseStatusDTO get(long id) {
        return result;
    }

    public ResponseStatusDTO update(TaskDescriptionDTO task) {
        return result;
    }

    public ResponseStatusDTO delete(long id) {
        return result;
    }
}
