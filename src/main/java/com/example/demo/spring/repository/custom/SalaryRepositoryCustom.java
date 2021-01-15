package com.example.demo.spring.repository.custom;

import com.example.demo.spring.entity.Salary;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Salary Custom Repository
 */
public interface SalaryRepositoryCustom {

    /**
     * За подаден месец да се сумират заплатите за всички служители и да се визуализират всички години в обратен ред.
     * @param month
     * @return
     */
    @Query(nativeQuery = true, value = "select salary.YEAR, SUM(salary.SALARY) from T_SALARY salary where salary.MONTH = ?1 GROUP BY salary.YEAR ORDER BY salary.YEAR DESC")
    List<Object[]> findSalarySumInYearsByMonth(int month);

}
