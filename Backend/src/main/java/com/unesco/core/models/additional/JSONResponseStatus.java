package com.unesco.core.models.additional;

public class JSONResponseStatus {
    public String status;
    public String message;

    JSONResponseStatus() { };

    public static JSONResponseStatus OK() {
        JSONResponseStatus responseStatus = new JSONResponseStatus();
        responseStatus.status = "ok";
        responseStatus.message = "";
        return responseStatus;
    }
    public static JSONResponseStatus OK(String message) {
        JSONResponseStatus responseStatus = new JSONResponseStatus();
        responseStatus.status = "ok";
        responseStatus.message = message;
        return responseStatus;
    }

    public static JSONResponseStatus ERROR() {
        JSONResponseStatus responseStatus = new JSONResponseStatus();
        responseStatus.status = "error";
        responseStatus.message = "";
        return responseStatus;
    }

    public static JSONResponseStatus ERROR(String message) {
        JSONResponseStatus responseStatus = new JSONResponseStatus();
        responseStatus.status = "error";
        responseStatus.message = message;
        return responseStatus;
    }

}
