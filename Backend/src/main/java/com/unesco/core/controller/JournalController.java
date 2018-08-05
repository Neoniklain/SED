package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.managers.journal.VisitationConfigManager.interfaces.IVisitationConfigManager;
import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.ILessonEventListManager;
import com.unesco.core.services.journal.journal.IJournalDataService;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.services.journal.visitation.IVisitationConfigDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalController {

    @Autowired
    private IJournalDataService journalDataService;
    @Autowired
    private IJournalManager journalManager;
    @Autowired
    private ILessonEventManager lessonEventManager;
    @Autowired
    private ILessonEventListManager lessonEventListManager;
    @Autowired
    private IVisitationConfigDataService visitationConfigDataService;
    @Autowired
    private ILessonEventDataService lessonEventDataService;
    @Autowired
    private IVisitationConfigManager visitationConfigManager;

    public ResponseStatusDTO GetJournal(long lessonId) {

        JournalDTO journal = journalDataService.Get(lessonId);

        VisitationConfigDTO visitConfig = visitationConfigDataService.GetByLesson(lessonId);
        visitationConfigManager.Init(visitConfig);

        List<LessonEventDTO> lessonEvents = lessonEventDataService.GetAll();
        lessonEventListManager.Init(lessonEvents);

        journalManager.Init(journal, lessonEventListManager.GetAll(), visitationConfigManager.Get());
        journalManager.CreateJournal();

        return new ResponseStatusDTO(StatusTypes.OK, journalManager.Get());
    }

    /**
     * На данный момент метод не верен.
     * Должен возвращать даты исходя из семестра.
     * Сейчас даты заданы в коде метода GET.
     * @param lessonId ID занятия
     * @return ResponseStatusDTO
     */
    public ResponseStatusDTO GetDates(long lessonId) {
        JournalDTO journal = journalDataService.Get(lessonId);
        journal.setDates(journal.getDates().stream().sorted().collect(Collectors.toList()));
        return new ResponseStatusDTO(StatusTypes.OK, journal.getDates());
    }

    public ResponseStatusDTO SaveJournal(JournalDTO journal) {

        VisitationConfigDTO visitConfig = visitationConfigDataService.GetByLesson(journal.getLesson().getId());
        visitationConfigManager.Init(visitConfig);

        List<LessonEventDTO> lessonEvents = lessonEventDataService.GetAll();
        lessonEventListManager.Init(lessonEvents);

        journalManager.Init(journal, lessonEventListManager.GetAll(), visitationConfigManager.Get());

        ResponseStatusDTO result = journalManager.Validate();
        if(result.getStatus() == StatusTypes.ERROR) return result;

        try {
            result.setStatus(StatusTypes.OK);
            journalDataService.Save(journalManager.Get());
            result.addMessage("Журнал сохранен.");
            return result;
        }
        catch (Exception e)
        {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("При сохранении журнала произошла ошибка");
            result.addErrors(e.getMessage());
            return result;
        }
    }

    public ResponseStatusDTO GetEvents(long lessonId) {

        lessonEventListManager.Init(lessonEventDataService.GetByLesson(lessonId));

        return new ResponseStatusDTO(StatusTypes.OK, lessonEventListManager.GetAll());
    }

    public ResponseStatusDTO SaveEvent(LessonEventDTO event) {
        lessonEventManager.Init(event);
        ResponseStatusDTO result = lessonEventManager.Validate();

        if(result.getStatus() == StatusTypes.ERROR) return result;
        try {
            lessonEventDataService.Save(lessonEventManager.Get());
            result.setStatus(StatusTypes.OK);
            result.addMessage("Событие сохранено.");
            return result;
        }
        catch (Exception e)
        {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("При сохранении события произошла ошибка");
            result.addErrors(e.getMessage());
            return result;
        }
    }

    public ResponseStatusDTO DeleteEvent(long id) {
        ResponseStatusDTO result = new ResponseStatusDTO();
        try {
            result.setStatus(StatusTypes.OK);
            lessonEventDataService.Delete(id);
            result.addMessage("Событие удалено.");
            return result;
        }
        catch (Exception e)
        {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("При удалении события произошла ошибка");
            result.addErrors(e.getMessage());
            return result;
        }
    }

    public ResponseStatusDTO SaveVisitationConfig(VisitationConfigDTO visit) {
        visitationConfigManager.Init(visit);
        ResponseStatusDTO result = visitationConfigManager.Validate();

        if(result.getStatus() == StatusTypes.ERROR) return result;
        try {
            visitationConfigDataService.Save(visitationConfigManager.Get());
            result.setStatus(StatusTypes.OK);
            result.addMessage("Настройки посещаемости сохранены.");
            return result;
        }
        catch (Exception e)
        {
            result.setStatus(StatusTypes.ERROR);
            result.addErrors("При сохранении настроек произошла ошибка");
            result.addErrors(e.getMessage());
            return result;
        }
    }

    public ResponseStatusDTO GetVisitationConfig(long lessonId) {

        VisitationConfigDTO visitationConfig = visitationConfigDataService.GetByLesson(lessonId);

        if (visitationConfig == null) {
            return new ResponseStatusDTO(StatusTypes.OK);
        }

        return new ResponseStatusDTO(StatusTypes.OK, visitationConfig);

    }

}
