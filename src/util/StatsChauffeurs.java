package util;

import model.Chauffeur;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Statistiques pour les chauffeurs.
 */
public class StatsChauffeurs {

    private final List<Chauffeur> chauffeurs;

    public StatsChauffeurs(List<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }

    public List<Chauffeur> getChauffeursDisponibles() {
        return chauffeurs.stream()
                .filter(Chauffeur::isDisponible)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getNombreParTypePermis() {
        return chauffeurs.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getTypePermis().name(),
                        Collectors.counting()
                ));
    }

    public void afficherResume() {
        System.out.println("Statistiques Chauffeurs");
        List<Chauffeur> disponibles = getChauffeursDisponibles();
        System.out.println("Chauffeurs disponibles (" + disponibles.size() + ") :");
        disponibles.forEach(c -> System.out.println("  " + c));
        System.out.println("Répartition par permis :");
        getNombreParTypePermis().forEach((p, n) -> System.out.println("  Permis " + p + " : " + n));
    }
}