package com.khorsandi.shayan.mailservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_mails")
public class UserMail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column
    private boolean confirmed;

    public UserMail(String username, String mail) {
        this.username = username;
        this.mail = mail;
        this.confirmed = false;
    }

    public UserMail() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
