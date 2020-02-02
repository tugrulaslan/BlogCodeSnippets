package com.tugrulaslan.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commonName;
    private String algorithm;
}
