package org.example.cab_userservice.passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    // Add custom query methods if needed
    Optional<Passenger> findByUserId(Long userId);

    Optional<Passenger>findByUserEmail(String email); //maybe custom query
}
