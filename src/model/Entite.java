package model;

public abstract class Entite {

    private String id;

    public Entite(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
