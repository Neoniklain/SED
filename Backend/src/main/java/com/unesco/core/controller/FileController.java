package com.unesco.core.controller;

import com.unesco.core.managers.task.interfaces.ITaskService;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.services.dataService.file.fileByteCodeService.IFileByteCodeService;
import com.unesco.core.services.dataService.file.fileDescriptionService.IFileDescriptionService;
import com.unesco.core.dto.enums.StatusTypes;
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

        FileDescriptionModel FD = new FileDescriptionModel();
        FileByteCodeModel FBC = new FileByteCodeModel();
        TaskDescriptionDTO item =  _taskService.getTaskDescriptionById(id);

        FD.setFileName(file.getOriginalFilename());
        FD.setFileType(file.getContentType());
        ResponseStatusDTO<FileDescriptionModel> saveFD = _fileDescriptionService.save(FD);
        if(saveFD.getStatus() == StatusTypes.ERROR)
        {
            saveFD.addErrors("Ошибка добавления файла");
            return saveFD;
        }
        FD = saveFD.getData();

        try {
            FBC.setData(file.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }

        FBC.setFileDescription(FD);
        ResponseStatusDTO<FileByteCodeModel> saveFBC = _fileByteCodeService.save(FBC);
        if(saveFBC.getStatus() == StatusTypes.ERROR)
        {
            saveFBC.addErrors("Ошибка добавления файла");
            return saveFBC;
        }

        List<FileDescriptionModel> files = item.getFiles();
        files.add(FD);
        item.setFiles(files);
        _taskService.updateTaskDescription(item);

        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatusDTO addFileForTU(long id, MultipartFile file) {

        FileDescriptionModel FD = new FileDescriptionModel();
        FileByteCodeModel FBC = new FileByteCodeModel();
        TaskUserDTO item =  _taskService.getTaskUserById(id);

        FD.setFileName(file.getOriginalFilename());
        FD.setFileType(file.getContentType());
        ResponseStatusDTO<FileDescriptionModel> saveFD = _fileDescriptionService.save(FD);
        if(saveFD.getStatus() == StatusTypes.ERROR)
        {
            saveFD.addErrors("Ошибка добавления файла");
            return saveFD;
        }
        FD = saveFD.getData();

        try {
            FBC.setData(file.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }

        FBC.setFileDescription(FD);
        ResponseStatusDTO<FileByteCodeModel> saveFBC = _fileByteCodeService.save(FBC);
        if(saveFBC.getStatus() == StatusTypes.ERROR)
        {
            saveFBC.addErrors("Ошибка добавления файла");
            return saveFBC;
        }

        List<FileDescriptionModel> files = item.getFiles();
        files.add(FD);
        item.setFiles(files);
        _taskService.updateTaskUser(item);

        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл добавлен");
        return result;
    }

    public ResponseStatusDTO getFilesForTD(long TD_id) {
        TaskDescriptionDTO res = _taskService.getTaskDescriptionById(TD_id);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл получен");
        result.setData(res.getFiles());
        return result;
    }

    public ResponseStatusDTO getFilesForTU(long TU_id) {
        TaskUserDTO res = _taskService.getTaskUserById(TU_id);
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        result.addMessage("Файл получен");
        result.setData(res.getFiles());
        return result;
    }

    public FileByteCodeModel download(long fileId) {
        return _fileByteCodeService.get(fileId);
    }
}
