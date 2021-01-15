package com.example.demo.spring.rest.impl;

import com.example.demo.spring.entity.Address;
import com.example.demo.spring.entity.Mail;
import com.example.demo.spring.entity.Person;
import com.example.demo.spring.repository.SalaryRepository;
import com.example.demo.spring.rest.*;
import com.example.demo.spring.service.AddressService;
import com.example.demo.spring.service.MailService;
import com.example.demo.spring.service.PersonService;
import com.example.demo.spring.service.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * RestApiProcessor implementation
 */
@Component
public class RestApiProcessorImpl implements RestApiProcessor {
    PersonService personService;
    AddressService addressService;
    MailService mailService;
    SalaryRepository salaryRepository;

    /**
     * Търсене на лице по име и визуализиране на списък с намерени резултати – търсенето по име не трябва
     * да зависи от начина на изписване в базата данни на името – Case Insensitive search;
     * @param filter
     * @return
     */
    @Override
    public List<RestPerson> searchPeople(String filter) {
        List<Person> people= personService.findByFilter(filter);
        List<RestPerson> personList = new ArrayList<>();
        for (Person person: people) {
            RestPerson restPerson=buildRestPerson(person);
            personList.add(restPerson);
        }
        return personList;
    }

    /**
     * Изгражда представянето на Person
     * @param person
     * @return
     */
    private RestPerson buildRestPerson(Person person) {
        RestPerson restPerson = new RestPerson();
        restPerson.setId(person.getId());
        restPerson.setFullName(person.getFullName());
        restPerson.setPin(person.getPin());
        List<RestMail> restMails = new ArrayList<>();
        List<Mail> mails= mailService.findByPersonId(person.getId());
        for (Mail mail: mails) {
            RestMail restMail= new RestMail();
            restMail.setId(mail.getId());
            restMail.setEmailType(mail.getEmailType());
            restMail.setEmail(mail.getEmail());
            restMails.add(restMail);
        }
        restPerson.setMails(restMails);
        List<RestAddress> restAddresses = new ArrayList<>();
        List<Address> addresses=addressService.findByPersonId(person.getId());
        for (Address address: addresses) {
            RestAddress restAddress = new RestAddress();
            restAddress.setId(address.getId());
            restAddress.setType(address.getType());
            restAddress.setInfo(address.getInfo());
            restAddresses.add(restAddress);
        }
        restPerson.setAddresses(restAddresses);
        return restPerson;
    }

    /**
     * Когато е попълнено да съдържа точно 10 цифри, като е допустима и водеща нула
     * @param pin
     * @throws RestApiException
     */
    protected void validatePin(String pin) throws RestApiException {
        if(StringUtils.isNoneBlank(pin)){
            if(StringUtils.length(pin)!=10) {
                throw new RestApiException("Невалиден PIN!");
            }
            if(!pin.matches("\\d+")){
                throw new RestApiException("Невалиден PIN!");
            }
        }
    }

    /**
     * Допуска единствено букви на латиница и кирилица, знака за интервал и тире
     * @param fullName
     * @throws RestApiException
     */
    protected void validateFullName(String fullName) throws RestApiException {
        if(StringUtils.isBlank(fullName)){
            throw new RestApiException("Името е задължително!");
        }
        if(StringUtils.length(fullName)>90) {
            throw new RestApiException("Името трябва да е до 90 знака!");
        }
        if(!fullName.matches("[a-zA-Zа-яА-Я -]+")){
            throw new RestApiException("Името не е валидно!");
        }
    }

    /**
     * Задължителен параметър до 5 знака
     * @param mailType
     * @throws RestApiException
     */
    protected void validateMailType(String mailType) throws RestApiException{
        if(StringUtils.isBlank(mailType)){
            throw new RestApiException("Типа на имеила е задължителен!");
        }
        if(StringUtils.length(mailType)>5) {
            throw new RestApiException("Типа на имеила е до 5 знака!");
        }
    }

    /**
     * Да допуска въвеждането на коректен e-mail адрес съгласно възприетия стандарт – пример: abv@abv.bg
     * @param email
     * @throws RestApiException
     */
    protected void validateEmail(String email) throws RestApiException{
        if(StringUtils.isNotBlank(email) && !email.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}")){
            throw new RestApiException("Невлиден електронен адрес!");
        }
    }

    /**
     * Валидира полетата на Mail
     * @param restMail
     * @throws RestApiException
     */
    protected void validate(RestMail restMail) throws RestApiException {
        validateMailType(restMail.getEmailType());
        validateEmail(restMail.getEmail());
    }

    /**
     * Задължителен параметър до 5 знака
     * @param type
     * @throws RestApiException
     */
    protected void validateAddressType(String type) throws RestApiException{
        if(StringUtils.isBlank(type)){
            throw new RestApiException("Типа на адреса е задължителен!");
        }
        if(StringUtils.length(type)>5) {
            throw new RestApiException("Типа на адреса е до 5 знака!");
        }
    }

    /**
     * Валидира полетата на Address
     * @param restAddress
     * @throws RestApiException
     */
    protected void validate(RestAddress restAddress) throws RestApiException {
        validateAddressType(restAddress.getType());
    }

    /**
     * Валидира полетата на Person
     * @param restPerson
     * @throws RestApiException
     */
    protected void validate(RestPerson restPerson) throws RestApiException {
        validateFullName(restPerson.getFullName());
        validatePin(restPerson.getPin());
        if(restPerson.getMails()!=null) {
            for (RestMail restMail : restPerson.getMails()) {
                validate(restMail);
            }
        }
        if(restPerson.getAddresses()!=null) {
            for (RestAddress restAddress : restPerson.getAddresses()) {
                validate(restAddress);
            }
        }
    }

    /**
     * Създаване на запис за лице в базата данни – ограниченията да бъдат дефинирани на ниво web услуга,
     * а наличието на валидационни проблеми да се визуализира с подходящи съобщения.
     * @param restPerson
     * @return
     * @throws RestApiException
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestPerson createPerson(RestPerson restPerson) throws RestApiException, ServiceException {
        validate(restPerson);
        Person person = new Person();
        setPerson(restPerson, person);
        person = personService.save(person);
        if(restPerson.getMails()!=null) {
            for (RestMail restMail : restPerson.getMails()) {
                Mail mail = new Mail();
                setMail(person, restMail, mail);
                mailService.save(mail);
            }
        }
        if(restPerson.getAddresses()!=null) {
            for (RestAddress restAddress : restPerson.getAddresses()) {
                Address address = new Address();
                setAddress(person, restAddress, address);
                addressService.save(address);
            }
        }
        return buildRestPerson(person);
    }

    /**
     * попълва параметрите на Address
     * @param person
     * @param restAddress
     * @param address
     */
    private void setAddress(Person person, RestAddress restAddress, Address address) {
        address.setPerson(person);
        address.setType(restAddress.getType());
        address.setInfo(restAddress.getInfo());
    }

    /**
     * попълва параметрите на Mail
     * @param person
     * @param restMail
     * @param mail
     */
    private void setMail(Person person, RestMail restMail, Mail mail) {
        mail.setPerson(person);
        mail.setEmailType(restMail.getEmailType());
        mail.setEmail(restMail.getEmail());
    }

    /**
     * попълва параметрите на Person
     * @param restPerson
     * @param person
     */
    private void setPerson(RestPerson restPerson, Person person) {
        person.setFullName(restPerson.getFullName());
        person.setPin(restPerson.getPin());
    }

    /**
     * Редактиране на съществуващ запис – след изпълнение на операция търсене, избран запис да се отвори за редактиране,
     * и нанесените изменения да се обработят със съответна операция за промяна – валидационните грешки
     * да се обработят от операцията аналогично на операцията по създаване на запис.
     * @param restPerson
     * @return
     * @throws RestApiException
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RestPerson updatePerson(RestPerson restPerson) throws RestApiException, ServiceException {
        validate(restPerson);
        Person person = personService.findById(restPerson.getId());
        setPerson(restPerson, person);
        person = personService.save(person);
        List<Mail> mails=mailService.findByPersonId(person.getId());
        if(restPerson.getMails()!=null) {
            for (RestMail restMail : restPerson.getMails()) {
                Mail mail = mails.stream().filter(e->e.getId().equals(restMail.getId())).findFirst().orElse(null);
                if(mail==null) {
                    mail = new Mail();
                } else {
                    mails.remove(mail);
                }
                setMail(person, restMail, mail);
                mailService.save(mail);
            }
        }
        mails.forEach(e->mailService.deleteById(e.getId()));

        List<Address> addresses=addressService.findByPersonId(person.getId());
        if(restPerson.getAddresses()!=null) {
            for (RestAddress restAddress : restPerson.getAddresses()) {
                Address address = addresses.stream().filter(e->e.getId().equals(restAddress.getId())).findFirst().orElse(null);
                if(address==null) {
                    address = new Address();
                } else {
                    addresses.remove(address);
                }
                setAddress(person, restAddress, address);
                addressService.save(address);
            }
        }
        addresses.forEach(e->addressService.deleteById(e.getId()));
        return buildRestPerson(person);
    }

    /**
     * Изтриване на избран запис - след изпълнение на операция търсене, избран запис да се изтрие.
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deletePerson(Long id) throws ServiceException {
        Person person = personService.findById(id);
        personService.delete(person);
    }
    public List<RestPerson> findPersonsByYearAndBiggerThan1000Salaries(int year) {
        List<Person> people= personService.findPersonsByYearAndBiggerSalaries(year,new BigDecimal(1000));
        List<RestPerson> personList = new ArrayList<>();
        for (Person person: people) {
            RestPerson restPerson=buildRestPerson(person);
            personList.add(restPerson);
        }
        return personList;
    }

    @Override
    public List<RestYearSum> findSalarySumInYearsByMonth(int month) {
        List<Object[]> list = salaryRepository.findSalarySumInYearsByMonth(month);
        List<RestYearSum> restList = new ArrayList<>();
        if(list!=null){
            for (Object[] field: list){
                RestYearSum value= new RestYearSum();
                value.setYear(((Number)field[0]).intValue());
                value.setSum(((Number)field[1]).doubleValue());
                restList.add(value);
            }
        }
        return restList;
    }

    public PersonService getPersonService() {
        return personService;
    }
    @Resource
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public AddressService getAddressService() {
        return addressService;
    }
    @Resource
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public MailService getMailService() {
        return mailService;
    }
    @Resource
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public SalaryRepository getSalaryRepository() {
        return salaryRepository;
    }
    @Resource
    public void setSalaryRepository(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }
}
