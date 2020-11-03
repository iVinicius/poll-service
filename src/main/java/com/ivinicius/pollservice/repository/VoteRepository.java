package com.ivinicius.pollservice.repository;

import com.ivinicius.pollservice.model.entity.Vote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends ReactiveMongoRepository<Vote, String> {
}
