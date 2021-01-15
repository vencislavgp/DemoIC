package com.example.demo.spring.repository.custom;

import com.example.demo.spring.entity.Person;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Person Custom Repository
 */
public interface PersonRepositoryCustom {
    /**
     * Търсене на лице по име – търсенето по име не трябва да зависи от начина на изписване в базата данни на името – Case Insensitive search;
     * @param filter
     * @return
     */
    @Query(nativeQuery = true, value = "select person.* from T_PEOPLE person where person.FULL_NAME ILIKE ?1 ORDER BY person.FULL_NAME")
    List<Person> findPersonsByFilter(String filter);

    /**
     * Да се извадят за посочена година всички хора, които са взели заплата над определена сума(1000лв).
     * @param year
     * @param minSalary
     * @return
     */
    @Query(nativeQuery = true, value = "select distinct person.* from T_PEOPLE person inner join T_SALARY salary ON salary.T_PEOPLE_ID=person.ID where salary.YEAR = ?1 AND salary.SALARY > ?2")
    List<Person> findPersonsByYearAndBiggerSalaries(int year, BigDecimal minSalary);
}
