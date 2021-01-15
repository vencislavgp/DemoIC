package com.example.demo.spring.service;

import com.example.demo.spring.entity.Mail;

import java.util.List;

/**
 * услуги предоставени за Mail
 */
public interface MailService {
    /**
     * търсене на Mail по идентификатор
     * @param id
     * @return
     */
    Mail findById(Long id);
    List<Mail> findByPersonId(long id);
    Mail save(Mail mail);
    void deleteById(long id);
}
