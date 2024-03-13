package com.vk.redirector.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Table
@Entity(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    private boolean userHasPrivilege;

    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime time;
    private String path;

}
