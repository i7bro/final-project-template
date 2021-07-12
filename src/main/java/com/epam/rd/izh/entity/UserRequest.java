package com.epam.rd.izh.entity;

import com.epam.rd.izh.util.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usersRequests")
public class UserRequest {

    @Id
    Integer id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
    @OneToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    Trip trip;
    State state;
}
