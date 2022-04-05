package app.util;

import app.entities.Passenger;
import app.entities.Passport;

/**
 * Class helps to add some passenger to DB for testing
 */
public class PassengerAndPassportCreator {


    /**
     * @return List of 3 passengers for testing
     */
    public static Passenger createOnePassengersAndPassports() {
        Passport passport1 = new Passport();
        passport1.setFirstName("Vadim");
//
        Passenger passenger1 = new Passenger();
        passenger1.setEmail("vp@italteplo.su");
        passenger1.setFirstName("Vadim");
        passenger1.setPassport(passport1);
        passport1.setPassenger(passenger1);

        return passenger1;


    }

}
