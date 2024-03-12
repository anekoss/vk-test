package com.vk.redirector.entity;

import com.vk.redirector.domain.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PACKAGE;

@Entity
@Table(name = "roles")
@Getter
@Setter(PACKAGE)
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private RoleType type;

}