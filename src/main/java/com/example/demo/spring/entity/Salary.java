package com.example.demo.spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Обекти Salary от таблицата T_SALARY
 */
@Entity()
@Table(name = "T_SALARY")
public class Salary {
    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "T_PEOPLE_ID")
    private Person person;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "MONTH")
    private int month;

    @Column(name = "SALARY", precision = 10, scale = 2)
    private BigDecimal salary;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
