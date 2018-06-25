package com.unesco.core.controllerWeb;

import com.unesco.core.controller.ExcelController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/excel")
public class ExcelControllerWeb {
    @Autowired
    protected ExcelController excelController;

    @RequestMapping(value = "/ParseStudyPlan")
    public ResponseStatusDTO ParseStudyPlan(@RequestParam("file") MultipartFile file) throws IOException {
        return excelController.ParseStudyPlan(file);
    }
}
