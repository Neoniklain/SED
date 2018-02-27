package com.unesco.core.controller;

import com.unesco.core.ViewModel.JSONResponseStatus;
import com.unesco.core.ViewModel.Journal.Journal;
import com.unesco.core.entities.News;
import com.unesco.core.repositories.NewsRepository;
import com.unesco.core.repositories.UserRepository;
import com.unesco.core.srvices.CustomUserDetailsService;
import com.unesco.core.srvices.dataInterface.JournalDataInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@CrossOrigin
@RestController
@RequestMapping("api/jurnal")
public class JournalController {

    @Autowired
    private JournalDataInterface _JournalDataInterface;

    @GetMapping("/all")
    public Journal GetJournal() {
        Journal journal = _JournalDataInterface.getJoutnal();
        return journal;
    }
}
