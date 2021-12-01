package com.sgveteris.coincalculator.service.blockchain;

import com.sgveteris.coincalculator.model.payload.Ticker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "blockchain-service", url = "${com.sgveteris.properties.blockchain-api-base-url}")
public interface BlockchainService {

    @RequestMapping("{pair}")
    Ticker getTicker(@PathVariable String pair);
}