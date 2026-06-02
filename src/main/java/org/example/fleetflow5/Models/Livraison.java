package org.example.fleetflow5.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow5.Enums.StatutLivraison;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livraison")
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateLivraison")
    private LocalDateTime dateLivraison;
    @Column(name = "start_adresse")
    private String startAdresse;
    @Column(name = "finish_adresse")
    private String finishAdresse;

    @Enumerated(EnumType.STRING)
    private StatutLivraison statut;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "Vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "client_id" , nullable = false)
    private Client client;
}
