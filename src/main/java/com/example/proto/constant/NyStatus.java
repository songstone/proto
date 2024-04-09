package com.example.proto.constant;

import org.springframework.util.ObjectUtils;

public enum NyStatus {
    N, Y;

    public static NyStatus findByOrdinal(Integer ordinal) {
        if(ObjectUtils.isEmpty(ordinal)) return null;

        NyStatus[] values = NyStatus.values();
        if(ordinal >= values.length) return null;

        return values[ordinal];
    }
}
