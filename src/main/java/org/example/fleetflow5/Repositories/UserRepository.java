package org.example.fleetflow5.Repositories;

import org.example.fleetflow5.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
