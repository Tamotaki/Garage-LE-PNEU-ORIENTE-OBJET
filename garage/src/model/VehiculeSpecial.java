package model;

public class VehiculeSpecial extends Vehicule {

    //Champs propre à cette sous-classe
    private String specialisation;

    //Constructeur qui appelle le constructeur de Vehicule et initialise le champs supplémentaire
    public VehiculeSpecial(String id, String immatriculation, String marque,
                           String modele, double kilometrage, String specialisation) {
        super(id, immatriculation, marque, modele, kilometrage);
        this.specialisation = specialisation;
    }

    //Implémentation des méthodes abstraites de Vehicule
    @Override
    public String getPermisRequis() {
        return "CE"; //Permis poids lourd avec remorque
    }

    @Override
    public String getType() {
        return "Spécial";
    }

    //Méthode getter pour le champ supplémentaire
    public String getSpecialisation() {
        return specialisation;
    }

    // Méthode toString enrichie avec la spécialisation
    @Override
    public String toString() {
        return super.toString() + " | Spécialité: " + specialisation;
    }
}
