package com.example.demo.spring.repository;

import com.example.demo.spring.entity.Salary;
import com.example.demo.spring.repository.custom.SalaryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Salary Jpa Repository
 */
public interface SalaryRepository extends SalaryRepositoryCustom, JpaRepository<Salary, Long> {
}
