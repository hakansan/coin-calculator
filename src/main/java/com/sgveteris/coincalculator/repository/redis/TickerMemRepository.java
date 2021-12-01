package com.sgveteris.coincalculator.repository.redis;

import com.sgveteris.coincalculator.model.entity.redis.TickerMem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerMemRepository extends JpaRepository<TickerMem, String> {
}
