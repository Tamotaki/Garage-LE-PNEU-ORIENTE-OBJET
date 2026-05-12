package util;

import model.Chauffeur;
import model.TypePermis;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Chargeur CSV pour les chauffeurs.
 */
public class CsvChauffeurLoader {

    private static final String SEPARATEUR = ",";

    public List<Chauffeur> charger(String cheminFichier) {
        List<Chauffeur> chauffeurs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int numeroLigne = 0;
            while ((ligne = reader.readLine()) != null) {
                numeroLigne++;
                if (numeroLigne == 1 || ligne.isBlank()) continue;
                try {
                    chauffeurs.add(parserLigne(ligne));
                } catch (Exception e) {
                    System.err.println("[CsvChauffeurLoader] Ligne " + numeroLigne + " ignorée : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("[CsvChauffeurLoader] Erreur lecture fichier : " + e.getMessage());
        }
        return chauffeurs;
    }

    private Chauffeur parserLigne(String ligne) {
        String[] champs = ligne.split(SEPARATEUR);
        if (champs.length < 5) {
            throw new IllegalArgumentException("Nombre de colonnes insuffisant (attendu au moins 5, trouvé " + champs.length + ")");
        }

        String id           = champs[0].trim();
        String nom          = champs[1].trim();
        String prenom       = champs[2].trim();
        String numeroPermis = champs[3].trim();
        TypePermis type     = TypePermis.valueOf(champs[4].trim());

        return new Chauffeur(id, nom, prenom, numeroPermis, type);
    }
}