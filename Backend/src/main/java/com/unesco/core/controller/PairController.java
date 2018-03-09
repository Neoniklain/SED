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

    @RequestMapping("/pairs/{id}")
    public Pair getPair(@PathVariable("id") int id) {
        return pairRepository.findById(id);
    }

    @RequestMapping("pairs/add")
    public String addNewPair(@RequestBody PairModel pairModel) {
        Pair pair = new Pair();
        //pair.setId(130);
        pair.setPairNumber(pairModel.getPairnumber());
        pair.setDayofweek(dayOfWeekRepository.findDayOfWeekByDayofweek(pairModel.getDayofweek()));
        pair.setDiscipline(disciplineRepository.findDisciplineByName(pairModel.getDiscipline()));
        pair.setGroup(groupRepository.findGroupByName(pairModel.getGroup()));
        pair.setProfessor(professorRepository.findByFio(pairModel.getProfessor()));
        pair.setRoom(roomRepository.findRoomByRoom(pairModel.getRoom()));
        pair.setWeektype(weekTypeRepository.findWeekTypeByType(pairModel.getWeektype()));
        pairRepository.save(pair);
        return JSONResponseStatus.OK;
    }
}
