package com.unesco.core.ViewModel;

public class RoleViewModel {

    public String roleName;

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleViewModel(String roleName) {
        this.roleName = roleName;
    }
}
