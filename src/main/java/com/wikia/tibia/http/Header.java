package com.wikia.tibia.http;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
/**
 * TODO: make a record class from this
 */
public class Header {

    String name;
    String value;
}
