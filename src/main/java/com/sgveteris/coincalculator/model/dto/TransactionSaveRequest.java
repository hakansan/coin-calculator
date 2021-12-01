package com.sgveteris.coincalculator.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
public class TransactionSaveRequest implements Serializable {

    @Range(min = 25, max = 5000)
    private float value;
    private String cacheId;
}
