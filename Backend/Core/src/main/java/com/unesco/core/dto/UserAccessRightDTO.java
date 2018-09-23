package com.unesco.core.dto;

import com.unesco.core.dto.account.UserDTO;

import java.util.List;

public class UserAccessRightDTO {

    private UserDTO user;
    private List<UserAccessRowDTO> rights;

    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<UserAccessRowDTO> getRights() {
        return rights;
    }
    public void setRights(List<UserAccessRowDTO> rights) {
        this.rights = rights;
    }

}