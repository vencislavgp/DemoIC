package com.example.demo.spring.rest;

import javax.persistence.Column;

/**
 * Rest представянето на Mail
 */
public class RestMail {
    private long id;

    private String emailType;

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
