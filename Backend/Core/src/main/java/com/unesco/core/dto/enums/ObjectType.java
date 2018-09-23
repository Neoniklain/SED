package com.unesco.core.dto.enums;

public enum ObjectType {
    /**
     * Описание задачи
     */
    TaskDescription("TaskDescription"),
    /**
     * Реализация задачи юзером
     */
    TaskUser("TaskUser");

    /**
     * Получить значение enum по её ID
     * @param id номер enum
     * @return null, если такого объекта нет в enum
     */
    public static ObjectType getById(long id) {
        for (ObjectType e : values()) {
            if (e.ordinal() == id)
                return e;
        }
        return null;
    }

    private final String text;

    ObjectType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
