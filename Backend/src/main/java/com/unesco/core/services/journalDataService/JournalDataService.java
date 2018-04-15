package com.unesco.core.services.journalDataService;

import com.unesco.core.entities.Student;
import com.unesco.core.models.StudentModel;
import com.unesco.core.models.journal.Journal;
import com.unesco.core.models.journal.JournalCell;
import com.unesco.core.repositories.account.StudentRepository;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JournalDataService implements IJournalDataService
{
   @Autowired
   private IUserService userService;
   @Autowired
   private StudentRepository studentRepository;
   @Autowired
   private IMapperService mapperService;

   public JournalDataService() {}

   public Journal getJournal(int proffesorId, int groupId)
   {
      List<Student> studentsEntity = studentRepository.findAllByGroupId(groupId);
      List<StudentModel> students = new ArrayList<StudentModel>();
      for (Student s : studentsEntity) {
         students.add((StudentModel) mapperService.toModel(s));
      }
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DAY_OF_MONTH, 1);
      int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      List<Integer> days = new ArrayList<Integer>();
      for(int i = 1; i <= maxDay; i++)
      {
         days.add(i);
      }
      Journal journal = new Journal(students, days);
      return journal;
   }

}
