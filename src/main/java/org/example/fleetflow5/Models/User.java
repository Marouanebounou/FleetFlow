package org.example.fleetflow5.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fleetflow5.Enums.Role;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
