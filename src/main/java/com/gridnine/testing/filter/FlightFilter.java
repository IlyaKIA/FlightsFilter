package com.gridnine.testing.filter;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilter implements FlightFilterInterface {
    List<Flight> flights;

    public FlightFilter(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> excludeBeforeDate(LocalDateTime dateTime) {
        List<Flight> filteredFlights = new ArrayList<>();
        for(Flight flight : flights){
            boolean isDepartureDateBeforeDate = false;
            for(Segment segment : flight.getSegments()){
                if (segment.getDepartureDate().isBefore(dateTime)){
                    isDepartureDateBeforeDate = true;
                }
            }
            if (!isDepartureDateBeforeDate) filteredFlights.add(flight);
        }
        flights = filteredFlights;
        return flights;
    }

    @Override
    public List<Flight> arrivalBeforeDeparture() {
        List<Flight> filteredFlights = new ArrayList<>();
        for(Flight flight : flights){
            boolean isArrivalBeforeDeparture = false;
            for(Segment segment : flight.getSegments()){
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())){
                    isArrivalBeforeDeparture = true;
                }
            }
            if (!isArrivalBeforeDeparture) filteredFlights.add(flight);
        }
        flights = filteredFlights;
        return flights;
    }

    @Override
    public List<Flight> downtimeInMinutesIsLess(long seconds) {
        List<Flight> filteredFlights = new ArrayList<>();
        for(int i = 0; i < flights.size(); i++){
            Flight flight = flights.get(i);
            boolean isDowntimeLessThisMinutes = false;
            if (flights.get(i).getSegments().size() >= 2){
                long downtimeSeconds = 0;
                for (int j = 0; j < flights.get(i).getSegments().size() - 1; j++) {
                    Segment segment = flight.getSegments().get(j);
                    Segment nextSegment = flight.getSegments().get(j + 1);
                    Duration durationTime = Duration.between(segment.getArrivalDate(), nextSegment.getDepartureDate());
                    downtimeSeconds += durationTime.getSeconds();
                }
                if (downtimeSeconds > seconds){
                    isDowntimeLessThisMinutes = true;
                }
            }
            if (!isDowntimeLessThisMinutes) filteredFlights.add(flight);
        }
        flights = filteredFlights;
        return flights;
    }
}
