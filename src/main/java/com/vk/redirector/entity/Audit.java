package com.vk.redirector.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private OffsetDateTime timestamp;
    @Column(name = "username")
    private String username;

    @Column(name = "access")
    private boolean access;

    @Column(name = "params")
    private String requestParams;

    @Column(name = "response_code")
    private int responseCode;

    @Column(name = "uri")
    private String uri;

}
