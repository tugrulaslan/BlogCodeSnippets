package com.tugrulaslan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public final class CertificateOrderDto {
    private String commonName;
    private String algorithm;
}

