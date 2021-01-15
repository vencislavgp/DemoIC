package com.example.demo.spring.service.impl;

import com.example.demo.spring.entity.Mail;
import com.example.demo.spring.repository.MailRepository;
import com.example.demo.spring.service.MailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Mail Service implementation
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class MailServiceImpl implements MailService {
    private MailRepository mailRepository;

    @Override
    public Mail findById(Long id) {
        return mailRepository.findById(id).orElse(null);
    }

    @Override
    public List<Mail> findByPersonId(long id) {
        return mailRepository.findMailsByPersonId(id);
    }

    @Override
    public Mail save(Mail mail) {
        return mailRepository.save(mail);
    }

    @Override
    public void deleteById(long id) {
        mailRepository.deleteById(id);
    }

    public MailRepository getMailRepository() {
        return mailRepository;
    }
    @Resource
    public void setMailRepository(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }
}
