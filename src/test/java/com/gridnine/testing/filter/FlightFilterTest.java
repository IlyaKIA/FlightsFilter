package com.gridnine.testing.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FlightFilterTest {
    FlightFilter flightFilter;

    @BeforeEach
    public void init(){
        flightFilter = new FlightFilter(TestFlightBuilder.createFlights());

    }

    @Test
    void excludeBeforeDate() {
        Assertions.assertEquals(flightFilter.excludeBeforeDate(LocalDateTime.of(2021,1,1,0,0)), TestFlightBuilder.createFlightsWithoutPastDeparting());
    }

    @Test
    void arrivalBeforeDeparture() {
        Assertions.assertEquals(flightFilter.arrivalBeforeDeparture(), TestFlightBuilder.createFlightsWithoutArrivesBeforeDeparts());
    }

    @Test
    void downtimeInMinutesIsLess() {
        Assertions.assertEquals(flightFilter.downtimeInMinutesIsLess(2*60*60), TestFlightBuilder.createFlightsWithoutMoreThanTwoHoursGroundTime());
    }
}