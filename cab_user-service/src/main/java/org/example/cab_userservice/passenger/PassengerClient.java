package org.example.cab_userservice.passenger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface PassengerClient {

    @PostExchange("/passenger/save")
    Passenger savePassenger(@RequestBody Passenger passenger);
    // Add other methods as needed

    Passenger getPassengerByEmail(String email);
}
