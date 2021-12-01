package com.sgveteris.coincalculator.controller;

import com.sgveteris.coincalculator.model.dto.TransactionRequest;
import com.sgveteris.coincalculator.model.dto.TransactionResponse;
import com.sgveteris.coincalculator.model.dto.TransactionSaveRequest;
import com.sgveteris.coincalculator.model.dto.WalletDTO;
import com.sgveteris.coincalculator.model.entity.Currency;
import com.sgveteris.coincalculator.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CalculatorControllerImpl implements CalculatorController {

    private final CalculatorService calculatorService;

    @Override
    public ResponseEntity<List<Currency>> getCurrencies() {
        return ResponseEntity.ok(calculatorService.currencyList());
    }

    @Override
    public ResponseEntity<WalletDTO> save(TransactionSaveRequest transactionSaveRequest) {
        return ResponseEntity.ok(calculatorService.save(transactionSaveRequest));
    }

    @Override
    public ResponseEntity<TransactionResponse> getTicker(TransactionRequest transactionRequest) {
        return ResponseEntity.ok(calculatorService.getTicker(transactionRequest));
    }
}