package com.example.demo.spring.rest;

import javax.persistence.Column;

/**
 * Rest представянето на Address
 */
public class RestAddress {
    private long id;

    private String type;

    private String info;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
