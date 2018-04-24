package com.unesco.core.services.journalDataService;

import com.unesco.core.entities.Student;
import com.unesco.core.models.PairModel;
import com.unesco.core.models.StudentModel;
import com.unesco.core.models.journal.Journal;
import com.unesco.core.models.journal.JournalCell;
import com.unesco.core.repositories.account.StudentRepository;
import com.unesco.core.services.mapperService.IMapperService;
import com.unesco.core.services.sheduleService.ISheduleService;
import com.unesco.core.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JournalDataService implements IJournalDataService
{
   @Autowired
   private IUserService userService;
   @Autowired
   private StudentRepository studentRepository;
   @Autowired
   private IMapperService mapperService;
   @Autowired
   private ISheduleService sheduleService;

   public JournalDataService() {}

   public Journal getJournal(int proffesorId, int groupId, int disciplineId)
   {
      PairModel pair = sheduleService.getPair(proffesorId, groupId, disciplineId);

      Calendar starDate = Calendar.getInstance();
      Calendar endDate = Calendar.getInstance();

      starDate.set(2018,8,1);
      endDate.set(2018,11,28);
      switch (pair.getDayofweek()) {
         case "Понедельник":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
         case "Вторник":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
         case "Среда":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
         case "Четверг":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
         case "Пятница":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
         case "Суббота":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
         case "Воскресенье":
            starDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            starDate.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            break;
      }

      DateFormat df = new SimpleDateFormat("dd.MM");
      List<String> days = new ArrayList<String>();

      // для четных/нечетных недель
      if(!pair.getWeektype().equals("Все"))
      {
         if(pair.getWeektype().equals("Чет")) {
            days.add(df.format(starDate.getTime()));
            starDate.add(Calendar.DAY_OF_WEEK, 14); //Прибавляем две недели
         } else {
            starDate.add(Calendar.DAY_OF_WEEK, 7); //Прибавляем неделю
            days.add(df.format(starDate.getTime()));
            starDate.add(Calendar.DAY_OF_WEEK, 14); //Прибавляем две недели
         }
      }

      int amount = pair.getWeektype().equals("Все") ? 7 : 14;
      for(int i = 0; starDate.getTime().compareTo(endDate.getTime()) < 0; i++){
         days.add(df.format(starDate.getTime()));
         starDate.add(Calendar.DAY_OF_WEEK, amount);
      }

      List<Student> studentsEntity = studentRepository.findAllByGroupId(groupId);
      List<StudentModel> students = new ArrayList<StudentModel>();
      for (Student s : studentsEntity) {
         students.add((StudentModel) mapperService.toModel(s));
      }

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DAY_OF_MONTH, 1);
      int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

      Journal journal = new Journal(students, days);
      return journal;
   }

}
