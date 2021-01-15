package com.example.demo.spring.repository;

import com.example.demo.spring.entity.Mail;
import com.example.demo.spring.repository.custom.MailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Mail Jpa Repository
 */
public interface MailRepository extends MailRepositoryCustom,JpaRepository<Mail, Long> {
}
