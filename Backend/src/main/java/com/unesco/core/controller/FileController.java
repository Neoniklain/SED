package com.unesco.core.controller;

import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
import com.unesco.core.services.dataService.file.fileByteCodeService.IFileByteCodeService;
import com.unesco.core.services.dataService.file.fileDescriptionService.IFileDescriptionService;
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
    private IFileDescriptionService _fileDescriptionService;
    @Autowired
    private ITaskService _taskService;

    public ResponseStatusDTO addFileForTD(long id, MultipartFile file) {
        try
        {
            FileDescriptionModel FD = new FileDescriptionModel();
            FileByteCodeModel FBC = new FileByteCodeModel();
            TaskDescriptionModel item =  _taskService.getTaskDescriptionById(id);

            FD.setFileName(file.getOriginalFilename());
            FD.setFileType(file.getContentType());
            FD = _fileDescriptionService.save(FD);

            FBC.setData(file.getBytes());
            FBC.setFileDescription(FD);
            FBC = _fileByteCodeService.save(FBC);

            List<FileDescriptionModel> files = item.getFiles();
            files.add(FD);
            item.setFiles(files);
            _taskService.updateTaskDescription(item);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatusDTO addFileForTU(long id, MultipartFile file) {
        try
        {
            FileDescriptionModel FD = new FileDescriptionModel();
            FileByteCodeModel FBC = new FileByteCodeModel();
            TaskUserModel item =  _taskService.getTaskUserById(id);

            FD.setFileName(file.getOriginalFilename());
            FD.setFileType(file.getContentType());
            FD = _fileDescriptionService.save(FD);

            FBC.setData(file.getBytes());
            FBC.setFileDescription(FD);
            FBC = _fileByteCodeService.save(FBC);

            List<FileDescriptionModel> files = item.getFiles();
            files.add(FD);
            item.setFiles(files);
            _taskService.updateTaskUser(item);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatusDTO getFilesForTD(long TD_id) {
        TaskDescriptionModel res = _taskService.getTaskDescriptionById(TD_id);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл получен");
        result.setData(res.getFiles());
        return result;
    }

    public ResponseStatusDTO getFilesForTU(long TU_id) {
        TaskUserModel res = _taskService.getTaskUserById(TU_id);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл получен");
        result.setData(res.getFiles());
        return result;
    }

    public FileByteCodeModel download(long fileId) {
        return _fileByteCodeService.get(fileId);
    }
}
