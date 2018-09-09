package com.unesco.core.controllerWeb;

import com.unesco.core.controller.ScheduleController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.PairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/shedule")
public class ScheduleControllerWeb {
    @Autowired
    private ScheduleController sheduleController;

    @RequestMapping("/department/{id}/pairs")
    public ResponseStatusDTO getDepartmentPairs(@PathVariable("id") long departmentId) {
        return sheduleController.getDepartmentPairs(departmentId);
    }

    @RequestMapping("/group/{id}/pairs")
    public ResponseStatusDTO getGroupPairs(@PathVariable("id") long groupId) {
        return sheduleController.getGroupPairs(groupId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pair/{id}")
    public ResponseStatusDTO getPair(@PathVariable("id") long pairId) {
        return sheduleController.getPair(pairId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/pair/{id}")
    public ResponseStatusDTO deletePair(@PathVariable("id") long pairId) {
        return sheduleController.deletePair(pairId);
    }

    @RequestMapping("/professor/{id}/pairs")
    public ResponseStatusDTO getProfessorPairs(@PathVariable("id") long professorId) {
        return sheduleController.getProfessorPairs(professorId);
    }

    @RequestMapping("/lesson/{lessonId}/pairs")
    public ResponseStatusDTO getLessonPairs(@PathVariable("lessonId") long lessonId) {
        return sheduleController.getLessonPairs(lessonId);
    }

    @RequestMapping("/professor/{professorId}/lessons")
    public ResponseStatusDTO getByProfessorId(@PathVariable("professorId") long professorId) {
        return sheduleController.getLessonsForProfessor(professorId);
    }

    @RequestMapping("/pair/save")
    public ResponseStatusDTO savePair(@RequestBody PairParameters param) {
        return sheduleController.savePair(param.getPairModel(), param.isSkipWarnings());
    }
}

class PairParameters {
    private PairDTO pairModel;
    private boolean skipWarnings;

    public PairParameters() {
        this.pairModel = new PairDTO();
    }

    public PairDTO getPairModel() {
        return pairModel;
    }
    public void setPairModel(PairDTO pairModel) {
        this.pairModel = pairModel;
    }

    public boolean isSkipWarnings() {
        return skipWarnings;
    }
    public void setSkipWarnings(boolean skipWarnings) {
        this.skipWarnings = skipWarnings;
    }
}

