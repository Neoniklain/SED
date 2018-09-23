package com.unesco.core.managers.schedule.pairManager;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class PairManager implements IPairManager {

    PairDTO pair;

    public PairManager() {
        pair = new PairDTO();
    }

    public ResponseStatusDTO CheckIntersection(List<PairDTO> pairsForValidate) {

        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setStatus(StatusTypes.OK);

        int size = pairsForValidate.size();
        if (size == 0) {
            return result;
        }

        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        for (PairDTO p: pairsForValidate) {

            if (pair.getId() == p.getId()) {
                continue;
            }

            if(pair.getDayofweek().equals(p.getDayofweek())
                    && pair.getPairNumber() == p.getPairNumber()) {

                boolean profEq = pair.getLesson().getProfessor().getId() == p.getLesson().getProfessor().getId();
                boolean groupEq = pair.getLesson().getGroup().getId() == p.getLesson().getGroup().getId();
                boolean roomEq = pair.getRoom().getId() == p.getRoom().getId();
                boolean weektypeEq = pair.getWeektype().equals(p.getWeektype());
                boolean subGroupEq = (pair.getSubgroup() == p.getSubgroup()
                                        || (pair.getSubgroup() == 0 && p.getSubgroup()!=0)
                                        || (pair.getSubgroup() != 0 && p.getSubgroup()==0));
                boolean disciplineEq = pair.getLesson().getDiscipline().getId() == p.getLesson().getDiscipline().getId();

                if (pair.isOptionally() && pair.getSubgroup() != 0) {
                    errors.add("Нельзя создать занятие по выбору и разделенное на подгруппы одновременно.");
                    break;
                }

                if (profEq && groupEq
                        && subGroupEq
                        && (weektypeEq
                            || (pair.getWeektype().equals("Все"))
                            || (p.getWeektype().equals("Все"))
                        )
                    )
                {
                    errors.add(" В это время у "+p.getLesson().getProfessor().getUser().getUserFIO()
                            +" и "+p.getLesson().getGroup().getName()+" уже назначено занятие ");
                    break;
                }

                if(profEq && groupEq && subGroupEq && pair.isFlow() && p.isFlow()
                        && (weektypeEq
                        || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все")))) {
                    if (!disciplineEq) {
                        errors.add("У потоковых дисциплин должен быть одинаковый предмет ("+p.getLesson().getDiscipline().getName()+").");
                        break;
                    }
                    if (!roomEq) {
                        errors.add("У потоковых дисциплин должна быть одинаковая аудитория ("+p.getRoom().getRoom()+").");
                        break;
                    }
                    if (!weektypeEq) {
                        errors.add("У потоковых дисциплин должна быть одинаковая переодичность ("+p.getWeektype()+").");
                        break;
                    }
                }

                if (profEq
                        && ((!p.isFlow() && pair.isFlow()) || (p.isFlow() && !pair.isFlow()) || (!p.isFlow() && !pair.isFlow()))
                        && (weektypeEq
                        || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))
                )
                        )
                {
                    if(p.isFlow())
                        warnings.add("Конфликтующее занятие является потоковым. Возможно вы забыли указать опцию 'Потоковый'?");

                    errors.add("Для преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO()
                            +" уже назначено занятие в это время у"
                            +p.getLesson().getGroup().getName());

                    break;
                }

                if (roomEq 
                        && ((!p.isFlow() && pair.isFlow()) || (p.isFlow() && !pair.isFlow()) || (!p.isFlow() && !pair.isFlow()))
                        && (weektypeEq
                            || (pair.getWeektype().equals("Все"))
                            || (p.getWeektype().equals("Все"))
                        )
                    )
                {
                    errors.add("В аудитории "+p.getRoom().getRoom()
                            +" уже назначено занятие в это время у преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO());
                    break;
                }

                if (groupEq && subGroupEq
                        && (weektypeEq
                            || (pair.getWeektype().equals("Все"))
                            || (p.getWeektype().equals("Все"))
                        )
                        && !pair.isOptionally()
                        && !p.isOptionally())
                {
                    errors.add("У "+p.getLesson().getGroup().getName()+" уже назначено занятие в это время у преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO());
                    break;
                }

                if (groupEq && subGroupEq
                        && (weektypeEq
                            || (pair.getWeektype().equals("Все"))
                            || (p.getWeektype().equals("Все"))
                        )
                        && (pair.isOptionally()
                        && !p.isOptionally()))
                {
                    errors.add("У "+p.getLesson().getGroup().getName()+" уже назначено занятие в это время у преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO());
                    break;
                }

                if (groupEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все")))
                        && (pair.isOptionally()
                        && p.isOptionally()
                        && pairsForValidate.stream().anyMatch(
                                o -> (o.getWeektype() == pair.getWeektype()
                                || (pair.getWeektype().equals("Все"))
                                || (p.getWeektype().equals("Все")))
                                && pair.getDayofweek().equals(o.getDayofweek())
                                && pair.getPairNumber() == o.getPairNumber()
                                && o.isOptionally()
                                && o != p
                                && o.getId() != pair.getId()
                            )
                        )
                )
                {
                    errors.add("В один день не может быть больше двух занятий по выбору");
                    break;
                }
            }
        }

        if(errors.size()>0)
        {
            result.setStatus(StatusTypes.WARNING);
            result.setErrors(errors);
            result.setWarnings(warnings);
        }

        return result;
    }

    public void init(PairDTO Pair) {
        pair = Pair;
    }

    public PairDTO get() {
        return pair;
    }

    public ResponseStatusDTO validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (pair.getLesson().getGroup() == null) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указанна группа");
        }
        if (pair.getLesson() == null || pair.getLesson().getDiscipline() == null || pair.getLesson().getDiscipline().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указанна дисциплина");
        }
        if (pair.getLesson().getProfessor() == null || pair.getLesson().getProfessor() == null || pair.getLesson().getProfessor().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан преподаватель");
        }
        if (pair.getWeektype() == null || pair.getWeektype().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана переодичность");
        }
        if (pair.getDayofweek() == null || pair.getDayofweek().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан день недели");
        }
        if (pair.getRoom() == null || pair.getRoom() == null || pair.getRoom().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана аудитория");
        }
        if (pair.getPairNumber() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан номер занятия");
        }
        if (pair.getPairType().getType() == "") {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан тип занятия");
        }
        return responseStatusDTO;
    }

}
