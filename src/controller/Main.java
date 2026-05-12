package controller;

import model.Vehicule;
import util.CsvVehiculeLoader;
import util.StatistiquesVehicules;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Chargement des véhicules ===\n");

        List<Vehicule> vehicules = CsvVehiculeLoader.charger("resources/vehicules_test.csv");

        System.out.println(vehicules.size() + " véhicule(s) chargé(s) :\n");

        //Version 1
        //for (Vehicule v : vehicules) {
            //System.out.println(v);

        //Version 2
        vehicules.forEach(System.out::println);

        // Statistiques
        System.out.println("\n=== Statistiques véhicules ===");

        // Disponibles
        System.out.println("\n-- Véhicules disponibles --");
        StatistiquesVehicules.getDisponibles(vehicules).forEach(System.out::println);

        // En maintenance
        System.out.println("\n-- Véhicules en maintenance --");
        StatistiquesVehicules.getEnMaintenance(vehicules).forEach(System.out::println);

        // Kilométrage moyen
        System.out.printf("%n-- Kilométrage moyen : %.1f km --%n",
                StatistiquesVehicules.getKilometrageMoyen(vehicules));

        // Véhicule le plus kilométré
        System.out.println("\n-- Véhicule le plus kilométré --");
        Optional<Vehicule> plusKilometre = StatistiquesVehicules.getPlusKilometre(vehicules);
        plusKilometre.ifPresent(System.out::println);

        // Classement par kilométrage décroissant
        System.out.println("\n-- Classement par kilométrage (décroissant) --");
        StatistiquesVehicules.classerParKilometrage(vehicules)
                .forEach(v -> System.out.printf("  %s : %.0f km%n",
                        v.getImmatriculation(), v.getKilometrage()));

        // Comptage par type
        System.out.println("\n-- Nombre de véhicules par type --");
        for (Map.Entry<String, Long> entry : StatistiquesVehicules.compterParType(vehicules).entrySet()) {
            System.out.printf("  %s : %d véhicule(s)%n", entry.getKey(), entry.getValue());

        }
    }
}
