package com.ivinicius.pollservice.repository;

import com.ivinicius.pollservice.model.entity.Subject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends ReactiveMongoRepository<Subject, String> {
}
