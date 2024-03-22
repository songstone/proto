package com.example.proto.dto;

import java.util.List;

public record PageInfo(List<?> list, Long totalCount) {
    public static PageInfo of(List<?> list, Long totalCount) {
        return new PageInfo(list, totalCount);
    }
}
