package com.unesco.core.dto.journal;

import com.unesco.core.dto.shedule.LessonDTO;

import java.util.List;

public class StudentJournalList {

    private List<StudentJournalDTO> studentJournal;
    private LessonDTO lesson;

    public List<StudentJournalDTO> getStudentJournal() {
        return studentJournal;
    }
    public void setStudentJournal(List<StudentJournalDTO> studentJournal) {
        this.studentJournal = studentJournal;
    }

    public LessonDTO getLesson() {
        return lesson;
    }
    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }
}
