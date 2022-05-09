package com.tugrulaslan.graphql.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public record HistoricalPrice(@JsonProperty("id") @Id Integer id,
                              @JsonProperty("symbol") String symbol,
                              @JsonProperty("time") Integer time,
                              @JsonProperty("price") Float price,
                              @JsonProperty("volume") Float volume) {
}
