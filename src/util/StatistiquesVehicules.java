package util;

import model.EtatVehicule;
import model.Vehicule;

import java.util.*;
import java.util.stream.Collectors;

public class StatistiquesVehicules {
    //Retourne la liste des véhicules disponibles
    public static List<Vehicule> getDisponibles(List<Vehicule> vehicules) {
        return vehicules.stream()
                .filter(v -> v.getEtat() == EtatVehicule.DISPONIBLE)
                .collect(Collectors.toList());
    }

    //Retourne la liste des véhicule en maintenance
    public static List<Vehicule> getEnMaintenance(List<Vehicule> vehicules) {
        return vehicules.stream()
                .filter(Vehicule::estEnMaintenance)
                .collect(Collectors.toList());
    }
    //Calcule le kilométrage moyen de tous les véhicules
    public static double getKilometrageMoyen(List<Vehicule> vehicules) {
        return vehicules.stream()
                .mapToDouble(Vehicule::getKilometrage)
                .average()
                .orElse(0.0);
    }

    //Retourne le véhicule ayant le plus grand kilométrage
    public static Optional<Vehicule> getPlusKilometre(List<Vehicule> vehicules) {
        return vehicules.stream()
                .max(Comparator.comparingDouble(Vehicule::getKilometrage));
    }

    //Classe les véhicules par kilométrage décroissant
    public static List<Vehicule> classerParKilometrage(List<Vehicule> vehicules) {
        return vehicules.stream()
                .sorted(Comparator.comparingDouble(Vehicule::getKilometrage).reversed())
                .collect(Collectors.toList());
    }

    //Compte le nombre de véhicules par type
    public static TreeMap<String, Long> compterParType(List<Vehicule> vehicules) {
        return vehicules.stream()
                .collect(Collectors.groupingBy(
                        Vehicule::getType,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }
}
