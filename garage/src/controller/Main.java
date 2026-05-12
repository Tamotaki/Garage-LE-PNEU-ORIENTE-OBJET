package controller;

import model.Chauffeur;
import model.Mission;
import util.CsvChauffeurLoader;
import util.CsvMissionLoader;
import view.ChauffeursPanel;
import view.MissionsPanel;

import java.util.List;

public class Main {

    public static void main(String[] args) {
 // Chargement des données CSV (MR06)
        CsvChauffeurLoader chauffeurLoader = new CsvChauffeurLoader();
        List<Chauffeur> chauffeurs = chauffeurLoader.charger("resources/chauffeurs_test.csv");

        CsvMissionLoader missionLoader = new CsvMissionLoader();
        List<Mission> missions = missionLoader.charger("resources/missions_test.csv");

        // Statistiques (MR09)
        StatistiquesChauffeurs statsChauffeurs = new StatistiquesChauffeurs(chauffeurs);
        statsChauffeurs.afficherResume();

        StatistiquesMissions statsMissions = new StatistiquesMissions(missions);
        statsMissions.afficherResume();

        // Test d'instanciation des panels Swing
        // PROVISOIRE A CHANGER : passer le vrai GarageController à la place de null quand MR07 est mergée
        ChauffeursPanel chauffeursPanel = new ChauffeursPanel(null);
        chauffeursPanel.afficherChauffeurs(chauffeurs);

        MissionsPanel missionsPanel = new MissionsPanel(null);
        missionsPanel.afficherMissions(missions);

        System.out.println("[Main] Panels instanciés avec succès.");

        // PROVISOIRE A CHANGER : intégrer MainFrame (Gabriela, MR10) pour afficher les panels
        // PROVISOIRE A CHANGER : intégrer GarageController (Antonin, MR07)
        // PROVISOIRE A CHANGER : lancement complet Swing dans MR12 (Antonin)
    }
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
