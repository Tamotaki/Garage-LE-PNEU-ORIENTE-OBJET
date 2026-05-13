package util;

import model.Entite;
import java.util.ArrayList;
import java.util.List;

public class Registre<T extends Entite> {
    private List<T> elements = new ArrayList<>();

    public void ajouter(T element) {
        elements.add(element);
    }

    public List<T> getToutes() {
        return elements;
    }

    public T getParId(String id) {
        for (T element : elements) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }
}
