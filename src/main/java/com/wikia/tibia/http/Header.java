package com.wikia.tibia.http;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@EqualsAndHashCode
public class Header {

    private final String name;
    private final String value;

    private Header() {
        this.name = null;
        this.value = null;
    }
}
