package org.example.fleetflow5.Repositories;

import org.example.fleetflow5.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
     Client findByEmail(String email);
}
