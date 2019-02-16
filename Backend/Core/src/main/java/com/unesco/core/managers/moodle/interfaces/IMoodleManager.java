package com.unesco.core.managers.moodle.interfaces;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.task.TaskDescriptionDTO;
import com.unesco.core.dto.task.TaskUserDTO;
import com.unesco.core.repositories.moodlerest.MoodleCohort;
import com.unesco.core.repositories.moodlerest.MoodleCourse;
import com.unesco.core.repositories.moodlerest.MoodleUser;

import java.util.List;

public interface IMoodleManager {
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
     * @return
     */
    MoodleUser[] CreateStudents();

    /**
     * Создание учебных групп в Moodle
     * @return
     */
    MoodleCohort[] CreateGroups();

    /**
     * Привязать студентов к глобальным группам в Moodle
     * @return true - если метод выполнен успешно
     */
    boolean AssignStudentsOnGroups();
}
