package com.unesco.core.config.MoodleConfig;

public interface IMoodleConfigService {
    /**
     * Получить адрес подключения к Moodle
     * @return
     */
    String GetURL();

    /**
     * Получить токен для авторизации
     * Необходимо для получения доступа к RestAPI
     * @return
     */
    String GetToken();
}
