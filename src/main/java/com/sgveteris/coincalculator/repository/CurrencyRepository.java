package com.sgveteris.coincalculator.repository;

import com.sgveteris.coincalculator.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findBySymbol(String symbol);
}