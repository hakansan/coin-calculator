package com.sgveteris.coincalculator.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
@Data
@EqualsAndHashCode(of = "id")
public class Wallet extends BaseEntity {

    private float amount;
    private String fiat;
    private String crypto;
    private float total;
}