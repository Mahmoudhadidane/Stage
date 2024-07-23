package com.cqrs.cqrs.example.employepoc.command.rest.dto;
import com.hydatis.cqrsref.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "device")
@Builder

public class Device extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;

}