package com.unesco.core.controllerWeb;


import com.unesco.core.controller.FileController;
import com.unesco.core.models.additional.ResponseStatusDTO;
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
    public ResponseStatusDTO GetFilesForTD(@PathVariable("id") long id) {
        return _fileController.GetFilesForTD(id);
    }

    @RequestMapping(value = "/addFileForTD/{id}")
    public ResponseStatusDTO AddFileForTD(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _fileController.AddFileForTD(id,file);
    }

    @RequestMapping(value = "/getFilesForTU/{id}")
    public ResponseStatusDTO GetFilesForTU(@PathVariable("id") long id) {
        return _fileController.GetFilesForTU(id);
    }

    @RequestMapping(value = "/addFileForTU/{id}")
    public ResponseStatusDTO AddFileForTU(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        return _fileController.AddFileForTU(id,file);
    }

    @RequestMapping(value = "/download/{id}")
    public ResponseStatusDTO Download(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
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
