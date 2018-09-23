package com.unesco.core.dto;

public class UserAccessRowDTO {

    private AccessRightDTO right;
    private boolean allow;

    public AccessRightDTO getRight() {
        return right;
    }
    public void setRight(AccessRightDTO right) {
        this.right = right;
    }

    public boolean isAllow() {
        return allow;
    }
    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}

