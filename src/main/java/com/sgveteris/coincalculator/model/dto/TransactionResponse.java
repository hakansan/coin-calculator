package com.sgveteris.coincalculator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
public class TransactionResponse implements Serializable {

    private float value;
    private String from;
    private String to;
    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss", timezone = "UTC")
    private Instant date;
    private float calculatedAmount;
    private String cacheId;
}