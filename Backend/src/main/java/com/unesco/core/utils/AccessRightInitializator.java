package com.unesco.core.utils;

import com.unesco.core.dto.AccessRightDTO;
import com.unesco.core.dto.enums.AccessRightType;
import com.unesco.core.services.dataService.account.accessRightService.IAccessRightDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Класс для инициализации прав доступа и приведения их в сопоставление с
 * перечислением AccessRightType
 */
@Component
public class AccessRightInitializator implements ApplicationRunner {

    @Autowired
    private IAccessRightDataService accessRightDataService;

    public void run(ApplicationArguments args) {
        for (AccessRightType type:AccessRightType.values()) {
            AccessRightDTO find = accessRightDataService.getByType(type);
            if(find == null) {
                AccessRightDTO newType = new AccessRightDTO();
                newType.setName(type.toString());
                accessRightDataService.save(newType);
            }
        }
    }
}
