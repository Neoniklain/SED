package com.unesco.core.controller;

import com.unesco.core.ViewModel.JSONResponseStatus;
import com.unesco.core.entities.*;
import com.unesco.core.repositories.*;
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
    @Autowired
    private DepartmentRepository _DepartmentRepository;
    @Autowired
    private CompetenceRepository _CompetenceRepository;
    @Autowired
    private PlanRepository _PlanRepository;
    @Autowired
    private SemesterRepository _SemesterRepository;

    @RequestMapping(value = "/ParseStudyPlan")
    public String ParseStudyPlan(@RequestParam("file") MultipartFile file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(file.getInputStream());
        HSSFSheet myExcelSheet = myExcelBook.getSheet("План");
        Iterator<Row> rowIterator = myExcelSheet.iterator();
        System.out.println("Parsing is started!");
        while (rowIterator.hasNext())
        {//71
            Row row = rowIterator.next();
            if(row.getRowNum()>20) {
                // ↓ Получение названий кафедр
                if(row.getCell(101).getCellStyle().getFillForegroundColor() == 22) {
                    if (row.getCell(101).getStringCellValue().length() > 0) {
                        String name = row.getCell(101).getStringCellValue();
                        if (_DepartmentRepository.findByName(name) == null) {
                            //_DepartmentRepository.save(new Department(name));
                            System.out.println("Кафедра добавлена: " + name);
                        }
                    }
                }

                // ↓ Получение компетенций
                if(row.getCell(102).getCellStyle().getFillForegroundColor() == 22) {
                    if (row.getCell(102).getStringCellValue().length() > 0) {
                        String codes_string = row.getCell(102).getStringCellValue();
                        if(!codes_string.toLowerCase().equals("Компетенции".toLowerCase())) {
                            codes_string = codes_string.replaceAll(" ", "");
                            String[] parts = codes_string.split(";");
                            for (String item : parts) {
                                String[] subparts = item.split("-");
                                String liters = subparts[0];
                                String[] numbers = subparts[1].split(",");
                                for (String code : numbers) {
                                    String res = liters + "-" + code;
                                    if (_CompetenceRepository.findByCode(res) == null) {
                                        //_CompetenceRepository.save(new Competence(res));
                                        System.out.println("Компетенция добавлена: " + res);
                                    }
                                }
                            }
                        }
                    }
                }

                // ↓ Получение названий пар
                if(row.getCell(3).getStringCellValue().length()>0) {

                    if(row.getCell(3).getCellStyle().getFillForegroundColor() == 42) {
                        String name = row.getCell(3).getStringCellValue();
                        if(_DisciplineRepository.findDisciplineByName(name) == null) {
                            //_DisciplineRepository.save(new Discipline(name, new Date()));
                            System.out.println("Дисциплина добавлена: " + name);
                        }

                        // ↓ Получение значений для таблицы Plan
                        Plan plan = new Plan();
                        plan.setDiscipline(_DisciplineRepository.findDisciplineByName(name));
                        plan.setDepartment(_DepartmentRepository.findByName(row.getCell(101).getStringCellValue()));
                        if(row.getCell(2).getStringCellValue().length()>0)
                        {
                            //Доделать получение индекса. Проблему получения смотри на 134 строке в excel файле.
                            plan.setIndex(row.getCell(2).getStringCellValue());
                        }
                        if(row.getCell(6).getStringCellValue().length()>0)
                        {
                            plan.setExamsQuantity(Integer.parseInt(row.getCell(6).getStringCellValue()));
                        }
                        if(row.getCell(7).getStringCellValue().length()>0)
                        {
                            String str = row.getCell(7).getStringCellValue();
                            String[] substr = str.split("-");
                            System.out.println("Last item of substr = "+substr[substr.length-1]);
                            plan.setSetoffQuantity(Integer.parseInt(substr[substr.length-1]));
                        }
                        if(row.getCell(8).getStringCellValue().length()>0)
                        {
                            plan.setSetOffWithMarkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                        }
                        if(row.getCell(9).getStringCellValue().length()>0)
                        {
                            plan.setCourseProjectQuantity(Integer.parseInt(row.getCell(9).getStringCellValue()));
                        }
                        if(row.getCell(10).getStringCellValue().length()>0)
                        {
                            plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                        }
                        if(_PlanRepository.findByDiscipline(plan.getDiscipline())==null) {
                            //_PlanRepository.save(plan);
                            System.out.println("План добавлен для: " + name);
                        }
                        // ↓ Получение значений для таблицы Semester
                        // ↓ Доделать через цикл.
                        /*for(Integer course=0; course < 4; course++)
                        {
                            for(Integer semestr=0; semestr<2;semestr++) {
                                Semester sem = new Semester();
                                Integer offset = semestr*2;
                                //Добавить ссылку у семестра на план
                                //sem.setPlans(_PlanRepository.findByDiscipline(plan.getDiscipline()));
                                if (row.getCell(25+offset).getStringCellValue().length() > 0) {
                                    plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                }
                                if (row.getCell(26+offset).getStringCellValue().length() > 0) {
                                    plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                }
                                if (row.getCell(27+offset).getStringCellValue().length() > 0) {
                                    plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                }
                                if (row.getCell(28+offset).getStringCellValue().length() > 0) {
                                    plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                }
                                if (row.getCell(29+offset).getStringCellValue().length() > 0) {
                                    plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                }
                                if (row.getCell(30+offset).getStringCellValue().length() > 0) {
                                    plan.setCourseWorkQuantity(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                }
                            }
                        }*/

                        // ↓ 1 семестр
                        Semester sem = new Semester();
                        //Добавить ссылку у семестра на план
                        //sem.setPlans(_PlanRepository.findByDiscipline(plan.getDiscipline()));
                        if (row.getCell(25).getStringCellValue().length() > 0) {
                            sem.setLection_hours(Integer.parseInt(row.getCell(25).getStringCellValue()));
                        }
                        if (row.getCell(26).getStringCellValue().length() > 0) {
                            sem.setLaboratory_hours(Integer.parseInt(row.getCell(26).getStringCellValue()));
                        }
                        if (row.getCell(27).getStringCellValue().length() > 0) {
                            sem.setPractice_hours(Integer.parseInt(row.getCell(27).getStringCellValue()));
                        }
                        if (row.getCell(28).getStringCellValue().length() > 0) {
                            sem.setSRS_hours(Integer.parseInt(row.getCell(28).getStringCellValue()));
                        }
                        if (row.getCell(29).getStringCellValue().length() > 0) {
                            sem.setControl_hours(Integer.parseInt(row.getCell(29).getStringCellValue()));
                        }
                        if (row.getCell(30).getStringCellValue().length() > 0) {
                            sem.setZET_hours(Integer.parseInt(row.getCell(30).getStringCellValue()));
                        }
                        if(_SemesterRepository.findByPlans(_PlanRepository.findByDiscipline(plan.getDiscipline()))==null)
                        {
                            //_SemesterRepository.save(sem);
                            System.out.println("Семестр 1 добавлен для: " + name);
                        }
                    }
                }
            }
        }
        System.out.println("Parsing is finished!");
        myExcelBook.close();
        return JSONResponseStatus.OK;
    }
}
