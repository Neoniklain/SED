package com.unesco.core.controllerWeb;


import com.unesco.core.controller.TaskController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.DisciplineDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.managers.schedule.disciplineManager.interfaces.discipline.IDisciplineManager;
import com.unesco.core.repositories.moodlerest.*;
import com.unesco.core.services.dataService.schedule.pairService.IPairDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/moodle")
public class MoodleControllerWeb {

    @Autowired
    IDisciplineManager _disciplineManager;
    @Autowired
    private IPairDataService pairDataService;

    MoodleControllerWeb() {
        MoodleCallRestWebService.setAuth("0696880e69359bb66a713931a44c9974");
        MoodleCallRestWebService.setURL("http://localhost/moodle/webservice/rest/server.php");
    }

    @RequestMapping(value = "getAllCourses")
    public ResponseStatusDTO GetAllCourses() {
        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setStatus(StatusTypes.ERROR);
        try{
            result.setData(MoodleRestCourse.getAllCourses());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "getAllUsers")
    public ResponseStatusDTO GetAllUsers() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            Criteria[] criteria = new Criteria[1];
            criteria[0] = new Criteria("email","%%");
            result.setData(MoodleRestUser.getUsers(criteria));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "createCourses")
    public ResponseStatusDTO CreateCourses() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            // 12 - id Иванова
            List<PairDTO> pairs = pairDataService.getAllByProfessor(12);
            List<DisciplineDTO> discs = new ArrayList<>();
            for (PairDTO pair: pairs) {
                Optional<DisciplineDTO> matchingObject = discs.stream().
                        filter(p -> p.getId() == pair.getLesson().getDiscipline().getId()).
                        findFirst();
                if(!matchingObject.isPresent()) {
                    discs.add(pair.getLesson().getDiscipline());
                }
            }

            ResponseStatusDTO temp = this.GetAllCourses();
            if(temp.getStatus() == StatusTypes.ERROR) {
                result.addErrors("Не удалось получить курсы из Moodle");
                return result;
            }

            MoodleCourse[] allCourses = (MoodleCourse[])temp.getData();
            List<MoodleCourse> listCourses = new ArrayList<>(Arrays.asList(allCourses));
            List<DisciplineDTO> forCreate = new ArrayList<>();
            for (DisciplineDTO disc: discs) {
                Optional<MoodleCourse> matchingObject = listCourses.stream().
                        filter(p -> p.getFullname().toLowerCase().equals(disc.getName().toLowerCase())).
                        findFirst();
                if(!matchingObject.isPresent()) {
                    forCreate.add(disc);
                }
            }
            try{
                List<MoodleCourse> courses = new ArrayList<>();
                Long catId = Long.valueOf(2);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date startDate = dateFormat.parse("01/09/2018");
                long start = startDate.getTime();
                // Убираем три нуля в концеъ
                start = start/1000;
                // 1546210800 - пример корректной даты

                Date endDate = dateFormat.parse("30/12/2018");
                long end = endDate.getTime();
                // Убираем три нуля в концеъ
                end = end/1000;
                // 1546210800 - пример корректной даты

                for (DisciplineDTO disc: forCreate) {
                    MoodleCourse course = new MoodleCourse();
                    course.setFullname(disc.getName());
                    course.setShortname(disc.getName());
                    course.setCategoryId(catId);
                    course.setStartDate(start);
                    course.setEndDate(end);
                    course.setNumSections(1);
                    course.setVisible(true);
                    courses.add(course);
                }

                if(courses.size()>0) {
                    MoodleCourse[] forCreateCourses = courses.toArray(new MoodleCourse[courses.size()]);
                    result.setData(MoodleRestCourse.createCourses(forCreateCourses));
                    result.addMessage("Дисциплины успешно созданы");
                }
                else {
                    result.addMessage("Дисциплины не указаны или курсы уже созданы");
                }
                result.setStatus(StatusTypes.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result.setData(e.getMessage());
                result.addErrors("Не удалось создать курсы (дисциплины)");
                e.printStackTrace();
            }
            //_disciplineManager.
            //result.setData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }
}
