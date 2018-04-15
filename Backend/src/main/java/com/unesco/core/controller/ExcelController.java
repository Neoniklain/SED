package com.unesco.core.controller;

import com.unesco.core.entities.plan.LessonType;
import com.unesco.core.entities.plan.Plan;
import com.unesco.core.entities.plan.Semester;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.entities.*;
import com.unesco.core.repositories.plan.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

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
    @Autowired
    private LessonTypeRepository _LessonTypeRepository;

    @RequestMapping(value = "/ParseStudyPlan")
    public JSONResponseStatus ParseStudyPlan(@RequestParam("file") MultipartFile file) throws IOException {
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
                        Discipline discipline = _DisciplineRepository.findDisciplineByName(name);

                        // ↓ Получение значений для таблицы Plan
                        Plan plan = new Plan();
                        plan.setDiscipline(discipline);
                        if(discipline == null){
                            System.out.println("Пары нет в БД");
                            plan.setDiscipline(new Discipline(name));
                        }
                        plan.setDepartment(_DepartmentRepository.findByName(row.getCell(101).getStringCellValue()));
                        if(row.getCell(2).getStringCellValue().length()>0)
                        {
                            //Доделать получение индекса. Проблему получения смотри на 134 строке в excel файле.
                            plan.setIndex(row.getCell(2).getStringCellValue());
                        }

                        //Старые поля для старой БД
                        /*if(row.getCell(6).getStringCellValue().length()>0)
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
                        }*/

                        if(_PlanRepository.findByDiscipline(plan.getDiscipline())==null) {
                            //_PlanRepository.save(plan);
                            System.out.println("План добавлен для: " + name);
                        }
                        //plan = _PlanRepository.findByDiscipline(plan.getDiscipline());

                        // ↓ Получение значений для таблицы Semester
                        Integer offset = 0;
                        for (Integer semestr = 0; semestr < 8; semestr++) {
                            Semester sem = new Semester();

                            //Нету поля "control_type" + пока не знаю как определять тип контроля. (Возможно с помощью итератора и значений в столбцах "формы контроля из excel")
                            //sem.setPlan(plan);
                            // _SemesterRepository.save(sem);
                            // sem = _SemesterRepository.findByPlanId(plan.getId());

                            //  getCellType()
                            //      1 - Стринговое значение ячейки
                            //      0 - Числовое значение ячейки
                            if (row.getCell(25 + offset).getCellType() == 1) {
                                if (row.getCell(25 + offset).getStringCellValue().length() > 0) {
                                    LessonType lection = new LessonType();
                                    lection.setHours((int) Float.parseFloat(row.getCell(25 + offset).getStringCellValue()));
                                    lection.setName("Лекция");
                                    lection.setSemester(sem);
                                    //_LessonTypeRepository.save(lection);
                                    //sem.setLection_hours((int) Float.parseFloat(row.getCell(25 + offset).getStringCellValue()));
                                    System.out.println(plan.getDiscipline().getName()+": Лекция");
                                }
                            } else if (row.getCell(25+ offset).getCellType() == 0) {
                                LessonType lection = new LessonType();
                                lection.setHours((int) row.getCell(25 + offset).getNumericCellValue());
                                lection.setName("Лекция");
                                lection.setSemester(sem);
                                //_LessonTypeRepository.save(lection);
                                //sem.setLection_hours((int) row.getCell(25 + offset).getNumericCellValue());
                                System.out.println(plan.getDiscipline().getName()+": Лекция");
                            } else System.out.println("Incorrect type of item for setLection_hours");

//------------------------------------------------------------------------------------------------------------------------------------------------

                            if (row.getCell(26 + offset).getCellType() == 1) {
                                if (row.getCell(26 + offset).getStringCellValue().length() > 0) {
                                    LessonType lection = new LessonType();
                                    lection.setHours((int) Float.parseFloat(row.getCell(26 + offset).getStringCellValue()));
                                    lection.setName("Лабораторная");
                                    lection.setSemester(sem);
                                    //_LessonTypeRepository.save(lection);
                                    //sem.setLaboratory_hours((int) Float.parseFloat(row.getCell(26 + offset).getStringCellValue()));
                                    System.out.println(plan.getDiscipline().getName()+": Лабораторная");
                                }
                            } else if (row.getCell(26+ offset).getCellType() == 0) {
                                LessonType lection = new LessonType();
                                lection.setHours((int) row.getCell(26 + offset).getNumericCellValue());
                                lection.setName("Лабораторная");
                                lection.setSemester(sem);
                                //_LessonTypeRepository.save(lection);
                                //sem.setLaboratory_hours((int) row.getCell(26 + offset).getNumericCellValue());
                                System.out.println(plan.getDiscipline().getName()+": Лабораторная");
                            } else System.out.println("Incorrect type of item for setLaboratory_hours");

//------------------------------------------------------------------------------------------------------------------------------------------------

                            if (row.getCell(27 + offset).getCellType() == 1) {
                                if (row.getCell(27 + offset).getStringCellValue().length() > 0) {
                                    LessonType lection = new LessonType();
                                    lection.setHours((int) Float.parseFloat(row.getCell(27 + offset).getStringCellValue()));
                                    lection.setName("Практика");
                                    lection.setSemester(sem);
                                    //_LessonTypeRepository.save(lection);
                                    //sem.setPractice_hours((int) Float.parseFloat(row.getCell(27 + offset).getStringCellValue()));
                                    System.out.println(plan.getDiscipline().getName()+": Практика");
                                }
                            } else if (row.getCell(27+ offset).getCellType() == 0) {
                                LessonType lection = new LessonType();
                                lection.setHours((int) row.getCell(27 + offset).getNumericCellValue());
                                lection.setName("Практика");
                                lection.setSemester(sem);
                                //_LessonTypeRepository.save(lection);
                                //sem.setPractice_hours((int) row.getCell(27 + offset).getNumericCellValue());
                                System.out.println(plan.getDiscipline().getName()+": Практика");
                            } else System.out.println("Incorrect type of item for setPractice_hours");

//------------------------------------------------------------------------------------------------------------------------------------------------

                            if (row.getCell(28 + offset).getCellType() == 1) {
                                if (row.getCell(28 + offset).getStringCellValue().length() > 0) {
                                    LessonType lection = new LessonType();
                                    lection.setHours((int) Float.parseFloat(row.getCell(28 + offset).getStringCellValue()));
                                    lection.setName("СРС");
                                    lection.setSemester(sem);
                                    //_LessonTypeRepository.save(lection);
                                    //sem.setSRS_hours((int) Float.parseFloat(row.getCell(28 + offset).getStringCellValue()));
                                    System.out.println(plan.getDiscipline().getName()+": СРС");
                                }
                            } else if (row.getCell(28+ offset).getCellType() == 0) {
                                LessonType lection = new LessonType();
                                lection.setHours((int) row.getCell(28 + offset).getNumericCellValue());
                                lection.setName("СРС");
                                lection.setSemester(sem);
                                //_LessonTypeRepository.save(lection);
                                //sem.setSRS_hours((int) row.getCell(28 + offset).getNumericCellValue());
                                System.out.println(plan.getDiscipline().getName()+": СРС");
                            } else System.out.println("Incorrect type of item for setSRS_hours");

//------------------------------------------------------------------------------------------------------------------------------------------------

                            if (row.getCell(29 + offset).getCellType() == 1) {
                                if (row.getCell(29 + offset).getStringCellValue().length() > 0) {
                                    LessonType lection = new LessonType();
                                    lection.setHours((int) Float.parseFloat(row.getCell(29 + offset).getStringCellValue()));
                                    lection.setName("Контроль");
                                    lection.setSemester(sem);
                                    //_LessonTypeRepository.save(lection);
                                    //sem.setControl_hours((int) Float.parseFloat(row.getCell(29 + offset).getStringCellValue()));
                                    System.out.println(plan.getDiscipline().getName()+": Контроль");
                                }
                            } else if (row.getCell(29+ offset).getCellType() == 0) {
                                LessonType lection = new LessonType();
                                lection.setHours((int) row.getCell(29 + offset).getNumericCellValue());
                                lection.setName("Контроль");
                                lection.setSemester(sem);
                                //_LessonTypeRepository.save(lection);
                                //sem.setControl_hours((int) row.getCell(29 + offset).getNumericCellValue());
                                System.out.println(plan.getDiscipline().getName()+": Контроль");
                            } else System.out.println("Incorrect type of item for setControl_hours");

//------------------------------------------------------------------------------------------------------------------------------------------------

                            if (row.getCell(30 + offset).getCellType() == 1) {
                                if (row.getCell(30 + offset).getStringCellValue().length() > 0) {
                                    LessonType lection = new LessonType();
                                    lection.setHours((int) Float.parseFloat(row.getCell(30 + offset).getStringCellValue()));
                                    lection.setName("ЗЕТ");
                                    lection.setSemester(sem);
                                    //_LessonTypeRepository.save(lection);
                                    //sem.setZET_hours((int) Float.parseFloat(row.getCell(30 + offset).getStringCellValue()));
                                    System.out.println(plan.getDiscipline().getName()+": ЗЕТ");
                                }
                            } else if (row.getCell(30+ offset).getCellType() == 0) {
                                LessonType lection = new LessonType();
                                lection.setHours((int) row.getCell(30 + offset).getNumericCellValue());
                                lection.setName("ЗЕТ");
                                lection.setSemester(sem);
                                //_LessonTypeRepository.save(lection);
                                //sem.setZET_hours((int) row.getCell(30 + offset).getNumericCellValue());
                                System.out.println(plan.getDiscipline().getName()+": ЗЕТ");
                            } else System.out.println("Incorrect type of item for setZET_hours");

//------------------------------------------------------------------------------------------------------------------------------------------------

                            if (semestr % 2 != 0) {
                                offset += 10;
                            } else {
                                offset += 7;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Parsing is finished!");
        myExcelBook.close();
        return JSONResponseStatus.OK();
    }
}
