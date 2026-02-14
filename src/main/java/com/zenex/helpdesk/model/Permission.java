package com.zenex.helpdesk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;
    // DASHBOARD_VIEW, USER_CREATE, REPORT_VIEW, etc
}
