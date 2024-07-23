package com.cqrs.cqrs.example.employepoc.query.rest.dto;

import com.hydatis.cqrsref.domain.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "device")
@Builder

public class Device extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;

}