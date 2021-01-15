package com.example.demo.spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Обекти Address от таблицата T_ADDRESSES
 */
@Entity()
@Table(name = "T_ADDRESSES")
public class Address {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "T_PEOPLE_ID")
    private Person person;

    @Column(name = "ADDR_TYPE", length = 5)
    private String type;

    @Column(name = "ADDR_INFO", length = 300)
    private String info;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
