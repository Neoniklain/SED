package com.unesco.core.controllerWeb;

import com.unesco.core.controller.SheduleController;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.shedule.PairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/shedule")
public class SheduleControllerWeb {
    @Autowired
    private SheduleController sheduleController;

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


    @RequestMapping("/pair/save")
    public ResponseStatusDTO savePair(@RequestBody PairDTO pairModel) {
        return sheduleController.savePair(pairModel);
    }

}
