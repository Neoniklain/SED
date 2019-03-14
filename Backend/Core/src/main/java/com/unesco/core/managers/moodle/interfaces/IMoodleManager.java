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
     * Получить пользователя Moodle
     * @param userId id пользователя этого web-приложения
     * @return
     */
    MoodleUser GetUserById(long userId);

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
     * @param groupId Id группы, студентов которой необходимо создать в Moodle
     * @return
     */
    MoodleUser[] CreateStudents(long groupId);

    /**
     * Регистрация пользователя в Moodle
     * @param userId id пользователя этого web-приложения, которого необходимо зарегестрировать
     * @return
     */
    MoodleUser CreateUser(long userId);

    /**
     * Регистрация всех пользователей-судентов в Moodle
     * @return
     */
    MoodleUser[] CreateAllStudents();

    /**
     * Создание всех учебных групп в Moodle
     * @return
     */
    MoodleCohort[] CreateGroups();

    /**
     * Привязать студентов к глобальным группам в Moodle
     * @param groupId Id группы, студентов которой необходимо связать в Moodle
     * @return true - если метод выполнен успешно
     */
    boolean AssignStudentsOnGroups(long groupId);

    /**
     * Привязать всех студентов к соответствующим глобальным группам в Moodle
     * @return true - если метод выполнен успешно
     */
    boolean AllAssignStudentsOnGroups();
}
