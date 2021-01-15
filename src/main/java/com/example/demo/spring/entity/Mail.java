package com.example.demo.spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Обекти Mail от таблицата T_MAILS
 */
@Entity()
@Table(name = "T_MAILS")
public class Mail {
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

    @Column(name = "EMAIL_TYPE", length = 5)
    private String emailType;

    @Column(name = "EMAIL", length = 40)
    private String email;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
