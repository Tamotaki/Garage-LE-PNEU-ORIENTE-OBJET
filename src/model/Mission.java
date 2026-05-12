package model;

public abstract class Mission extends Entite {

    private String titre;
    private String depart;
    private String arrivee;
    private double distanceKm;
    private StatutMission statut;
    private Chauffeur chauffeur;
    private Vehicule vehicule;

    public Mission(String id, String titre, String depart, String arrivee, double distanceKm) {
        super(id);
        this.titre = titre;
        this.depart = depart;
        this.arrivee = arrivee;
        this.distanceKm = distanceKm;
        this.statut = StatutMission.EN_ATTENTE;
        this.chauffeur = null;
        this.vehicule = null;
    }

    //Méthode pour que chaque type de mission calcule son coût différemment
    public abstract double calculerCout();

    //Getters
    public String getTitre() {
        return titre; }
    public String getDepart() {
        return depart; }
    public String getArrivee() {
        return arrivee; }
    public double getDistanceKm() {
        return distanceKm; }
    public StatutMission getStatut() {
        return statut; }
    public Chauffeur getChauffeur() {
        return chauffeur; }
    public Vehicule getVehicule() {
        return vehicule; }

    //Setters
    public void setStatut(StatutMission statut) {
        this.statut = statut; }
    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur; }
    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule; }

    @Override
    public String toString() {
        return String.format(
                "Mission[%s] \"%s\" | %s → %s (%.1f km) | Statut: %s | Chauffeur: %s | Véhicule: %s",
                getId(), titre, depart, arrivee, distanceKm,
                statut,
                chauffeur != null ? chauffeur.getNom() : "non affecté",
                vehicule != null ? vehicule.getImmatriculation() : "non affecté"
        );
    }
}
