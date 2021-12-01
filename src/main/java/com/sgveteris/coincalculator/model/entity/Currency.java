package com.sgveteris.coincalculator.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
@Data
@EqualsAndHashCode(of = "id")
public class Currency extends BaseEntity {

    private String symbol;
    private String name;
    private boolean isSell;
    private boolean isBuy;
}