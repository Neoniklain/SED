package com.unesco.core.models.plan;

public class CompetenceDTO {
    private long id;
    /** Поле кода */
    private String code;
    /** Поле описания */
    private String description;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
