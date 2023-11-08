package ru.shishkin.statistic;

import java.time.Instant;

public interface Clock {
    Instant now();
}
