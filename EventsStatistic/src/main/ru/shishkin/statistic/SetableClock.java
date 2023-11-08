package ru.shishkin.statistic;

import java.time.Instant;

public class SetableClock implements Clock {
    private Instant now;

    public SetableClock(Instant now) {
        setNow(now);
    }

    public void setNow(Instant now) {
        this.now = now;
    }

    @Override
    public Instant now() {
        return now;
    }
}
