package com.sgveteris.coincalculator.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TimeOutException extends RuntimeException {
    private String resourceName;
    private Object fieldValue;

    public TimeOutException(String resourceName, Object fieldValue) {
        super(String.format("price has expired %s : '%s'", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
