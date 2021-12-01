package com.sgveteris.coincalculator.controller;

import com.sgveteris.coincalculator.model.dto.TransactionRequest;
import com.sgveteris.coincalculator.model.dto.TransactionSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public interface CalculatorController {

    @GetMapping("currencies")
    ResponseEntity<?> getCurrencies();

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody @Valid TransactionSaveRequest transactionSaveRequest);

    @PostMapping("calculate")
    ResponseEntity<?> getTicker(@RequestBody @Valid TransactionRequest transactionRequest);
}