package com.example.demo.spring.rest;

import com.example.demo.spring.service.ServiceException;

import java.util.List;

/**
 * Processor на услугите предоставени от RestApi
 */
public interface RestApiProcessor {
    List<RestPerson> searchPeople(String filter);
    RestPerson createPerson(RestPerson restPerson) throws RestApiException, ServiceException;
    RestPerson updatePerson(RestPerson restPerson) throws RestApiException, ServiceException;
    void deletePerson(Long id) throws ServiceException;
    List<RestPerson> findPersonsByYearAndBiggerThan1000Salaries(int year);
    List<RestYearSum> findSalarySumInYearsByMonth(int month);
}
