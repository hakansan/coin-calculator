package com.sgveteris.coincalculator.service;

import com.sgveteris.coincalculator.config.CalculatorProperties;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class BaseService {

    @Autowired
    protected CalculatorProperties calculatorProperties;
    @Autowired
    protected ModelMapper modelMapper;
}