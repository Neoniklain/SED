package com.unesco.core.repositories.account;

import com.unesco.core.entities.account.UserToAccessRightEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserToAccessRightRepository extends CrudRepository<UserToAccessRightEntity, Long>, CrudPagableRepository<UserToAccessRightEntity, Long> {


    List<UserToAccessRightEntity> findAllByUserId(long id);

    UserToAccessRightEntity findByUserIdAndRightId(long userId,long rightId);

}
