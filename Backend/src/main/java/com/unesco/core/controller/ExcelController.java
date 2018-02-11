package com.unesco.core.controller;

import com.unesco.core.ViewModel.JSONResponseStatus;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.News;
import com.unesco.core.repositories.DisciplineRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/excel")
public class ExcelController {
    @Autowired
    private DisciplineRepository _DisciplineRepository;

    @RequestMapping(value = "/ParseStudyPlan")
    public String ParseStudyPlan(@RequestParam("file") MultipartFile file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(file.getInputStream());
        HSSFSheet myExcelSheet = myExcelBook.getSheet("План");
        Iterator<Row> rowIterator = myExcelSheet.iterator();
        List<Discipline> disciplines = new ArrayList<Discipline>();

        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            if(row.getRowNum()>18) {
                //System.out.println("Row Num = " + row.getRowNum());
                if(row.getCell(3).getCellStyle().getFillForegroundColor() == 42) {
                    //System.out.println("Cell 3 style = " + row.getCell(3).getCellStyle().getFillForegroundColor());
                    if(row.getCell(3).getStringCellValue().length()>0) {
                        //System.out.println("Cell name = " + row.getCell(3).getStringCellValue());
                        String name = row.getCell(3).getStringCellValue();
                        String index = row.getCell(2).getStringCellValue();
                        disciplines.add(new Discipline(/*index,*/ name, new Date()));
                    }
                }
            }
        }
        myExcelBook.close();
        _DisciplineRepository.save(disciplines);
        return "{'Disciplines':'added'}";
    }
}
