package com.khorsandi.shayan.mailservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sent_mails")
public class SentMail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private UserMail mail;

    @Column
    private String text;

    @Enumerated(EnumType.STRING)
    private MailType mailType;

    public SentMail(UserMail mail, String text, MailType mailType) {
        this.mail = mail;
        this.text = text;
        this.mailType = mailType;
    }

    public SentMail () {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserMail getMail() {
        return mail;
    }

    public void setMail(UserMail mail) {
        this.mail = mail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }
}
