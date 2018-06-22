package com.unesco.core.models.additional;

import com.unesco.core.utils.StatusTypes;

import java.util.ArrayList;
import java.util.List;

public class ResponseStatusDTO {

    private StatusTypes status;
    private Object data;
    private List<String> warnings;
    private List<String> message;
    private List<String> errors;

    public ResponseStatusDTO() {
        warnings = new ArrayList<>();
        message = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public ResponseStatusDTO(StatusTypes stat) {
        status = stat;
        warnings = new ArrayList<>();
        message = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public ResponseStatusDTO(StatusTypes stat, Object dataObject) {
        status = stat;
        data = dataObject;
        warnings = new ArrayList<>();
        message = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public ResponseStatusDTO(StatusTypes stat, String error) {
        status = stat;
        warnings = new ArrayList<>();
        message = new ArrayList<>();
        errors = new ArrayList<>();
        errors.add(error);
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public StatusTypes getStatus() {
        return status;
    }
    public void setStatus(StatusTypes status) {
        this.status = status;
    }

    public void addWarning(String msg) {
        warnings.add(msg);
    }
    public void addMessage(String msg) {
        message.add(msg);
    }
    public void addErrors(String msg) {
        errors.add(msg);
    }

    public List<String> getWarnings() {
        return warnings;
    }
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
