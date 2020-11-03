package com.ivinicius.pollservice.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
public class Subject {

    @Id
    private String id;
    private String description;
}
