package com.unesco.core.managers.schedule.pairManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.PairDTO;
import com.unesco.core.utils.StatusTypes;
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

        List<String> messages = new ArrayList<>();

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

                if(profEq && groupEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                    || (p.getWeektype().equals("Все"))) )
                {
                    messages.add(" В это время у "+p.getLesson().getProfessor().getUser().getUserFIO()
                            +" и "+p.getLesson().getGroup().getName()+" уже назначено занятие ");
                    break;
                }

                if(roomEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))))
                {
                    messages.add("В аудитории "+p.getRoom().getRoom()
                            +" уже назначено занятие в это время у преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO());
                    break;
                }

                if(profEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))))
                {
                    messages.add("Для преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO()
                            +" уже назначено занятие в это время у группы "+p.getLesson().getGroup().getName());
                    break;
                }

                if(groupEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))))
                {
                    messages.add("У группы "+p.getLesson().getGroup().getName()
                            +" уже назначено занятие в это время у преподавателя "+p.getLesson().getProfessor().getUser().getUserFIO());
                    break;
                }
            }
        }

        if(messages.size()>0)
        {
            result.setStatus(StatusTypes.ERROR);
            result.setErrors(messages);
        }

        return result;
    }

    public void Init(PairDTO Pair) {
        pair = Pair;
    }

    public PairDTO Get() {
        return pair;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (pair.getLesson().getGroup() == null || pair.getLesson().getGroup().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указанна группа");
        }
        if (pair.getLesson().getDiscipline() == null || pair.getLesson().getDiscipline().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указанна дисциплина");
        }
        if (pair.getLesson().getProfessor() == null || pair.getLesson().getProfessor().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан преподаватель");
        }
        if (pair.getWeektype().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана переодичность");
        }
        if (pair.getDayofweek().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан день недели");
        }
        if (pair.getRoom() == null || pair.getRoom().getId() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана аудитория");
        }
        if (pair.getPairNumber() == 0) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указана аудитория");
        }
        return responseStatusDTO;
    }

}
