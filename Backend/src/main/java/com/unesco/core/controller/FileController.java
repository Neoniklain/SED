package com.unesco.core.controller;

import com.unesco.core.dto.enums.ObjectType;
import com.unesco.core.managers.task.interfaces.ITaskManager;
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
    private ITaskManager _taskService;

    public ResponseStatusDTO addFile(long objectTypeId, long objectId, MultipartFile file) {
        ResponseStatusDTO result;
        try {
            ObjectType type = ObjectType.getById(objectTypeId);
            if (type == null) {
                result = new ResponseStatusDTO(StatusTypes.ERROR);
                result.addErrors("Предназначенный класс не найден");
                return result;
            }
            FileDescriptionModel FD = new FileDescriptionModel();
            FileByteCodeModel FBC = new FileByteCodeModel();

            // Сохранение описания файла
            FD.setFileName(file.getOriginalFilename());
            FD.setFileType(file.getContentType());
            ResponseStatusDTO<FileDescriptionModel> saveFD = _fileDescriptionService.save(FD);
            if (saveFD.getStatus() == StatusTypes.ERROR) {
                saveFD.addErrors("Ошибка добавления файла");
                return saveFD;
            }
            FD = saveFD.getData();

            // Сохранение самого файла (byte кода)
            FBC.setData(file.getBytes());
            FBC.setFileDescription(FD);
            ResponseStatusDTO<FileByteCodeModel> saveFBC = _fileByteCodeService.save(FBC);
            if (saveFBC.getStatus() == StatusTypes.ERROR) {
                saveFBC.addErrors("Ошибка добавления файла");
                return saveFBC;
            }

            // Прикрепление файлов к объектам
            if(type == ObjectType.TaskDescription){
                TaskDescriptionDTO item =  _taskService.getTaskDescById(objectId,false);
                List<FileDescriptionModel> files = item.getFiles();
                files.add(FD);
                item.setFiles(files);
                _taskService.updateTaskDesc(item);
            }

            if(type == ObjectType.TaskUser){
                TaskUserDTO item =  _taskService.getTaskUserById(objectId);
                List<FileDescriptionModel> files = item.getFiles();
                files.add(FD);
                item.setFiles(files);
                _taskService.updateTaskUser(item);
            }

            result = new ResponseStatusDTO(StatusTypes.OK);
            result.addMessage("Файл добавлен");
        }
        catch (IOException e) {
            e.printStackTrace();
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Ошибка добавления файла");
        }
        return result;
    }

    public ResponseStatusDTO getFiles(long objectTypeId, long objectId){
        ResponseStatusDTO result;
        ObjectType type = ObjectType.getById(objectTypeId);
        if (type == null) {
            result = new ResponseStatusDTO(StatusTypes.ERROR);
            result.addErrors("Предназначенный класс не найден");
            return result;
        }

        if(type == ObjectType.TaskDescription){
            TaskDescriptionDTO item =  _taskService.getTaskDescById(objectId,false);
            result = new ResponseStatusDTO(StatusTypes.OK);
            result.addMessage("Список файлов");
            result.setData(item.getFiles());
            return result;
        }

        if(type == ObjectType.TaskUser){
            TaskUserDTO res = _taskService.getTaskUserById(objectId);
            result = new ResponseStatusDTO(StatusTypes.OK);
            result.addMessage("Список файлов");
            result.setData(res.getFiles());
            return result;
        }

        result = new ResponseStatusDTO(StatusTypes.ERROR);
        result.addErrors("Не удалось получить список файлов");
        return result;
    }

    public FileByteCodeModel getByteCode(long fileId) {
        return _fileByteCodeService.get(fileId);
    }
}
