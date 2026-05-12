package util;

import model.Mission;
import model.MissionCourte;
import model.MissionLongue;
import model.StatutMission;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Chargeur CSV pour les missions.
 */
public class CsvMissionLoader {

    private static final String SEPARATEUR = ",";

    public List<Mission> charger(String cheminFichier) {
        List<Mission> missions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int numeroLigne = 0;
            while ((ligne = reader.readLine()) != null) {
                numeroLigne++;
                if (numeroLigne == 1 || ligne.isBlank()) continue;
                try {
                    missions.add(parserLigne(ligne));
                } catch (Exception e) {
                    System.err.println("[CsvMissionLoader] Ligne " + numeroLigne + " ignorée : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("[CsvMissionLoader] Erreur lecture fichier : " + e.getMessage());
        }
        return missions;
    }

    private Mission parserLigne(String ligne) {
        String[] champs = ligne.split(SEPARATEUR);
        if (champs.length < 5) {
            throw new IllegalArgumentException("Nombre de colonnes insuffisant (attendu au moins 5, trouvé " + champs.length + ")");
        }

        String id       = champs[0].trim();
        String titre    = champs[1].trim();
        String depart   = champs[2].trim();
        String arrivee  = champs[3].trim();
        double distance = Double.parseDouble(champs[4].trim());
        String type     = champs.length > 5 ? champs[5].trim().toUpperCase() : "COURTE";

        Mission mission = switch (type) {
            case "COURTE" -> new MissionCourte(id, titre, depart, arrivee, distance);
            case "LONGUE" -> new MissionLongue(id, titre, depart, arrivee, distance, arrivee); // Utilise arrivee comme itineraire par défaut
            default -> throw new IllegalArgumentException("Type de mission inconnu : " + type);
        };

        if (champs.length > 6) {
            try {
                mission.setStatut(StatutMission.valueOf(champs[6].trim().toUpperCase()));
            } catch (Exception ignored) {}
        }

        return mission;
    }
}