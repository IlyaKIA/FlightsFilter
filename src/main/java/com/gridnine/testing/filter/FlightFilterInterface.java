package com.gridnine.testing.filter;


import com.gridnine.testing.models.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilterInterface {
    List<Flight> excludeBeforeDate(LocalDateTime dateTime);
    List<Flight> arrivalBeforeDeparture();
    List<Flight> downtimeInMinutesIsLess(long seconds);

}
