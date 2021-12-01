package com.sgveteris.coincalculator.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
public class WalletDTO implements Serializable {

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss", timezone = "UTC")
    private Instant createdAt;
    private float amount;
    private String fiat;
    private String crypto;
    private float total;
}