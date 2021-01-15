package com.example.demo.spring.entity;

import javax.persistence.*;

/**
 * Обекти Person от таблицата T_PEOPLE
 */
@Entity()
@Table(name = "T_PEOPLE")
public class Person {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FULL_NAME", length = 90)
    private String fullName;

    @Column(name = "PIN", length = 10)
    private String pin;

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

}
