package controller;

import model.*;
import util.Registre;
import java.util.List;

/**
 * Contrôleur central de l'application.
 * Relie les modèles, gère la logique métier et les affectations.
 */
public class GarageController {

    // Registres pour stocker les données de l'application
    private final Registre<Vehicule> registreVehicules = new Registre<>();
    private final Registre<Chauffeur> registreChauffeurs = new Registre<>();
    private final Registre<Mission> registreMissions = new Registre<>();

    // Gestion des données

    // Ajouter un véhicule au garage
    public void ajouterVehicule(Vehicule v) { registreVehicules.ajouter(v); }
    
    // Ajouter un chauffeur au registre
    public void ajouterChauffeur(Chauffeur c) { registreChauffeurs.ajouter(c); }
    
    // Ajouter une mission à la liste
    public void ajouterMission(Mission m) { registreMissions.ajouter(m); }

    // Récupérer tous les véhicules
    public List<Vehicule> getVehicules() { return registreVehicules.getToutes(); }
    
    // Récupérer tous les chauffeurs
    public List<Chauffeur> getChauffeurs() { return registreChauffeurs.getToutes(); }
    
    // Récupérer toutes les missions
    public List<Mission> getMissions() { return registreMissions.getToutes(); }

    // Logique métier

    /**
     * Affecte un chauffeur et un véhicule à une mission.
     * Vérifie la disponibilité et les permis.
     */
    public void affecterMission(String idMission, String idChauffeur, String idVehicule) {
        Mission mission = registreMissions.getParId(idMission);
        Chauffeur chauffeur = registreChauffeurs.getParId(idChauffeur);
        Vehicule vehicule = registreVehicules.getParId(idVehicule);

        // Vérification de l'existence des entités
        if (mission == null || chauffeur == null || vehicule == null) {
            throw new IllegalArgumentException("ID invalide : mission, chauffeur ou véhicule introuvable.");
        }

        // Vérifier la disponibilité du chauffeur et du véhicule
        if (!chauffeur.isDisponible()) {
            throw new IllegalStateException("Le chauffeur " + chauffeur.getNom() + " n'est pas disponible.");
        }
        if (!vehicule.estDisponible()) {
            throw new IllegalStateException("Le véhicule " + vehicule.getImmatriculation() + " n'est pas disponible.");
        }

        // Vérifier si le chauffeur possède le permis requis pour le véhicule
        TypePermis requis = TypePermis.valueOf(vehicule.getPermisRequis());
        if (!chauffeur.aLePourPermis(requis)) {
            throw new IllegalStateException("Le chauffeur " + chauffeur.getNom() + 
                " n'a pas le permis requis (" + requis + ") pour ce véhicule.");
        }

        // Effectuer l'affectation et mettre à jour les statuts
        mission.setChauffeur(chauffeur);
        mission.setVehicule(vehicule);
        mission.setStatut(StatutMission.EN_COURS);
        
        chauffeur.setDisponible(false);
        vehicule.affecter();
        
        System.out.println("[GarageController] Mission " + idMission + " affectée avec succès.");
    }

    /**
     * Termine une mission et libère les ressources (chauffeur et véhicule).
     */
    public void terminerMission(String idMission) {
        Mission mission = registreMissions.getParId(idMission);
        
        // Vérifier que la mission est bien en cours
        if (mission == null || mission.getStatut() != StatutMission.EN_COURS) {
            throw new IllegalStateException("Mission non trouvée ou non en cours.");
        }

        // Libération du personnel et du matériel
        mission.getChauffeur().setDisponible(true);
        mission.getVehicule().liberer();
        mission.setStatut(StatutMission.TERMINEE);

        System.out.println("[GarageController] Mission " + idMission + " terminée.");
    }

    // Mettre un véhicule en maintenance
    public void mettreEnMaintenance(String idVehicule) {
        Vehicule v = registreVehicules.getParId(idVehicule);
        if (v != null) {
            v.mettreEnMaintenance();
            System.out.println("[GarageController] Véhicule " + idVehicule + " en maintenance.");
        }
    }

    // Sortir un véhicule de maintenance
    public void sortirDeMaintenance(String idVehicule) {
        Vehicule v = registreVehicules.getParId(idVehicule);
        if (v != null) {
            v.sortirDeMaintenance();
            System.out.println("[GarageController] Véhicule " + idVehicule + " sorti de maintenance.");
        }
    }
}
