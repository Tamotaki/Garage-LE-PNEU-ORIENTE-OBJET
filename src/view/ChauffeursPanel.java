package view;

import controller.GarageController;
import model.Chauffeur;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel Swing dédié à l'affichage des chauffeurs.
 */
public class ChauffeursPanel extends JPanel {

    private JTable tableChauffeurs;
    private DefaultTableModel modeleTableau;
    private final GarageController controller;

    public ChauffeursPanel(GarageController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        initialiserTableau();
        initialiserBoutons();
    }

    private void initialiserTableau() {
        String[] colonnes = { "ID", "Nom", "Prénom", "Numéro Permis", "Type Permis", "Disponibilité" };
        modeleTableau = new DefaultTableModel(colonnes, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableChauffeurs = new JTable(modeleTableau);
        add(new JScrollPane(tableChauffeurs), BorderLayout.CENTER);
    }

    private void initialiserBoutons() {
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRafraichir = new JButton("Rafraîchir");
        btnRafraichir.addActionListener(e -> rafraichir());
        panelBoutons.add(btnRafraichir);
        add(panelBoutons, BorderLayout.SOUTH);
    }

    public void afficherChauffeurs(List<Chauffeur> chauffeurs) {
        modeleTableau.setRowCount(0);
        for (Chauffeur c : chauffeurs) {
            modeleTableau.addRow(new Object[]{
                c.getId(), c.getNom(), c.getPrenom(), c.getNumeroDPermis(),
                c.getTypePermis(), c.isDisponible() ? "Disponible" : "Indisponible"
            });
        }
    }

    private void rafraichir() {
        if (controller != null) afficherChauffeurs(controller.getChauffeurs());
    }
}