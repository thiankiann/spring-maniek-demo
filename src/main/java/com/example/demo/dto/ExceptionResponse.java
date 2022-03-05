package com.example.demo.dto;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private LocalDateTime timeStamp;
    private int responseStatus;
    private String error;
    private String message;
    private String patch;

    public ExceptionResponse(LocalDateTime timeStamp, int responseStatus, String error, String message, String patch) {
        this.timeStamp = timeStamp;
        this.responseStatus = responseStatus;
        this.error = error;
        this.message = message;
        this.patch = patch;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPatch() {
        return patch;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "timeStamp=" + timeStamp +
                ", responseStatus=" + responseStatus +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
