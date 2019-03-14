package com.unesco.core.config.MoodleConfig;

import org.springframework.stereotype.Service;


@Service
public class MoodleConfigService implements IMoodleConfigService {

    public String GetURL(){
        // Удалённое подключение (Prod)
        //return "http://localhost/moodle/webservice/rest/server.php";

        // Локальное подключение (Dev)
        return "http://localhost/moodle/webservice/rest/server.php";
    }

    public String GetToken() {
        // Токен удалённого подключения (Prod)
        //return "0696880e69359bb66a713931a44c9974";

        // Токен локального подключения (Dev)
        return "0696880e69359bb66a713931a44c9974";
    }
}
