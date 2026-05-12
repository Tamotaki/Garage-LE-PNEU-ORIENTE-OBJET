package model;

import model.interfaces.Assignable;
import model.interfaces.Maintenable;

public abstract class Vehicule extends Entite implements Assignable, Maintenable {

    //Champs
    private String immatriculation;
    private String marque;
    private String modele;
    private double kilometrage;
    private EtatVehicule etat;

    //Constructeur
    public Vehicule(String id, String immatriculation, String marque,
                    String modele, double kilometrage) {
        super(id);
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.kilometrage = kilometrage;
        this.etat = EtatVehicule.DISPONIBLE;
    }

    //Méthodes abstraites (chaque sous-classe les implémente)
    public abstract String getPermisRequis();
    public abstract String getType();

    //Méthodes pour interface Assignable
    @Override
    public boolean estDisponible() {
        return etat == EtatVehicule.DISPONIBLE;
    }

    @Override
    public void affecter() {
        if (!estDisponible()) {
            throw new IllegalStateException(
                "Véhicule " + getId() + " non disponible (état actuel : " + etat + ")"
            );
        }
        this.etat = EtatVehicule.EN_MISSION;
    }

    @Override
    public void liberer() {
        this.etat = EtatVehicule.DISPONIBLE;
    }

    //Méthodes pour interface Maintenable
    @Override
    public void mettreEnMaintenance() {
        if (etat == EtatVehicule.EN_MISSION) {
            throw new IllegalStateException(
                "Impossible : le véhicule " + getId() + " est en mission."
            );
        }
        this.etat = EtatVehicule.EN_MAINTENANCE;
    }

    // Méthode pour sortir de maintenance
    @Override
    public void sortirDeMaintenance() {
        if (etat != EtatVehicule.EN_MAINTENANCE) {
            throw new IllegalStateException(
                "Le véhicule " + getId() + " n'est pas en maintenance."
            );
        }
        this.etat = EtatVehicule.DISPONIBLE;
    }

    // Méthode pour vérifier si le véhicule est en maintenance
    @Override
    public boolean estEnMaintenance() {
        return etat == EtatVehicule.EN_MAINTENANCE;
    }

    //Méthodes getters et setters
    public String getImmatriculation() {
        return immatriculation; 
    }
    public String getMarque() {
        return marque; 
    }
    public String getModele() {
        return modele; 
    }
    public double getKilometrage() {
        return kilometrage; 
    }
    public EtatVehicule getEtat() {
        return etat;
    }

    // Setters (si besoin d'être modifiés après création)
    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    // Setter pour l'état (utile pour les tests ou cas particuliers)
    public void setEtat(EtatVehicule etat) {
        this.etat = etat;
    }

    //Méthode pour l'affichage
    @Override
    public String toString() {
        return String.format("[%s] %s %s %s | Immat: %s | Km: %.0f | Etat: %s | Permis: %s",
                getType(), getId(), marque, modele,
                immatriculation, kilometrage, etat, getPermisRequis());
    }
}