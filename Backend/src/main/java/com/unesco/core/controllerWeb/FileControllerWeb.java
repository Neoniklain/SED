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

import static org.apache.jasper.Constants.DEFAULT_BUFFER_SIZE;

@CrossOrigin
@RestController
@RequestMapping("api/file")
public class FileControllerWeb {

    @Autowired
    FileController _fileController;

    /*@RequestMapping(value = "/getFilesForTD/{id}")
    public ResponseStatusDTO getFilesForTD(@PathVariable("id") long id) {
        return _fileController.getFilesForTD(id);
    }

    @RequestMapping(value = "/addFileForTD/{id}")
    public ResponseStatusDTO addFileForTD(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _fileController.addFileForTD(id,file);
    }

    @RequestMapping(value = "/getFilesForTU/{id}")
    public ResponseStatusDTO getFilesForTU(@PathVariable("id") long id) {
        return _fileController.getFilesForTU(id);
    }

    @RequestMapping(value = "/addFileForTU/{id}")
    public ResponseStatusDTO addFileForTU(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _fileController.addFileForTU(id,file);
    }*/

    @RequestMapping(value = "/download/{id}")
    public ResponseStatusDTO download(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        try
        {
            FileByteCodeModel myFile = _fileController.download(id);
            byte[] file = myFile.getData();
            response.reset();
            response.setHeader("Content-disposition","attachment; filename="+myFile.getFileDescription().getFileName());
            response.setBufferSize(DEFAULT_BUFFER_SIZE);
            response.setContentType(myFile.getFileDescription().getFileType()); //or whatever file type you want to send.
            response.getOutputStream().write(file);
            result.addMessage("Файл загружен");
        }
        catch (IOException e) {
            result.addErrors("Ошибка загрузки файла");
        }
        return result;
    }
}
