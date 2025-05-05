package com.flightapp.domain;
import java.util.logging.Logger;

public class Airport {
    private static final Logger logger = Logger.getLogger(Airport.class.getName());

    public static void main(String[] args) {
        Flight economyFlight = new EconomyFlight("1");
        Flight businessFlight = new BusinessFlight("2");
        Passenger james = new Passenger("James", true);
        Passenger mike = new Passenger("Mike", false);

        businessFlight.addPassenger(james);
        businessFlight.removePassenger(james);
        businessFlight.addPassenger(mike);

        economyFlight.addPassenger(mike);
        logger.info("Business flight passengers list:");

        for (Passenger passenger : businessFlight.getPassengersList()) {
            logger.info(passenger.getName());
        }

        logger.info("Economy flight passengers list:");
        for (Passenger passenger : economyFlight.getPassengersList()) {
            logger.info(passenger.getName());
        }
    }
}
