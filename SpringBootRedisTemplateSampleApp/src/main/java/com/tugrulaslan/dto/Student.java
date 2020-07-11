package com.tugrulaslan.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public final class Student implements Serializable {
    private static final long serialVersionUID = 5087223005039923556L;

    private final String id;
    private final String name;
}
