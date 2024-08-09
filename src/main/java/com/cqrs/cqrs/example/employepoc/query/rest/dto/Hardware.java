package com.cqrs.cqrs.example.employepoc.query.rest.dto;


import com.cqrs.cqrs.example.employepoc.command.rest.dto.CommType;
import com.cqrs.cqrs.example.employepoc.command.rest.dto.Direction;
import com.cqrs.cqrs.example.employepoc.command.rest.dto.SupportedDevice;
import com.hydatis.cqrsref.domain.BaseEntity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Document(collection = "hardware")
@Builder
public class Hardware extends BaseEntity implements Serializable {
   @Id
    private String id;
    private static final long serialVersionUID = 1L;

    private Direction direction = Direction.NONE;
    private SupportedDevice type;
    private String serialNumber;

    private CommType commType = CommType.TCP;
    private String trigger;
 private String ipAddress;
    private int portNumber;
    private LocalDateTime date;

    @ElementCollection
    @CollectionTable(name = "Hardware_map", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")

    private Map<String, String> otherAttributes = new HashMap<>();


}
