package com.sgveteris.coincalculator.model.entity.redis;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@RedisHash(value = "ticker", timeToLive = 100)
@Data
@Builder
public class TickerMem implements Serializable {

    @Id
    private String id;
    private String from;
    private String to;
    private float last_trade_price;
}