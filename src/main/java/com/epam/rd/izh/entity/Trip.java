package com.epam.rd.izh.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trips")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Trip {

    @Id
    private Integer id;
    private Integer tourId;
    private Integer freeSpots;
    private LocalDateTime arriveDate;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, String> instructors;
}
