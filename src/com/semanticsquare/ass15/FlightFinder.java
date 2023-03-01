package com.semanticsquare.ass15;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.spi.TimeZoneNameProvider;

public class FlightFinder {

    private Map<String, List<Flight>> allFlights = new HashMap<>();

    public FlightFinder(Map<String, List<Flight>> allFlights) {
        this.allFlights = allFlights;
    }

    public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year,
                                                  int preferredDepartureStartHour, int preferredDepartureEndHour,
                                                  String departureCity, String arrivalCity, String finalArrivalCity,
                                                  String departureCityTimeZone, String arrivalCityTimeZone) {

        List<NavigableSet<Flight>> result = new ArrayList<>();

        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart

        // Your code
        // starting time
        LocalTime DepartureStartTime = LocalTime.of(preferredDepartureStartHour, 00, 00);
        LocalDate DepartureFlightStartDate = LocalDate.of(year, month, dayOfMonth);
        LocalDateTime DepartureFlightStartTime = LocalDateTime.of(DepartureFlightStartDate, DepartureStartTime);
        ZonedDateTime DepartureZonedFlightStartTime = ZonedDateTime.of(DepartureFlightStartTime, ZoneId.of(departureCityTimeZone));
        // end time
        LocalTime DepartureEndTime = LocalTime.of(preferredDepartureEndHour, 00, 00);
        LocalDate DepartureFlightEndDate = LocalDate.of(year, month, dayOfMonth);
        LocalDateTime DepartureFlightEndTime = LocalDateTime.of(DepartureFlightEndDate, DepartureEndTime);
        ZonedDateTime DepartureZonedFlightEndTime = ZonedDateTime.of(DepartureFlightEndTime, ZoneId.of(departureCityTimeZone));
        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);



        NavigableSet<Flight> departingflights = new TreeSet<>();

        // Your code
        // Tip: Methods like isAfter can be used to find flights in the specified user time interval

        // System.out.println("departingflights\n");
        //for(Flight x:allDepartingFlights){System.out.println(x);}
        for(Flight x:allDepartingFlights)
        {
            //System.out.println(x.getDepartureTime());
            //System.out.println(DepartureEndTime);
            if((x.getDepartureTime().toLocalTime()).isAfter(DepartureStartTime) && (x.getDepartureTime().toLocalTime()).isBefore(DepartureEndTime)  && (x.getArrivalCity()).equals(arrivalCity))
            {
                //allDepartingFlights.add(x);
                //System.out.println(x);
                departingflights.add(x);
            }
        }


        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);


        NavigableSet<Flight> connectingflights = new TreeSet<>();

        // Your code
        // System.out.println("connectingflights\n");
        //for(Flight x:allConnectingFlights){System.out.println(x);}
        if(departingflights.size()!=0 && allConnectingFlights.size()!=0)
        {
            for(Flight y:allConnectingFlights )
            {

                for(Flight x:departingflights)
                {
                    LocalTime ConnectingStartTime = LocalTime.of(x.getDepartureTime().getHour()+2, 00, 00);
                    if((y.getDepartureTime().toLocalTime()).isAfter(ConnectingStartTime) && (y.getArrivalCity()).equals(finalArrivalCity))
                    {
                        //System.out.println(y);
                        connectingflights.add(y);
                        break; // please break here else they will repeat
                    }
                }

            }
        }

        //System.out.println("no of departingflights : " + departingflights.size());
        //System.out.println("no of connectingflights : " + connectingflights.size());
        result.add(departingflights);
        result.add(connectingflights);

        return result;
    }

    public static void main(String[] args) {
        Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
                LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
                LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
                LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
        Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
                LocalDateTime.of(2017, 12, 20, 8, 0), LocalDateTime.of(2017, 12, 20, 8, 0));

        Map<String, List<Flight>> allFlights = new HashMap<>();

        allFlights.put("New York", Arrays.asList(f1));
        allFlights.put("San Francisco", Arrays.asList(f2, f3, f4));

        List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
    }

}