package com.example.demo.spring.service;

import com.example.demo.spring.entity.Person;

import java.math.BigDecimal;
import java.util.List;

/**
 * услуги предоставени за Person
 */
public interface PersonService {
    /**
     * търсене на Person по идентификатор
     * @param id
     * @return
     */
    Person findById(Long id);

    /**
     * запис на Person в базата данни
     * @param entity
     * @return
     * @throws ServiceException
     */
    Person save(Person entity) throws ServiceException;

    /**
     * изтриване на Person от базата данни
     * @param entity
     * @throws ServiceException
     */
    void delete(Person entity) throws ServiceException;

    /**
     * Търсене на лице по име и визуализиране на списък с намерени резултати – търсенето по име не трябва да зависи от начина на изписване в базата данни на името – Case Insensitive search;
     * @param filter
     * @return
     */
    List<Person> findByFilter(String filter);

    /**
     * Да се извадят за посочена година всички хора, които са взели заплата над определена сума.
     * @param year
     * @param minSalary
     * @return
     */
    List<Person> findPersonsByYearAndBiggerSalaries(int year, BigDecimal minSalary);
}
