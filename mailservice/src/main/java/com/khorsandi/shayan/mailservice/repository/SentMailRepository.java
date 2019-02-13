package com.khorsandi.shayan.mailservice.repository;

import com.khorsandi.shayan.mailservice.domain.SentMail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentMailRepository extends PagingAndSortingRepository<SentMail, Integer> {
}
