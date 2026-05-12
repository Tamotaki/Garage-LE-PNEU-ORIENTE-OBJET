package model.interfaces;

public interface Assignable {

    //Retourne true si l'entité est disponible pour une mission
    boolean estDisponible();

    //Marque l'entité en mission
    void affecter();

    //Rend l'entité disponible après mission
    void liberer();
}