package ru.shishkin.statistic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

class EventsStatisticImplTest {
    private Instant start;
    private SetableClock clock;
    private EventsStatistic eventsStatistic;

    @BeforeEach
    void setUp() {
        start = Instant.MIN.plus(1, ChronoUnit.DAYS);
        clock = new SetableClock(start);
        eventsStatistic = new EventsStatisticImpl(clock);
    }

    private void setClock(int minutes) {
        clock.setNow(start.plus(minutes, ChronoUnit.MINUTES));
    }

    private void checkRPM(int expectedCount, double realRPM) {
        Assertions.assertTrue(Math.abs(realRPM - expectedCount / 60.0) < 1e-8);
    }

    @Test
    void simpleTest() {
        String name = "name";
        for (int i = 1; i <= 5; i++) {
            setClock(i);
            eventsStatistic.incEvent(name);
            checkRPM(i, eventsStatistic.getEventStatisticByName(name));
        }
    }

    @Test
    void forgetOldEventsTest() {
        String name = "name";
        setClock(0);
        eventsStatistic.incEvent(name);

        setClock(61);
        eventsStatistic.incEvent(name);

        checkRPM(1, eventsStatistic.getEventStatisticByName(name));
    }

    @Test
    void notFoundEventTest() {
        checkRPM(0, eventsStatistic.getEventStatisticByName("randomName"));
    }

    @Test
    void getAllEventStatisticTest() {
        String name = "name";
        for (int i = 1; i <= 3; i++) {
            setClock(i);
            eventsStatistic.incEvent(name);
        }
        String name2 = "name2";
        eventsStatistic.incEvent(name2);

        Map<String, Double> result = eventsStatistic.getAllEventStatistic();
        Assertions.assertEquals(result.size(), 2);
        checkRPM(3, result.get(name));
        checkRPM(1, result.get(name2));
    }
}