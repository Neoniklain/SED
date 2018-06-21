package com.unesco.core.controllerWeb;


import com.unesco.core.controller.FileController;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.file.FileByteCodeModel;
import com.unesco.core.utils.StatusTypes;
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

    @RequestMapping(value = "/getFilesForTD/{id}")
    public ResponseStatus GetFilesForTD(@PathVariable("id") long id) {
        return _fileController.GetFilesForTD(id);
    }

    @RequestMapping(value = "/addFileForTD/{id}")
    public ResponseStatus AddFileForTD(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _fileController.AddFileForTD(id,file);
    }

    @RequestMapping(value = "/getFilesForTU/{id}")
    public ResponseStatus GetFilesForTU(@PathVariable("id") long id) {
        return _fileController.GetFilesForTU(id);
    }

    @RequestMapping(value = "/addFileForTU/{id}")
    public ResponseStatus AddFileForTU(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _fileController.AddFileForTU(id,file);
    }

    @RequestMapping(value = "/download/{id}")
    public ResponseStatus Download(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ResponseStatus result = new ResponseStatus(StatusTypes.OK);
        try
        {
            FileByteCodeModel myFile = _fileController.Download(id);
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
