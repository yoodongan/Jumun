package com.mihak.jumun.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_ID")
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDateTime signupAt;
    private boolean agree;
}
