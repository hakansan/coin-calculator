package com.sgveteris.coincalculator.service;

import com.sgveteris.coincalculator.model.dto.TransactionRequest;
import com.sgveteris.coincalculator.model.dto.TransactionResponse;
import com.sgveteris.coincalculator.model.dto.TransactionSaveRequest;
import com.sgveteris.coincalculator.model.dto.WalletDTO;
import com.sgveteris.coincalculator.model.entity.Currency;

import java.util.List;

public interface CalculatorService {

    List<Currency> currencyList();

    WalletDTO save(TransactionSaveRequest transactionSaveRequest);

    TransactionResponse getTicker(TransactionRequest transactionRequest);
}