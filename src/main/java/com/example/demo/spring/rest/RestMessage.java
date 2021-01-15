package com.example.demo.spring.rest;

/**
 * Обект на съобщение в RestApi-то
 */
public class RestMessage {
    private String message;

    public RestMessage() {
    }

    public RestMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
