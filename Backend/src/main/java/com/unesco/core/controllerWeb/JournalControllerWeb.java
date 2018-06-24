package com.unesco.core.controllerWeb;

import com.unesco.core.controller.JournalController;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.journal.JournalDTO;
import com.unesco.core.models.journal.LessonEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/journal")
public class JournalControllerWeb {

    @Autowired
    private JournalController journalController;

    @GetMapping("/{lessonId}")
    public ResponseStatusDTO GetJournal(@PathVariable("lessonId") long lessonId) {
        return journalController.GetJournal(lessonId);
    }

    @RequestMapping("/dates/{lessonId}")
    public ResponseStatusDTO GetDates(@PathVariable("lessonId") long lessonId) {
        return journalController.GetJournal(lessonId);
    }

    @RequestMapping("/save")
    public ResponseStatusDTO SaveJournal(@RequestBody JournalDTO journal) {
        return journalController.SaveJournal(journal);
    }

    @RequestMapping("/event/lesson/{lessonId}")
    public ResponseStatusDTO GetEvents(@PathVariable("lessonId") long lessonId) {
        return journalController.GetEvents(lessonId);
    }

    @RequestMapping("/event/save")
    public ResponseStatusDTO SaveEvent(@RequestBody LessonEventDTO event) {
        return journalController.SaveEvent(event);
    }

    @RequestMapping("/event/delete/{id}")
    public ResponseStatusDTO DeleteEvent(@PathVariable("id") long id) {
        return journalController.DeleteEvent(id);
    }
}
