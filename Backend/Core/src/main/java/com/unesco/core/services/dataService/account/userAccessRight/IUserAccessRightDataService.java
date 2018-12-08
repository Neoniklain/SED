package com.unesco.core.services.dataService.account.userAccessRight;

import com.unesco.core.dto.account.UserAccessRightDTO;
import com.unesco.core.dto.enums.AccessRightType;

import java.util.List;

public interface IUserAccessRightDataService {
    /**
     * Сохраняет права пользователя в базе
     * @param t сохраняемая сущность
     * @return
     */
    UserAccessRightDTO save(UserAccessRightDTO t);

    /**
     * Сохраняет права пользователя в базе
     * @param t сохраняемая сущность
     * @return
     */
    boolean save(long userId, AccessRightType rightType, boolean allow);

    /**
     * Удаляет право для пользователя
     * @param userId id пользователя
     * @param rightType Тип права доступа
     */
    void delete(long userId, AccessRightType rightType);

    /**
     * Получает права для пользователя
     * @param userId id пользователя
     * @return
     */
    UserAccessRightDTO get(long userId);

    /**
     * Получает права для всех пользователей
     * @return
     */
    List<UserAccessRightDTO> getAll();
}
