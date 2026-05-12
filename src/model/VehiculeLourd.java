package model;

public class VehiculeLourd extends Vehicule {
//Pas de champs, tout est dans Vehicule

    //Contructeur qui appelle le constructeur de Vehicule
    public VehiculeLourd(String id, String immatriculation,
                         String marque, String modele, double kilometrage) {
        super(id, immatriculation, marque, modele, kilometrage);
    }

    //Implémentation des méthodes abstraites de Vehicule
    @Override
    public String getPermisRequis() {
        return "C"; //Permis poids lourd
    }

    // Type spécifique pour l'affichage
    @Override
    public String getType() {
        return "Lourd";
    }
}