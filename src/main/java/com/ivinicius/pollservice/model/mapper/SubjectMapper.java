package com.ivinicius.pollservice.model.mapper;

import com.ivinicius.pollservice.api.v1.request.SubjectRequest;
import com.ivinicius.pollservice.model.entity.Subject;

public class SubjectMapper {

    public static Subject map(SubjectRequest from){
        return Subject.builder()
                .description(from.getDescription())
                .build();
    }
}
