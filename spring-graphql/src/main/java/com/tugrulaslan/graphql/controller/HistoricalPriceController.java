package com.tugrulaslan.graphql.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugrulaslan.graphql.dto.Trade;
import com.tugrulaslan.graphql.entity.HistoricalPrice;
import com.tugrulaslan.graphql.repository.HistoricalPriceRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Controller
public class HistoricalPriceController {

    private final HistoricalPriceRepository repository;

    public HistoricalPriceController(HistoricalPriceRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Flux<HistoricalPrice> historicalPrices() {
        return repository.findAll();
    }

    @QueryMapping
    public Flux<HistoricalPrice> historicalPricesBySymbol(@Argument String symbol) {
        return repository.findBySymbol(symbol);
    }

    @MutationMapping
    Mono<HistoricalPrice> addHistoricalPrice(@Argument String symbol, @Argument Integer time,
                                           @Argument Float price, @Argument Float volume) {
        return repository.save(new HistoricalPrice(null, symbol, time, price, volume));
    }

    @SubscriptionMapping
    Flux<String> lastTradingPrice() {
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
        EmitterProcessor<String> output = EmitterProcessor.create();
        Mono<Void> sessionMono = client.execute(URI.create("wss://stream.binance.com:9443/ws/btcusdt@trade"),
                session -> session.receive()
                        .timeout(Duration.ofSeconds(3))
                        .map(WebSocketMessage::getPayloadAsText)
                        .map(this::mapPrice)
                        .subscribeWith(output)
                        .then());
        return output.doOnSubscribe(s -> sessionMono.subscribe());
    }

    private String mapPrice(String s) {
        ObjectMapper mapper = new ObjectMapper();
        Trade price = null;
        try {
            price = mapper.readValue(s, Trade.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return price.price();
    }
}
