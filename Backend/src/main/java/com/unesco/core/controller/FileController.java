package com.unesco.core.controller;

import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.file.FileByteCodeModel;
import com.unesco.core.models.file.FileDescriptionModel;
import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.security.CustomUserDetailsService;
import com.unesco.core.services.file.fileByteCodeService.IFileByteCodeService;
import com.unesco.core.services.file.fileDescriptionService.IFileDescriptionService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileController {
    @Autowired
    private IFileByteCodeService _fileByteCodeService;
    @Autowired
    private CustomUserDetailsService _CustomUserDetailsService;
    @Autowired
    private IFileDescriptionService _fileDescriptionService;
    @Autowired
    private ITaskService _taskService;

    public ResponseStatus AddFileForTD(long id, MultipartFile file) {
        try
        {
            FileDescriptionModel FD = new FileDescriptionModel();
            FileByteCodeModel FBC = new FileByteCodeModel();
            TaskDescriptionModel item =  _taskService.getTaskDescriptionById(id);

            FD.setFileName(file.getOriginalFilename());
            FD.setFileType(file.getContentType());
            FD = _fileDescriptionService.Save(FD);

            FBC.setData(file.getBytes());
            FBC.setFileDescription(FD);
            FBC = _fileByteCodeService.Save(FBC);

            List<FileDescriptionModel> files = item.getFiles();
            files.add(FD);
            item.setFiles(files);
            _taskService.updateTaskDescription(item);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ResponseStatus result = new ResponseStatus(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatus AddFileForTU(long id, MultipartFile file) {
        try
        {
            FileDescriptionModel FD = new FileDescriptionModel();
            FileByteCodeModel FBC = new FileByteCodeModel();
            TaskUserModel item =  _taskService.getTaskUserById(id);

            FD.setFileName(file.getOriginalFilename());
            FD.setFileType(file.getContentType());
            FD = _fileDescriptionService.Save(FD);

            FBC.setData(file.getBytes());
            FBC.setFileDescription(FD);
            FBC = _fileByteCodeService.Save(FBC);

            List<FileDescriptionModel> files = item.getFiles();
            files.add(FD);
            item.setFiles(files);
            _taskService.updateTaskUser(item);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ResponseStatus result = new ResponseStatus(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatus GetFilesForTD(long TD_id) {
        TaskDescriptionModel res = _taskService.getTaskDescriptionById(TD_id);
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Файл получен");
        result.setData(res.getFiles());
        return result;
    }

    public ResponseStatus GetFilesForTU(long TU_id) {
        TaskUserModel res = _taskService.getTaskUserById(TU_id);
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        result.addMessage("Файл получен");
        result.setData(res.getFiles());
        return result;
    }

    public FileByteCodeModel Download(long fileId) {
        return _fileByteCodeService.Get(fileId);
    }
}
