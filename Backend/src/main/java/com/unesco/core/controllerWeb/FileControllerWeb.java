package com.unesco.core.controllerWeb;


import com.unesco.core.controller.FileController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.dto.enums.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.jasper.Constants.DEFAULT_BUFFER_SIZE;

@CrossOrigin
@RestController
@RequestMapping("api/file")
public class FileControllerWeb {

    @Autowired
    FileController _fileController;

    @RequestMapping(value = "/getFileForObject/{objectTypeId}/{objectId}")
    public ResponseStatusDTO getFileForObject(@PathVariable("objectTypeId") long objectTypeId,
                                              @PathVariable("objectId") long objectId) {
        return _fileController.getFiles(objectTypeId, objectId);
    }

    @RequestMapping(value = "/addFileForObject/{objectTypeId}/{objectId}")
    public ResponseStatusDTO addFilesForObject(@PathVariable("objectTypeId") long objectTypeId,
                                               @PathVariable("objectId") long objectId,
                                               @RequestParam("files") MultipartFile[] files) {
        return _fileController.addFiles(objectTypeId, objectId, files);
    }

    @RequestMapping(value = "/download/{fileId}")
    public ResponseStatusDTO download(@PathVariable("fileId") long fileId, HttpServletRequest request, HttpServletResponse response)
    {
        ResponseStatusDTO result = new ResponseStatusDTO();
        try
        {
            FileByteCodeModel myFile = _fileController.getByteCode(fileId);
            byte[] file = myFile.getData();
            response.reset();
            response.setHeader("Content-disposition","attachment; filename="+myFile.getFileDescription().getFileName());
            response.setBufferSize(DEFAULT_BUFFER_SIZE);
            response.setContentType(myFile.getFileDescription().getFileType());
            response.getOutputStream().write(file);
            result.setStatus(StatusTypes.OK);
            result.addMessage("Файл загружен");
        }
        catch (IOException e) {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("Ошибка загрузки файла");
        }
        return result;
    }
}
