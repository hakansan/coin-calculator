package com.sgveteris.coincalculator.service;

import com.sgveteris.coincalculator.exception.BadRequestException;
import com.sgveteris.coincalculator.exception.TimeOutException;
import com.sgveteris.coincalculator.model.dto.TransactionRequest;
import com.sgveteris.coincalculator.model.dto.TransactionResponse;
import com.sgveteris.coincalculator.model.dto.TransactionSaveRequest;
import com.sgveteris.coincalculator.model.dto.WalletDTO;
import com.sgveteris.coincalculator.model.entity.Currency;
import com.sgveteris.coincalculator.model.entity.Wallet;
import com.sgveteris.coincalculator.model.entity.redis.TickerMem;
import com.sgveteris.coincalculator.model.payload.Ticker;
import com.sgveteris.coincalculator.repository.CalculatorRepository;
import com.sgveteris.coincalculator.repository.CurrencyRepository;
import com.sgveteris.coincalculator.repository.redis.TickerMemRepository;
import com.sgveteris.coincalculator.service.blockchain.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl extends BaseService implements CalculatorService {

    private final CurrencyRepository currencyRepository;
    private final CalculatorRepository calculatorRepository;
    private final BlockchainService blockchainClient;
    private final TickerMemRepository tickerMemRepository;

    @Override
    public List<Currency> currencyList() {
        return currencyRepository.findAll();
    }

    @Override
    public WalletDTO save(TransactionSaveRequest transactionSaveRequest) {
        TickerMem tickerMem = tickerMemRepository.findById(transactionSaveRequest.getCacheId())
                .orElseThrow(() -> new TimeOutException("cacheId", transactionSaveRequest.getCacheId()));
        WalletDTO walletDTO = WalletDTO.builder()
                .amount(transactionSaveRequest.getValue())
                .crypto(tickerMem.getFrom())
                .fiat(tickerMem.getTo())
                .total(transactionSaveRequest.getValue() / tickerMem.getLast_trade_price())
                .build();
        Wallet wallet = modelMapper.map(walletDTO, Wallet.class);
        calculatorRepository.save(wallet);
        walletDTO.setCreatedAt(wallet.getCreatedAt());
        return walletDTO;
    }

    @Override
    public TransactionResponse getTicker(TransactionRequest transactionRequest) {
        Map<String, Currency> currencyMap = validCurrency(transactionRequest.getFrom(), transactionRequest.getTo());
        var pair = currencyMap.get(transactionRequest.getFrom()).getSymbol().toUpperCase() + "-" +
                currencyMap.get(transactionRequest.getTo()).getSymbol().toUpperCase();
        Ticker ticker = blockchainClient.getTicker(pair);
        TickerMem tickerMem = TickerMem.builder()
                .last_trade_price(ticker.getLast_trade_price())
                .from(currencyMap.get(transactionRequest.getFrom()).getName())
                .to(currencyMap.get(transactionRequest.getTo()).getName())
                .build();
        var cacheId = tickerMemRepository.save(tickerMem).getId();
        return TransactionResponse.builder()
                .from(transactionRequest.getFrom())
                .to(transactionRequest.getTo())
                .calculatedAmount(transactionRequest.getValue() / tickerMem.getLast_trade_price())
                .date(Instant.now())
                .value(transactionRequest.getValue())
                .cacheId(cacheId)
                .build();
    }

    private Map<String, Currency> validCurrency(String from, String to) {
        Currency fromCurrency = currencyRepository.findBySymbol(from)
                .orElseThrow(() -> new BadRequestException("invalid symbol: " + from));
        Currency toCurrency = currencyRepository.findBySymbol(to)
                .orElseThrow(() -> new BadRequestException("invalid symbol: " + to));
        Map<String, Currency> currencyMap = new HashMap<>();
        currencyMap.put(from, fromCurrency);
        currencyMap.put(to, toCurrency);
        return currencyMap;
    }
}