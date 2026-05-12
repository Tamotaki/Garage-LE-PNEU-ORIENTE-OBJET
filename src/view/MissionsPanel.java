package view;

import controller.GarageController;
import model.Mission;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel Swing dédié à l'affichage et à la gestion des missions.
 */
public class MissionsPanel extends JPanel {

    private JTable tableMissions;
    private DefaultTableModel modeleTableau;
    private final GarageController controller;

    public MissionsPanel(GarageController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        initialiserTableau();
        initialiserBoutons();
    }

    private void initialiserTableau() {
        String[] colonnes = { "ID", "Titre", "Départ", "Arrivée", "Distance (km)", "Statut", "Chauffeur", "Véhicule" };
        modeleTableau = new DefaultTableModel(colonnes, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableMissions = new JTable(modeleTableau);
        add(new JScrollPane(tableMissions), BorderLayout.CENTER);
    }

    private void initialiserBoutons() {
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRafraichir = new JButton("Rafraîchir");
        btnRafraichir.addActionListener(e -> rafraichir());
        
        JButton btnAffecter = new JButton("Affecter mission");
        btnAffecter.addActionListener(e -> affecter());
        
        JButton btnTerminer = new JButton("Terminer mission");
        btnTerminer.addActionListener(e -> terminer());

        panelBoutons.add(btnRafraichir);
        panelBoutons.add(btnAffecter);
        panelBoutons.add(btnTerminer);
        add(panelBoutons, BorderLayout.SOUTH);
    }

    public void afficherMissions(List<Mission> missions) {
        modeleTableau.setRowCount(0);
        for (Mission m : missions) {
            modeleTableau.addRow(new Object[]{
                m.getId(), m.getTitre(), m.getDepart(), m.getArrivee(), m.getDistanceKm(),
                m.getStatut(),
                m.getChauffeur() != null ? m.getChauffeur().getNom() : "N/A",
                m.getVehicule() != null ? m.getVehicule().getImmatriculation() : "N/A"
            });
        }
    }

    private void rafraichir() {
        if (controller != null) afficherMissions(controller.getMissions());
    }

    private void affecter() {
        int row = tableMissions.getSelectedRow();
        if (row == -1) return;
        String idM = (String) modeleTableau.getValueAt(row, 0);
        String idC = JOptionPane.showInputDialog(this, "ID Chauffeur :");
        String idV = JOptionPane.showInputDialog(this, "ID Véhicule :");
        if (idC != null && idV != null && controller != null) {
            try {
                controller.affecterMission(idM, idC, idV);
                rafraichir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void terminer() {
        int row = tableMissions.getSelectedRow();
        if (row == -1) return;
        String idM = (String) modeleTableau.getValueAt(row, 0);
        if (controller != null) {
            try {
                controller.terminerMission(idM);
                rafraichir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}