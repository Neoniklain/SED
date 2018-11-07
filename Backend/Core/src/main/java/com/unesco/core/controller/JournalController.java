package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.dto.journal.CertificationReportDTO;
import com.unesco.core.managers.journal.VisitationConfigManager.interfaces.IVisitationConfigManager;
import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEventList.ILessonEventListManager;
import com.unesco.core.services.dataService.journal.journal.IJournalDataService;
import com.unesco.core.services.dataService.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.services.dataService.journal.visitation.IVisitationConfigDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public ResponseStatusDTO getJournal(long lessonId, int month) {

        JournalDTO journal = journalDataService.getForMonth(lessonId, month);

        VisitationConfigDTO visitConfig = visitationConfigDataService.getByLesson(lessonId);
        visitationConfigManager.init(visitConfig);

        List<LessonEventDTO> lessonEvents = lessonEventDataService.getAll();
        lessonEventListManager.init(lessonEvents);

        journalManager.init(journal, lessonEventListManager.getAll(), visitationConfigManager.get());
        journalManager.CreateJournal();

        return new ResponseStatusDTO(StatusTypes.OK, journalManager.get());
    }

    /**
     * На данный момент метод не верен.
     * Должен возвращать даты исходя из семестра.
     * Сейчас даты заданы в коде метода get.
     * @param lessonId ID занятия
     * @return ResponseStatusDTO
     */
    public ResponseStatusDTO getDates(long lessonId) {
        JournalDTO journal = journalDataService.get(lessonId);
        return new ResponseStatusDTO(StatusTypes.OK, journal.getComparison().stream().map(x -> x.getDate()).collect(Collectors.toList()));
    }

    public ResponseStatusDTO saveJournal(JournalDTO journal) {

        VisitationConfigDTO visitConfig = visitationConfigDataService.getByLesson(journal.getLesson().getId());
        visitationConfigManager.init(visitConfig);

        List<LessonEventDTO> lessonEvents = lessonEventDataService.getAll();
        lessonEventListManager.init(lessonEvents);

        journalManager.init(journal, lessonEventListManager.getAll(), visitationConfigManager.get());

        ResponseStatusDTO result = journalManager.validate();
        if(result.getStatus() == StatusTypes.ERROR) return result;

        try {
            result.setStatus(StatusTypes.OK);
            journalDataService.save(journalManager.get());
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

    public ResponseStatusDTO getEvents(long lessonId) {

        lessonEventListManager.init(lessonEventDataService.getByLesson(lessonId));

        return new ResponseStatusDTO(StatusTypes.OK, lessonEventListManager.getAll());
    }

    public ResponseStatusDTO saveEvent(LessonEventDTO event) {
        lessonEventManager.init(event);
        ResponseStatusDTO result = lessonEventManager.validate();

        if(result.getStatus() == StatusTypes.ERROR) return result;
        try {
            lessonEventDataService.save(lessonEventManager.get());
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

    public ResponseStatusDTO deleteEvent(long id) {
        ResponseStatusDTO result = new ResponseStatusDTO();
        try {
            result.setStatus(StatusTypes.OK);
            lessonEventDataService.delete(id);
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

    public ResponseStatusDTO saveVisitationConfig(VisitationConfigDTO visit) {
        visitationConfigManager.init(visit);
        ResponseStatusDTO result = visitationConfigManager.validate();

        if(result.getStatus() == StatusTypes.ERROR) return result;
        try {
            visitationConfigDataService.save(visitationConfigManager.get());
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

    public ResponseStatusDTO getVisitationConfig(long lessonId) {
        VisitationConfigDTO visitationConfig = visitationConfigDataService.getByLesson(lessonId);

        if (visitationConfig == null) {
            return new ResponseStatusDTO(StatusTypes.OK);
        }

        return new ResponseStatusDTO(StatusTypes.OK, visitationConfig);
    }

    public ResponseStatusDTO getCertificationReport(long lessonId, Date start, Date end) {

        JournalDTO journal = journalDataService.get(lessonId);

        VisitationConfigDTO visitConfig = visitationConfigDataService.getByLesson(lessonId);
        visitationConfigManager.init(visitConfig);

        List<LessonEventDTO> lessonEvents = lessonEventDataService.getAll();
        lessonEventListManager.init(lessonEvents);

        journalManager.init(journal, lessonEventListManager.getAll(), visitationConfigManager.get());
        CertificationReportDTO result = journalManager.CertificationReportDto(start, end);

        return new ResponseStatusDTO(StatusTypes.OK, result);
    }

}
