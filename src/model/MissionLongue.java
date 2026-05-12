package model;

import model.interfaces.Trackable;

public class MissionLongue extends Mission implements Trackable {
    private static final double TARIF_PAR_KM = 0.95; // €/km (dégressif sur longue distance)
    private static final double FORFAIT_BASE = 150.0; // forfait fixe longue distance

    private String itineraire;

    public MissionLongue(String id, String titre, String depart, String arrivee,
                         double distanceKm, String itineraire) {
        super(id, titre, depart, arrivee, distanceKm);
        this.itineraire = itineraire;
    }

    @Override
    public double calculerCout() {
        return FORFAIT_BASE + (getDistanceKm() * TARIF_PAR_KM);
    }

    @Override
    public String getItineraire() {
        return itineraire;
    }

    @Override
    public String toString() {
        return "[LONGUE] " + super.toString()
                + String.format(" | Itinéraire: %s | Coût: %.2f€", itineraire, calculerCout());
    }
}
