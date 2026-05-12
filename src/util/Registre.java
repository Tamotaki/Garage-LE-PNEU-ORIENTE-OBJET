package util;

import model.Entite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registre générique pour stocker des entités par ID.
 */
public class Registre<T extends Entite> {

    // Map de stockage utilisant l'ID comme clé
    private final Map<String, T> stockage = new HashMap<>();

    // Méthode pour ajouter une entité au registre
    public void ajouter(T entite) {
        if (entite != null && entite.getId() != null) {
            stockage.put(entite.getId(), entite);
        }
    }

    // Méthode pour supprimer une entité par son ID
    public void supprimer(String id) {
        stockage.remove(id);
    }

    // Récupérer une entité spécifique par son ID
    public T getParId(String id) {
        return stockage.get(id);
    }

    // Récupérer la liste complète des entités stockées
    public List<T> getToutes() {
        return new ArrayList<>(stockage.values());
    }

    // Retourne le nombre d'entités présentes
    public int size() {
        return stockage.size();
    }
}
