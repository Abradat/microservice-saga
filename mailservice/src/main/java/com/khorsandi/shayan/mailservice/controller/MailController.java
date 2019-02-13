package com.khorsandi.shayan.mailservice.controller;

import com.khorsandi.shayan.mailservice.domain.UserMail;
import com.khorsandi.shayan.mailservice.repository.UserMailRepository;
import com.khorsandi.shayan.mailservice.service.UserMailService;
import com.khorsandi.shayan.mailservice.web.ConfirmMailRequest;
import com.khorsandi.shayan.mailservice.web.ConfirmMailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/mails")
public class MailController {

    private UserMailService userMailService;
    private UserMailRepository userMailRepository;

    @Autowired
    public MailController(UserMailService userMailService, UserMailRepository userMailRepository) {
        this.userMailService = userMailService;
        this.userMailRepository = userMailRepository;
    }

    protected MailController() {
    }

    @RequestMapping(path = "/{mailId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserMail> getUserMail(@PathVariable("mailId") int mailId) {
        UserMail userMail = userMailService.verifyMailById(mailId);

        if(userMail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<UserMail>(userMail ,HttpStatus.OK);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createMail(@Valid @RequestBody UserMail mail, UriComponentsBuilder ucBuilder) {
        userMailRepository.save(new UserMail(mail.getUsername(), mail.getMail()));
        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(ucBuilder.path("/mails/{mailId}").buildAndExpand(mail.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(path="/confirm", method = RequestMethod.PUT)
    public ResponseEntity<ConfirmMailResponse> confirmMail(@Valid @RequestBody ConfirmMailRequest confirmMailRequest) {
        UserMail userMail = userMailRepository.findByUsername(confirmMailRequest.getUsername());
        if(userMail == null) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        else {
            if(!userMail.isConfirmed()) {
                userMail.setConfirmed(true);
                userMailRepository.save(userMail);
            }
            return new ResponseEntity<>(new ConfirmMailResponse(userMail.getId()), HttpStatus.OK);
        }

    }


}
