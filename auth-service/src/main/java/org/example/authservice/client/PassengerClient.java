package org.example.authservice.client;


import org.example.cab_userservice.passenger.Passenger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface PassengerClient {

    @PostExchange("/passenger/save")
    Passenger savePassenger(@RequestBody Passenger passenger);

    Passenger getPassengerByEmail(String email);
}
