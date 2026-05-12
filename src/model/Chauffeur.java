package model;

import javax.print.DocFlavor;

public class Chauffeur extends Entite {

    private String nom;
    private String prenom;
    private String  numeroDePermis;
    private TypePermis typePermis;
    private boolean disponible;

    public Chauffeur(String id, String nom, String prenom, String numeroDePermis, TypePermis typePermis ) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.numeroDePermis = numeroDePermis;
        this.typePermis = typePermis;
        this.disponible = true;
    }

    //Vérification si chauffeur a le permis
    public boolean aLePourPermis(TypePermis permisRequis) {
        return this.typePermis.couvre(permisRequis);
    }

    //Getters
    public String getNom() {
        return nom; }
    public String getPrenom() {
        return prenom; }
    public String getNumeroDPermis() {
        return numeroDePermis; }
    public TypePermis getTypePermis() {
        return typePermis; }
    public boolean isDisponible() {
        return disponible; }

    //Setters
    public void setDisponible(boolean disponible) {
        this.disponible = disponible; }

    @Override
    public String toString() {
        return String.format(
                "Chauffeur[%s] %s %s | Permis %s (%s) | %s",
                getId(),
                prenom, nom,
                typePermis,
                numeroDePermis,
                disponible ? "Disponible" : "Indisponible"
        );
    }
}
