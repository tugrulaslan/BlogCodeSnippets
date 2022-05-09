package com.tugrulaslan.graphql.repository;

import com.tugrulaslan.graphql.entity.HistoricalPrice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface HistoricalPriceRepository extends ReactiveCrudRepository<HistoricalPrice, Integer> {
    Flux<HistoricalPrice> findBySymbol(String symbol);
}
