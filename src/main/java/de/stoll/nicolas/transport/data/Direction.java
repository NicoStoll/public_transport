package de.stoll.nicolas.transport.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {

    FORWARD("1"), BACKWARD("2"), UNKNOWN("0");

    private final String value;
}
