package com.unesco.core.config.MoodleConfig;

import org.springframework.stereotype.Service;


@Service
public class MoodleConfigService implements IMoodleConfigService {

    public String GetURL(){
        // Удалённое подключение (Prod)
        // Вариант №1
        return "http://192.168.253.9/moodle/webservice/rest/server.php";
        // Вариант №2
        // return "http://ictkem.ru:84/moodle/webservice/rest/server.php";

        // Локальное подключение (Dev)
        //return "http://localhost/moodle/webservice/rest/server.php";
    }

    public String GetToken() {
        // Токен удалённого подключения (Prod)
        return "b6d863d7db8c12e55df671ffc7b23f1b";

        // Токен локального подключения (Dev)
        //return "0696880e69359bb66a713931a44c9974";
    }
}
