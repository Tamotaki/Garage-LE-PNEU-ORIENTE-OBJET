package model;

import model.interfaces.Facturable;

public class MissionCourte extends Mission implements Facturable {
    private static final double TARIF_PAR_KM = 1.20;

    public MissionCourte(String id, String titre, String depart, String arrivee, double distanceKm) {
        super(id, titre, depart, arrivee, distanceKm);
    }

    @Override
    public double calculerCout() {
        return getDistanceKm() * TARIF_PAR_KM;
    }

    @Override
    public String toString() {
        return "[COURTE] " + super.toString() + String.format(" | Coût: %.2f€", calculerCout());
    }
}
