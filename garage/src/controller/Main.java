package controller;

import model.Chauffeur;
import model.Mission;
import util.CsvChauffeurLoader;
import util.CsvMissionLoader;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Test de CsvChauffeurLoader pour charger et afficher les chauffeurs du CSV
        System.out.println("Chargement des chauffeurs");
        CsvChauffeurLoader chauffeurLoader = new CsvChauffeurLoader();
        List<Chauffeur> chauffeurs = chauffeurLoader.charger("resources/chauffeurs_test.csv");
        for (Chauffeur c : chauffeurs) {
            System.out.println(c);
        }

        System.out.println();

        //Test de CsvMissionLoader pour charger et afficher les missions du CSV
        System.out.println("Chargement des missions");
        CsvMissionLoader missionLoader = new CsvMissionLoader();
        List<Mission> missions = missionLoader.charger("resources/missions_test.csv");
        for (Mission m : missions) {
            System.out.println(m);
        }

        //PROVISOIRE A CHANGER : intégrer GarageController (Antonin, MR07)
        //PROVISOIRE A CHANGER : intégrer les statistiques (MR09)
        //PROVISOIRE A CHANGER : lancer l'interface Swing (MR11/MR12)
    }
}
