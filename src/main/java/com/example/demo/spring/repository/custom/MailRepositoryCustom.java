package com.example.demo.spring.repository.custom;

import com.example.demo.spring.entity.Mail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MailRepositoryCustom {
    @Query(nativeQuery = true, value = "select mail.* from T_MAILS mail where mail.T_PEOPLE_ID = ?1")
    List<Mail> findMailsByPersonId(long personId);
}
