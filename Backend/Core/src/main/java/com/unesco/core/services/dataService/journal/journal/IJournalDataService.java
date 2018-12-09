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
     * @return Журнал
     */
    JournalDTO get(long lessonId, Date date);

    /**
     * олучить журнал за месяц
     * @param lessonId id занятия
     * @param month номер месяца
     * @param date Дата за которую нужно получить журнал
     * @return Журнал
     */
    JournalDTO getForMonth(long lessonId, int month, Date date);

    /**
     * Получить даты истории журнала
     * @param lessonId id занятия
     * @return Набор дат когда был сохранен журнал
     */
    List<Date> getHistoryDates(long lessonId);

    ResponseStatusDTO<JournalDTO> save(JournalDTO journal);
}
