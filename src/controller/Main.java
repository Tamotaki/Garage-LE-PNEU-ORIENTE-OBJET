package controller;

import model.Vehicule;
import util.CsvVehiculeLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Chargement des véhicules ===\n");

        List<Vehicule> vehicules = CsvVehiculeLoader.charger("resources/vehicules_test.csv");

        System.out.println(vehicules.size() + " véhicule(s) chargé(s) :\n");

        for (Vehicule v : vehicules) {
            System.out.println(v);
        }
    }
}
