package com.unesco.core.controller;

import com.unesco.core.managers.journal.journalManager.interfaces.journal.IJournalManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.journal.JournalModel;
import com.unesco.core.services.journal.journal.IJournalDataService;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ILessonEventDataService lessonEventDataService;

    @GetMapping("/professor/{professorId}/group/{groupId}/discipline/{disciplineId}")
    public ResponseStatus GetJournal(@PathVariable("professorId") long professorId,
                                   @PathVariable("groupId") long groupId,
                                   @PathVariable("disciplineId") long disciplineId) {

        JournalModel journal = journalDataService.Get(professorId, groupId, disciplineId);

        journalManager.Init(journal);
        journalManager.InitEmptyCells(lessonEventDataService.GetAll());

        return new ResponseStatus(StatusTypes.OK, journalManager.Get());
    }

    @RequestMapping("/save")
    public ResponseStatus SaveJournal(@RequestBody JournalModel journal) {

        ResponseStatus result = new ResponseStatus();

        try {
            result.setStatus(StatusTypes.OK);
            journalDataService.Save(journal);
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
}
