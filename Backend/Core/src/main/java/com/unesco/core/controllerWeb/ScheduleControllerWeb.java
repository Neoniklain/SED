package com.unesco.core.controllerWeb;

import com.unesco.core.controller.ScheduleController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.plan.EducationPeriodDTO;
import com.unesco.core.dto.shedule.PairParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/schedule")
public class ScheduleControllerWeb {
    @Autowired
    private ScheduleController sheduleController;

    @RequestMapping("/department/{id}/pairs/period/{semester}/{year}")
    public ResponseStatusDTO getDepartmentPairs(@PathVariable("id") long departmentId, @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getDepartmentPairs(departmentId, semester, year);
    }

    @RequestMapping("/group/{id}/pairs/period/{semester}/{year}")
    public ResponseStatusDTO getGroupPairs(@PathVariable("id") long groupId, @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getGroupPairs(groupId, semester, year);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pair/{id}")
    public ResponseStatusDTO getPair(@PathVariable("id") long pairId) {
        return sheduleController.getPair(pairId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pair/{id}")
    public ResponseStatusDTO deletePair(@PathVariable("id") long pairId) {
        return sheduleController.deletePair(pairId);
    }

    @RequestMapping("/professor/{id}/pairs/period/{semester}/{year}")
    public ResponseStatusDTO getProfessorPairs(@PathVariable("id") long professorId, @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getProfessorPairs(professorId, semester, year);
    }

    @RequestMapping("/lesson/{lessonId}/pairs/period/{semester}/{year}")
    public ResponseStatusDTO getLessonPairs(@PathVariable("lessonId") long lessonId, @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getLessonPairs(lessonId, semester, year);
    }

    @RequestMapping("/professor/{professorId}/lessons/period/{semester}/{year}")
    public ResponseStatusDTO getByProfessorLessons(@PathVariable("professorId") long professorId, @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getLessonsForProfessor(professorId, semester, year);
    }

    @RequestMapping("/group/{groupId}/lessons/period/{semester}/{year}")
    public ResponseStatusDTO getByPGroupLessons(@PathVariable("groupId") long groupId, @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getLessonsForGroup(groupId, semester, year);
    }

    @RequestMapping("/pair/save")
    public ResponseStatusDTO savePair(@RequestBody PairParameters param) {
        return sheduleController.savePair(param.getPairModel(), param.isSkipWarnings());
    }

    @RequestMapping("/week/prity/period/{semester}/{year}")
    public ResponseStatusDTO getPrityWeek(@PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getPrityWeek(semester, year);
    }

    @RequestMapping("/educationPeriod/speciality/{id}")
    public ResponseStatusDTO getEducationPeriod(@PathVariable("id") long specialityId) {
        return sheduleController.getEducationPeriod(specialityId);
    }

    @RequestMapping("/educationPeriod/periodSave/")
    public ResponseStatusDTO SavePeriod(@RequestBody EducationPeriodDTO educationPeriod) {
        return sheduleController.saveEducationPeriod(educationPeriod);
    }

    @RequestMapping("/educationPeriod/group/{id}/period/{semester}/{year}")
    public ResponseStatusDTO getEducationPeriodForGroup(@PathVariable("id") long groupId,
                                                        @PathVariable("semester") int semester, @PathVariable("year") int year) {
        return sheduleController.getEducationPeriodForGroup(groupId, semester, year);
    }
}

