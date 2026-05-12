package model;

public class VehiculeLeger extends Vehicule {
//Pas de champs, tout est dans Vehicule

    //Contructeur qui appelle le constructeur de Vehicule
    public VehiculeLeger(String id, String immatriculation,
                         String marque, String modele, double kilometrage) {
        super(id, immatriculation, marque, modele, kilometrage);
    }

    //Implémentation des méthodes abstraites de Vehicule
    @Override
    public String getPermisRequis() {
        return "B";
    }

    // Type spécifique pour l'affichage
    @Override
    public String getType() {
        return "Léger";
    }
}