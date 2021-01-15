package com.example.demo.spring.rest;

import com.example.demo.spring.entity.Address;
import com.example.demo.spring.entity.Mail;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Rest представянето на Person
 */
public class RestPerson {
    private long id;

    private String fullName;

    private String pin;

    List<RestMail> mails;

    List<RestAddress> addresses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<RestMail> getMails() {
        return mails;
    }

    public void setMails(List<RestMail> mails) {
        this.mails = mails;
    }

    public List<RestAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<RestAddress> addresses) {
        this.addresses = addresses;
    }
}
