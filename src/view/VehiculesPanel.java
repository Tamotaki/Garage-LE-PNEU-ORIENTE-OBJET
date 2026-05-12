package view;

import controller.GarageController;
import model.Vehicule;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel Swing dédié à l'affichage et à la gestion des véhicules.
 */
public class VehiculesPanel extends JPanel {

    private JTable tableVehicules;
    private DefaultTableModel modeleTableau;
    private final GarageController controller;

    public VehiculesPanel(GarageController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        initialiserTableau();
        initialiserBoutons();
    }

    private void initialiserTableau() {
        String[] colonnes = { "ID", "Immatriculation", "Marque", "Modèle", "Type", "Kilométrage", "État", "Permis requis" };
        modeleTableau = new DefaultTableModel(colonnes, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tableVehicules = new JTable(modeleTableau);
        add(new JScrollPane(tableVehicules), BorderLayout.CENTER);
    }

    private void initialiserBoutons() {
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRafraichir = new JButton("Rafraîchir");
        btnRafraichir.addActionListener(e -> rafraichir());
        
        JButton btnMaint = new JButton("Mettre en maintenance");
        btnMaint.addActionListener(e -> maintenance(true));
        
        JButton btnSortie = new JButton("Sortir de maintenance");
        btnSortie.addActionListener(e -> maintenance(false));

        panelBoutons.add(btnRafraichir);
        panelBoutons.add(btnMaint);
        panelBoutons.add(btnSortie);
        add(panelBoutons, BorderLayout.SOUTH);
    }

    public void afficherVehicules(List<Vehicule> vehicules) {
        modeleTableau.setRowCount(0);
        for (Vehicule v : vehicules) {
            modeleTableau.addRow(new Object[]{
                v.getId(), v.getImmatriculation(), v.getMarque(), v.getModele(),
                v.getType(), String.format("%.0f km", v.getKilometrage()), v.getEtat(), v.getPermisRequis()
            });
        }
    }

    private void rafraichir() {
        if (controller != null) afficherVehicules(controller.getVehicules());
    }

    private void maintenance(boolean enMaintenance) {
        int row = tableVehicules.getSelectedRow();
        if (row == -1) return;
        String id = (String) modeleTableau.getValueAt(row, 0);
        if (controller != null) {
            if (enMaintenance) controller.mettreEnMaintenance(id);
            else controller.sortirDeMaintenance(id);
            rafraichir();
        }
    }
}
