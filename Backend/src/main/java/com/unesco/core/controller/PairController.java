package com.unesco.core.controller;

import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.models.PairModel;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.repositories.*;
import com.unesco.core.repositories.plan.DisciplineRepository;
import com.unesco.core.repositories.plan.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class PairController {
    @Autowired
    public PairRepository pairRepository;

    @Autowired
    public DayOfWeekRepository dayOfWeekRepository;

    @Autowired
    public DisciplineRepository disciplineRepository;

    @Autowired
    public GroupRepository groupRepository;

    @Autowired
    public ProfessorRepository professorRepository;

    @Autowired
    public RoomRepository roomRepository;

    @Autowired
    public WeekTypeRepository weekTypeRepository;

    @RequestMapping("/pair/get/{id}")
    public Pair getPair(@PathVariable("id") int id) {
        return pairRepository.findById(id);
    }

    @RequestMapping("pairs/save")
    public String addNewPair(@RequestBody PairModel pairModel) {
        Pair pair = new Pair();
        //pair.setId(130);
        modelToPair(pair, pairModel);
        pairRepository.save(pair);
        return JSONResponseStatus.OK;
    }

    @RequestMapping("/pair/{id}/edit")
    public String updatePair(@PathVariable("id") int id, @RequestBody PairModel pairModel) {
        Pair pair = pairRepository.findById(pairModel.getId());
        modelToPair(pair, pairModel);
        pairRepository.save(pair);
        return JSONResponseStatus.OK;
    }

    @RequestMapping("/pair/delete/{id}")
    public String deletePair(@PathVariable("id") int id) {
        pairRepository.delete(id);
        return JSONResponseStatus.OK;
    }

    void modelToPair(Pair pair, PairModel pairModel) {
        pair.setPairNumber(pairModel.getPairnumber());
        pair.setDayofweek(dayOfWeekRepository.findDayOfWeekByDayofweek(pairModel.getDayofweek()));
        pair.setDiscipline(disciplineRepository.findDisciplineByName(pairModel.getDiscipline()));
        pair.setGroup(groupRepository.findGroupByName(pairModel.getGroup()));
        pair.setProfessor(professorRepository.findByFio(pairModel.getProfessor()));
        pair.setRoom(roomRepository.findRoomByRoom(pairModel.getRoom()));
        pair.setWeektype(weekTypeRepository.findWeekTypeByType(pairModel.getWeektype()));
    }
}
