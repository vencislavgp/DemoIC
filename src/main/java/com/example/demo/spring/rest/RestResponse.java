package com.example.demo.spring.rest;

/**
 * Обект на резултата в RestApi-то с възможност за връщане на съобщение
 * @param <T>
 */
public class RestResponse<T> {
    private RestMessage message;
    private T data;

    public RestResponse() {
    }

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(RestMessage error) {
        this.message = error;
    }

    public RestMessage getMessage() {
        return message;
    }

    public void setMessage(RestMessage message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
