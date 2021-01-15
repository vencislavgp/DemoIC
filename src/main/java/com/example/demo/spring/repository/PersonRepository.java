package com.example.demo.spring.repository;

import com.example.demo.spring.entity.Person;
import com.example.demo.spring.repository.custom.PersonRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person Jpa Repository
 */
public interface PersonRepository extends PersonRepositoryCustom, JpaRepository<Person, Long> {
}
