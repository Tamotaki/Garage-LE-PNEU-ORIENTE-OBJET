package model.interfaces;

public interface Maintenable {

    //Met l'entité en maintenance
    void mettreEnMaintenance();

    //Repasse à disponible après maintenance
    void sortirDeMaintenance();

    //Retourne true si l'entité est en maintenance
    boolean estEnMaintenance();
}