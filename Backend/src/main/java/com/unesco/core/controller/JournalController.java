package com.unesco.core.controller;

import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.services.journal.journal.IJournalDataService;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalController {

    @Autowired
    private IJournalDataService journalDataService;
    @Autowired
    private IJournalManager journalManager;
    @Autowired
    private ILessonEventManager lessonEventManager;
    @Autowired
    private ILessonEventDataService lessonEventDataService;

    public ResponseStatusDTO GetJournal(long lessonId) {

        JournalDTO journal = journalDataService.Get(lessonId);

        journalManager.Init(journal);
        journalManager.InitEmptyCells(lessonEventDataService.GetAll());

        return new ResponseStatusDTO(StatusTypes.OK, journalManager.Get());
    }

    public ResponseStatusDTO GetDates(long lessonId) {

        JournalDTO journal = journalDataService.Get(lessonId);

        journalManager.Init(journal);
        journalManager.InitEmptyCells(lessonEventDataService.GetAll());

        return new ResponseStatusDTO(StatusTypes.OK, journalManager.GetDates());

    }

    public ResponseStatusDTO SaveJournal(JournalDTO journal) {

        journalManager.Init(journal);
        ResponseStatusDTO result = journalManager.Validate();
        if(result.getStatus() != StatusTypes.OK) return result;

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

        List<LessonEventDTO> pairEventModels = lessonEventDataService.GetByLesson(lessonId);

        return new ResponseStatusDTO(StatusTypes.OK, pairEventModels);
    }

    public ResponseStatusDTO SaveEvent(LessonEventDTO event) {
        lessonEventManager.Init(event);
        ResponseStatusDTO result = lessonEventManager.Validate();
        if(result.getStatus() != StatusTypes.OK) return result;
        try {
            result.setStatus(StatusTypes.OK);
            lessonEventDataService.Save(lessonEventManager.Get());
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
}
