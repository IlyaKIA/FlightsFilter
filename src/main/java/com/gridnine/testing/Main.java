package com.gridnine.testing;


import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.models.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilter(flights);

        System.out.println("Перелеты до фильтрации:");
        System.out.println(flights + "\n");
        System.out.println("Исключены перелеты с датами вылета до текущего времени:");
        System.out.println(flightFilter.excludeBeforeDate(LocalDateTime.now()) + "\n");
        System.out.println("Дополнительно исключены перелеты с датой прилёта раньше даты вылета:");
        System.out.println(flightFilter.arrivalBeforeDeparture() + "\n");
        System.out.println("Наложены все вильтры, включая исключение перелетов с временем на земле более двух часов:");
        System.out.println(flightFilter.downtimeInMinutesIsLess(2*60*60));
    }
}
