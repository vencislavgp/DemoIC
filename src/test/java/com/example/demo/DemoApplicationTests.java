package com.example.demo;

import com.example.demo.spring.entity.Mail;
import com.example.demo.spring.entity.Person;
import com.example.demo.spring.repository.SalaryRepository;
import com.example.demo.spring.rest.RestApiException;
import com.example.demo.spring.rest.RestApiProcessor;
import com.example.demo.spring.rest.RestMail;
import com.example.demo.spring.rest.RestPerson;
import com.example.demo.spring.service.PersonService;
import com.example.demo.spring.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	RestApiProcessor restApiProcessor;
	PersonService personService;
	SalaryRepository salaryRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void restApiProcessor_cudPerson() throws RestApiException, ServiceException {
		RestPerson restPerson = new RestPerson();
		restPerson.setFullName("Test");
		restPerson.setPin("0123456789");
		restPerson.setMails(new ArrayList<>());
		restPerson.setAddresses(new ArrayList<>());
		{
			RestMail mail = new RestMail();
			mail.setEmailType("test");
			mail.setEmail("test@test.com");
			restPerson.getMails().add(mail);
		}
		restPerson=restApiProcessor.createPerson(restPerson);
		Assert.notNull(restPerson,"restPerson");
		Assert.isTrue(restPerson.getId()!=0,"restPerson.getId");
		{
			restPerson.setFullName("Test Test");
			restPerson.setPin("0000000000");
			{
				RestMail mail = new RestMail();
				mail.setEmailType("test");
				mail.setEmail("test2@test.com");
				restPerson.getMails().add(mail);
			}
			restPerson=restApiProcessor.updatePerson(restPerson);
		}
		restApiProcessor.deletePerson(restPerson.getId());
	}
	@Test
	void personService_findPersonsByYearAndBiggerSalaries() {
		List<Person> people=personService.findPersonsByYearAndBiggerSalaries(2019,new BigDecimal(1000));
		Assert.notNull(people,"people");
		people=personService.findPersonsByYearAndBiggerSalaries(2020,new BigDecimal(1000));
		Assert.notNull(people,"people");
	}
	@Test
	void findSalarySumInYearsByMonth() {
		List<Object[]> list = salaryRepository.findSalarySumInYearsByMonth(2);
		Assert.notNull(list,"people");
	}
	public RestApiProcessor getRestApiProcessor() {
		return restApiProcessor;
	}
	@Resource
	public void setRestApiProcessor(RestApiProcessor restApiProcessor) {
		this.restApiProcessor = restApiProcessor;
	}

	public PersonService getPersonService() {
		return personService;
	}

	@Resource
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public SalaryRepository getSalaryRepository() {
		return salaryRepository;
	}
	@Resource
	public void setSalaryRepository(SalaryRepository salaryRepository) {
		this.salaryRepository = salaryRepository;
	}
}
