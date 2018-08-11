package com.unesco.core.services.dataService;

import java.util.List;

public interface IDataService<T> {
    /**
     * Сохраняет сущность в базе данных и возвращает её
     * @param t сохраняемая сущность
     * @return
     */
    T save(T t);
    /**
     * Удаляет сущность из базы данных
     * @param id id удаляемой сущности
     */
    void delete(long id);

    /**
     * Получает сущность из базы данных
     * @param id id искомой сущности
     * @return
     */
    T get(long id);

    /**
     * Получает список всех объектов данной сущности
     * @return
     */
    List<T> getAll();
}
