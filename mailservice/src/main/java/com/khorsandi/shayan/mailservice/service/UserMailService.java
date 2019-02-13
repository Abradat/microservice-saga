package com.khorsandi.shayan.mailservice.service;

import com.khorsandi.shayan.mailservice.domain.UserMail;
import com.khorsandi.shayan.mailservice.repository.UserMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserMailService {

    @Autowired
    private UserMailRepository userMailRepository;

    public UserMail verifyMailById(int mailId) throws NoSuchElementException {
        try{
            return userMailRepository.findById(mailId).orElseThrow(() -> new NoSuchElementException("Mail id does not exists with id" + mailId));

        } catch (NoSuchElementException e) {
            return null;
        }

    }
}
