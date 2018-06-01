package com.unesco.core.managers.schedule.pairManager;

import com.unesco.core.managers.schedule.pairManager.interfaces.pair.IPairManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.PairModel;
import com.unesco.core.utils.StatusTypes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class PairManager implements IPairManager {

    PairModel pair;

    public PairManager() {
        pair = new PairModel();
    }

    public ResponseStatus CheckIntersection(List<PairModel> pairsForValidate) {

        ResponseStatus result = new ResponseStatus();
        result.setStatus(StatusTypes.OK);

        int size = pairsForValidate.size();
        if (size == 0) {
            return result;
        }

        List<String> messages = new ArrayList<>();

        for (PairModel p: pairsForValidate) {

            if (pair.getId() == p.getId()) {
                continue;
            }

            if(pair.getDayofweek().equals(p.getDayofweek())
                    && pair.getPairNumber() == p.getPairNumber()) {

                boolean profEq = pair.getProfessor().getId() == p.getProfessor().getId();
                boolean groupEq = pair.getGroup().getId() == p.getGroup().getId();
                boolean roomEq = pair.getRoom().getId() == p.getRoom().getId();
                boolean weektypeEq = pair.getWeektype().equals(p.getWeektype());

                if(profEq && groupEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                    || (p.getWeektype().equals("Все"))) )
                {
                    messages.add(" В это время у "+p.getProfessor().getUserFIO()
                            +" и "+p.getGroup().getName()+" уже назначено занятие ");
                    break;
                }

                if(roomEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))))
                {
                    messages.add("В аудитории "+p.getRoom().getRoom()
                            +" уже назначено занятие в это время у преподавателя "+p.getProfessor().getUserFIO());
                    break;
                }

                if(profEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))))
                {
                    messages.add("Для преподавателя "+p.getProfessor().getUserFIO()
                            +" уже назначено занятие в это время у группы "+p.getGroup().getName());
                    break;
                }

                if(groupEq && (weektypeEq || (pair.getWeektype().equals("Все"))
                        || (p.getWeektype().equals("Все"))))
                {
                    messages.add("У группы "+p.getGroup().getName()
                            +" уже назначено занятие в это время у преподавателя "+p.getProfessor().getUserFIO());
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

    public void Init(PairModel Pair) {
        pair = Pair;
    }

    public PairModel Get() {
        return pair;
    }

}
