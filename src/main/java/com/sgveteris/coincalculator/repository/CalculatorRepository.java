package com.sgveteris.coincalculator.repository;

import com.sgveteris.coincalculator.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorRepository extends JpaRepository<Wallet, Long> {
}