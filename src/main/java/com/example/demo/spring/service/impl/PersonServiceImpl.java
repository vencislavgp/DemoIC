package com.example.demo.spring.service.impl;

import com.example.demo.spring.entity.Person;
import com.example.demo.spring.repository.PersonRepository;
import com.example.demo.spring.service.PersonService;
import com.example.demo.spring.service.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Person Service implementation
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List findByFilter(String filter) {
        if(StringUtils.isBlank(filter)){
            return personRepository.findAll();
        }
        return personRepository.findPersonsByFilter("%"+filter+"%");
    }

    @Override
    public List<Person> findPersonsByYearAndBiggerSalaries(int year, BigDecimal minSalary) {
        return personRepository.findPersonsByYearAndBiggerSalaries(year,minSalary);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Person save(Person entity) throws ServiceException {
        return personRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Person entity) throws ServiceException {
        personRepository.delete(entity);
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }
    @Resource
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
