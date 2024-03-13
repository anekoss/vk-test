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

    private OffsetDateTime timestamp;
    private String username;
    private boolean hasAccess;
    private String requestParams;
    private long executionTime;
    private int responseCode;
    private String responseMessage;
    private String uri;

}
