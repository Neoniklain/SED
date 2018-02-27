package com.unesco.core.srvices.dataInterface;

import com.unesco.core.ViewModel.Journal.Journal;
import com.unesco.core.ViewModel.Journal.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalDataInterface
{
   public JournalDataInterface() {
   }

   public Journal getJoutnal() {
      List<Student> students = new ArrayList<>();
      int[] days = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
      students.add(new Student("Василий Пупкин"));
      students.add(new Student("Пуп Васильевич"));
      students.add(new Student("Пупилий Василькин"));
      students.add(new Student("Артем Савов"));
      students.add(new Student("Сав Мартемов"));
      return new Journal(students, days);
   }

}
