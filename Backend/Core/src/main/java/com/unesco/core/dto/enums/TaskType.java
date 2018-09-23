package com.unesco.core.dto.enums;

public enum TaskType {
    /**
     * Без уведомления
     */
    Info("Info"),
    /**
     * С уведомлением о прочтении
     */
    Notice("Notice"),
    /**
     * С ответом
     */
    Answer("Answer");

    /**
     * Получить значение enum по её ID
     * @param id номер enum
     * @return null, если такого объекта нет в enum
     */
    public static TaskType getById(long id) {
        for (TaskType e : values()) {
            if (e.ordinal() == id)
                return e;
        }
        return null;
    }

    private final String text;

    TaskType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
