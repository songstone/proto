package com.example.proto.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommentLevel {
    S("시니어"),
    J("주니어");

    private final String description;
}
