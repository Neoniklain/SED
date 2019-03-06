package com.unesco.core.controllerWeb;

import com.unesco.core.controller.JournalController;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public ResponseStatusDTO getJournal(@PathVariable("lessonId") long lessonId,
                                        @RequestParam int month,
                                        @RequestParam String forDate,
                                        @RequestParam int semester,
                                        @RequestParam int year) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(forDate);
        } catch (ParseException e) {
            Logger.getLogger("JournalControllerWeb").log(Level.ALL, "Could not parse date");
            Logger.getLogger("JournalControllerWeb").log(Level.ALL, e.getMessage());
        }
        return journalController.getJournal(lessonId, month, date, semester, year);
    }

    @RequestMapping("/dates/{lessonId}")
    public ResponseStatusDTO getDates(@PathVariable("lessonId") long lessonId,
                                      @RequestParam int semester,
                                      @RequestParam int year) {
        return journalController.getDates(lessonId, semester, year);
    }

    @RequestMapping("/save")
    public ResponseStatusDTO saveJournal(@RequestBody JournalDTO journal) {
        return journalController.saveJournal(journal);
    }

    @RequestMapping("/history/{lessonId}")
    public ResponseStatusDTO getJournalHistoryDate(@PathVariable("lessonId") long lessonId,
                                                   @RequestParam int semester,
                                                   @RequestParam int year) {
        return journalController.getJournalHistoryDate(lessonId, semester, year);
    }

    @RequestMapping("/event/lesson/{lessonId}")
    public ResponseStatusDTO getEvents(@PathVariable("lessonId") long lessonId) {
        return journalController.getEvents(lessonId);
    }

    @RequestMapping("/event/save")
    public ResponseStatusDTO saveEvent(@RequestBody LessonEventDTO event) {
        return journalController.saveEvent(event);
    }

    @RequestMapping("/visitation/saveConfig")
    public ResponseStatusDTO saveVisitationConfig(@RequestBody VisitationConfigDTO configDTO) {
        return journalController.saveVisitationConfig(configDTO);
    }

    @RequestMapping("/visitation/lesson/{lessonId}")
    public ResponseStatusDTO getVisitationConfig(@PathVariable("lessonId") long lessonId) {
        return journalController.getVisitationConfig(lessonId);
    }

    @RequestMapping("/event/delete/{id}")
    public ResponseStatusDTO deleteEvent(@PathVariable("id") long id) {
        return journalController.deleteEvent(id);
    }

    @RequestMapping("/report/certification/{id}")
    public ResponseStatusDTO getCertificationReport(@PathVariable("id") long id,
                                                    @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                    @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
                                                    @RequestParam int semester,
                                                    @RequestParam int year) {
        return journalController.getCertificationReport(id, start, end, semester, year);
    }
}
