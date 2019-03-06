package com.unesco.core.services.dataService.journal.journal;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.JournalDTO;

import java.util.Date;
import java.util.List;

public interface IJournalDataService {
    /**
     * Получить журнал за весь период
     * @param lessonId id занятия
     * @param date Дата за которую нужно получить журнал
     * @param semester Семестр обучения
     * @param year Год обучения
     * @return Журнал
     */
    JournalDTO get(long lessonId, Date date, int semester, int year);

    /**
     * олучить журнал за месяц
     * @param lessonId id занятия
     * @param month номер месяца
     * @param date Дата за которую нужно получить журнал
     * @param semester Семестр обучения
     * @param year Год обучения
     * @return Журнал
     */
    JournalDTO getForMonth(long lessonId, int month, Date date, int semester, int year);

    /**
     * Получить даты истории журнала
     * @param lessonId id занятия
     * @param semester Семестр обучения
     * @param year Год обучения
     * @return Набор дат когда был сохранен журнал
     */
    List<Date> getHistoryDates(long lessonId, int semester, int year);

    ResponseStatusDTO<JournalDTO> save(JournalDTO journal);
}
