package com.unesco.core.services.dataService.account.accessRightService;

import com.unesco.core.dto.account.AccessRightDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.enums.AccessRightType;
import com.unesco.core.services.dataService.IDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccessRightDataService extends IDataService<AccessRightDTO> {
    AccessRightDTO getByType(AccessRightType name);
    List<AccessRightDTO> getPage(FilterQueryDTO filter);
}
