package org.example.cab_userservice.passenger;

import org.springframework.stereotype.Service;


@Service
public class PassengerService implements PassengerClient {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger savePassenger(Passenger passenger) {
        // Your logic to save the passenger in the database
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger getPassengerByEmail(String email) {
        return passengerRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Passenger not found for email: " + email));
    }


}
