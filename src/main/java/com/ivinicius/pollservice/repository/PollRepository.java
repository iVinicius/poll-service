package com.ivinicius.pollservice.repository;

import com.ivinicius.pollservice.model.entity.Poll;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends ReactiveMongoRepository<Poll, String> {
}
