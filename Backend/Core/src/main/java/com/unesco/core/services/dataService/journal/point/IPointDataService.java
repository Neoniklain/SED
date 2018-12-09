package com.unesco.core.services.dataService.journal.point;

import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.Date;
import java.util.List;

public interface IPointDataService extends IDataService<PointDTO> {

    /**
     * Поучает отметки для студента и по указанному занятию за определенный период
     * @param studentId ID студента
     * @param pairId ID Занятия
     * @param from Начало периода
     * @param to Конец периода
     * @return Список отметок DTO
     */
    List<PointDTO> getByStudentAndPairBetweenDate(long studentId, long pairId, Date from, Date to);

    /**
     * Поучает отметки для студента и по указанному занятию за определенный период по состоянию на указанную дату
     * @param studentId ID студента
     * @param pairId ID Занятия
     * @param from Начало периода
     * @param to Конец периода
     * @param date Дата за которую взять состояние журнала
     * @return Список отметок DTO
     */
    List<PointDTO> getByStudentAndPairBetweenDate(long studentId, long pairId, Date from, Date to, Date date);

    /**
     * Поучает все отметки указанному занятию
     * отличается от getByLesson тем, что получает не послденюю созданную отметку, а все.
     * Нужно для формирования истории.
     * @param lessonId ID Занятия
     * @return Список отметок DTO
     */
    List<PointDTO> getAllByLesson(long lessonId);

    /**
     * Поучает отметки указанному занятию
     * @param lessonId ID Занятия
     * @return Список отметок DTO
     */
    List<PointDTO> getByLesson(long lessonId);

    /**
     * Поучает отметки указанному занятию по состоянию на указанную дату
     * @param lessonId ID Занятия
     * @param date Дата за которую взять состояние журнала
     * @return Список отметок DTO
     */
    List<PointDTO> getByLesson(long lessonId, Date date);

    /**
     * Находит соответствующюю отметку в журнале
     * @param studentId ID студента
     * @param date Дата в которую должна стоять отметка
     * @param typeId ID типа отметки
     * @param pairId ID занятия
     * @param dateOfCreate Дата создания отметки
     * @return Отметка DTO
     */
    PointDTO getEqualPoint(long studentId, Date date, long typeId, long pairId, Date dateOfCreate);

}
