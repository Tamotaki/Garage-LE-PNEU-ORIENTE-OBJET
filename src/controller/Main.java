package controller;

import model.Chauffeur;
import model.Mission;
import model.Vehicule;
import util.CsvChauffeurLoader;
import util.CsvMissionLoader;
import util.CsvVehiculeLoader;
import view.MainFrame;

import javax.swing.*;
import java.util.List;

/**
 * Classe principale : Intégration finale.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Garage LE PNEU : Lancement de l'application");

        // Initialisation du contrôleur central
        GarageController controller = new GarageController();

        // Chargement des données via les loaders
        try {
            System.out.println("Chargement des données...");
            
            // Chargement des véhicules
            List<Vehicule> vehicules = CsvVehiculeLoader.charger("resources/vehicules_test.csv");
            vehicules.forEach(controller::ajouterVehicule);
            System.out.println("- " + vehicules.size() + " véhicules chargés.");

            // Chargement des chauffeurs
            CsvChauffeurLoader chauffeurLoader = new CsvChauffeurLoader();
            List<Chauffeur> chauffeurs = chauffeurLoader.charger("resources/chauffeurs_test.csv");
            chauffeurs.forEach(controller::ajouterChauffeur);
            System.out.println("- " + chauffeurs.size() + " chauffeurs chargés.");

            // Chargement des missions
            CsvMissionLoader missionLoader = new CsvMissionLoader();
            List<Mission> missions = missionLoader.charger("resources/mission_test.csv");
            missions.forEach(controller::ajouterMission);
            System.out.println("- " + missions.size() + " missions chargées.");

        } catch (Exception e) {
            // Gestion des erreurs de chargement initial
            System.err.println("Erreur lors du chargement des données : " + e.getMessage());
        }

        // Lancement de l'interface graphique
        SwingUtilities.invokeLater(() -> {
            try {
                // Création et affichage de la fenêtre principale injectée avec le contrôleur
                MainFrame frame = new MainFrame(controller);
                frame.setVisible(true);
                System.out.println("Application prête");
            } catch (Exception e) {
                System.err.println("Erreur au lancement de l'interface : " + e.getMessage());
            }
        });
    }
}
