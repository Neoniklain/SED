package com.unesco.core.controller;

import com.unesco.core.models.journal.Journal;
import com.unesco.core.models.journal.JournalCell;
import com.unesco.core.models.journal.Student;
import com.unesco.core.services.journalDataService.IJournalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/jurnal")
public class JournalController {

    @Autowired
    @Qualifier("journalDataService") /* Указываю реализацию интерфейса, если ее не укзать то
                                        выпадет исключени о том, что найдено две реализации
                                         (IJournalDataService и JournalDataService)*/
    private IJournalDataService journalDataService;

    @GetMapping("/all")
    public Journal GetJournal() {
        Journal journal = journalDataService.getJournal();
        List<JournalCell> CellsForDate = journalDataService.getCellsForDate(10);
        List<JournalCell> CellsForStudent = journalDataService.getCellsForStudent(new Student("Василий Пупкин"));
        return journal;
    }
}
