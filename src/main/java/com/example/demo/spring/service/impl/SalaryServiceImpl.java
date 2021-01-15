package com.example.demo.spring.service.impl;

import com.example.demo.spring.repository.SalaryRepository;
import com.example.demo.spring.service.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Salary Service implementation
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class SalaryServiceImpl implements SalaryService {
    private SalaryRepository salaryRepository;

    public SalaryRepository getSalaryRepository() {
        return salaryRepository;
    }
    @Resource
    public void setSalaryRepository(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }
}
