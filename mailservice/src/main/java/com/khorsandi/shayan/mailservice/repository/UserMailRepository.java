package com.khorsandi.shayan.mailservice.repository;

import com.khorsandi.shayan.mailservice.domain.UserMail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMailRepository extends PagingAndSortingRepository<UserMail, Integer> {

    UserMail findByUsername(String username);
}
