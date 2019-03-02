package com.unesco.core.services.dataService.moodleService;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.repositories.moodlerest.MoodleCohort;
import com.unesco.core.repositories.moodlerest.MoodleCourse;
import com.unesco.core.repositories.moodlerest.MoodleUser;

import java.util.List;

public interface IMoodleService {
    /**
     * Получить список всех курсов из Moodle
     * @return
     */
    MoodleCourse[] GetAllCourses();

    /**
     * Получить список всех пользователей Moodle
     * @return
     */
    MoodleUser[] GetAllUsers();

    /**
     * Получить список всех групп Moodle
     * @return
     */
    MoodleCohort[] GetAllGroups();

    /**
     * Создать курсы
     * Максимально сырой сервис, созданный для теста
     * @return
     */
    MoodleCourse[] CreateCourses();

    /**
     * Регистрация пользователей-судентов в Moodle
     * @param students Список студентов, которых необходимо зарегестрировать
     * @return
     */
    MoodleUser[] CreateStudents(List<StudentDTO> students, boolean LineByLineCreation);

    /**
     * Регистрация пользователей-судентов в Moodle
     * @param students Список студентов, которых необходимо зарегестрировать
     * @return
     */
    MoodleUser[] CreateStudents(List<StudentDTO> students);

    /**
     * Создание учебных групп в Moodle
     * @param groups Список групп, которые необходимо создать
     * @return
     */
    MoodleCohort[] CreateGroups(List<GroupDTO> groups, boolean LineByLineCreation);

    /**
     * Создание учебных групп в Moodle
     * @param groups Список групп, которые необходимо создать
     * @return
     */
    MoodleCohort[] CreateGroups(List<GroupDTO> groups);

    /**
     * Привязать студентов к глобальным группам в Moodle
     * @param students Список студентов, которых необходимо привязать
     * @return true - если метод выполнен успешно
     */
    boolean AddUsersToCohorts(List<StudentDTO> students, boolean LineByLineCreation);

    /**
     * Привязать студентов к глобальным группам в Moodle
     * @param students Список студентов, которых необходимо привязать
     * @return true - если метод выполнен успешно
     */
    boolean AddUsersToCohorts(List<StudentDTO> students);
}
