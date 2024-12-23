package com.zuko.crm.dto.exception;

import java.time.LocalDateTime;

public class RuntimeExceptionDTO {
    private  String message;
    private  String status;
    private LocalDateTime timestamp;

    public RuntimeExceptionDTO(String status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
