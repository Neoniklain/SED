package com.unesco.core.controller;

import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.managers.journal.lessonEvent.interfaces.lessonEvent.ILessonEventManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.journal.JournalModel;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.services.journal.journal.IJournalDataService;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/journal")
public class JournalController {

    @Autowired
    private IJournalDataService journalDataService;
    @Autowired
    private IJournalManager journalManager;
    @Autowired
    private ILessonEventManager lessonEventManager;
    @Autowired
    private ILessonEventDataService lessonEventDataService;

    @GetMapping("/{lessonId}")
    public ResponseStatus GetJournal(@PathVariable("lessonId") long lessonId) {

        JournalModel journal = journalDataService.Get(lessonId);

        journalManager.Init(journal);
        journalManager.InitEmptyCells(lessonEventDataService.GetAll());

        return new ResponseStatus(StatusTypes.OK, journalManager.Get());
    }

    @RequestMapping("/dates/{lessonId}")
    public ResponseStatus GetDates(@PathVariable("lessonId") long lessonId) {

        JournalModel journal = journalDataService.Get(lessonId);

        journalManager.Init(journal);
        journalManager.InitEmptyCells(lessonEventDataService.GetAll());

        return new ResponseStatus(StatusTypes.OK, journalManager.GetDates());

    }

    @RequestMapping("/save")
    public ResponseStatus SaveJournal(@RequestBody JournalModel journal) {

        journalManager.Init(journal);
        ResponseStatus result = journalManager.Validate();
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

    @RequestMapping("/event/lesson/{lessonId}")
    public ResponseStatus GetEvents(@PathVariable("lessonId") long lessonId) {

        List<LessonEventModel> pairEventModels = lessonEventDataService.GetByLesson(lessonId);

        return new ResponseStatus(StatusTypes.OK, pairEventModels);
    }

    @RequestMapping("/event/save")
    public ResponseStatus SaveEvent(@RequestBody LessonEventModel event) {
        lessonEventManager.Init(event);
        ResponseStatus result = lessonEventManager.Validate();
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

    @RequestMapping("/event/delete/{id}")
    public ResponseStatus SaveEvent(@PathVariable("id") long id) {
        ResponseStatus result = new ResponseStatus();
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
