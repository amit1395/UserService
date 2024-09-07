package com.fitstam.User.UserService.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="microusers")
public class User {

    @Id
    @Column(name="userid")
    private String userId;

    @Column(name="username")
    private String UserName;

    @Column(name="email")
    private String email;

    @Column(name="about")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
