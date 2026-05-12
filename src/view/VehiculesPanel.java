package view;

import model.EtatVehicule;
import model.Vehicule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Panel Swing dédié à l'affichage et à la gestion des véhicules.
public class VehiculesPanel extends JPanel {

    // Composants graphiques panel
    private JTable tableVehicules;
    private DefaultTableModel modeleTableau;
    private JButton btnRafraichir;
    private JButton btnMettreEnMaintenance;
    private JButton btnSortirMaintenance;


    // PROVISOIRE A CHANGER : remplacer Object par GarageController quand MR07 sera fait
    private Object controller;

    // PROVISOIRE A CHANGER : remplacer Object par GarageController quand MR07 sera fait
    public VehiculesPanel(Object controller) {
        this.controller = controller; // PROVISOIRE A CHANGER

        setLayout(new BorderLayout());
        initialiserTableau();
        initialiserBoutons();
    }

    // Méthode qui crée le tableau avec les colonnes demandés
    private void initialiserTableau() {

        String[] colonnes = {
                "ID",
                "Immatriculation",
                "Marque",
                "Modèle",
                "Type",
                "Kilométrage",
                "État",
                "Permis requis"
        };

        // DefaultTableModel non éditables directement
        modeleTableau = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Empêcher l'édition directe dans le tableau
                return false;
            }
        };

        tableVehicules = new JTable(modeleTableau);

        // JScrollPane pour avoir une barre de défilement
        JScrollPane scrollPane = new JScrollPane(tableVehicules);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Méthode qui crée le panneau de boutons et l'ajoute en bas
    private void initialiserBoutons() {
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Bouton rafraîchir : recharge les véhicules depuis le contrôleur
        btnRafraichir = new JButton("Rafraîchir");
        btnRafraichir.addActionListener(e -> rafraichir());

        // Bouton mettre en maintenance : change l'état du véhicule sélectionné
        btnMettreEnMaintenance = new JButton("Mettre en maintenance");
        btnMettreEnMaintenance.addActionListener(e -> mettreEnMaintenanceSelectionne());

        // Bouton sortir de maintenance : remet le véhicule disponible
        btnSortirMaintenance = new JButton("Sortir de maintenance");
        btnSortirMaintenance.addActionListener(e -> sortirDeMaintenanceSelectionne());

        panelBoutons.add(btnRafraichir);
        panelBoutons.add(btnMettreEnMaintenance);
        panelBoutons.add(btnSortirMaintenance);

        add(panelBoutons, BorderLayout.SOUTH);
    }

    // Méthode qui vide le tableau et le remplit avec une nouvelle liste de véhicules
    public void afficherVehicules(List<Vehicule> vehicules) {
        // Vide toutes les lignes existantes avant de recharger
        modeleTableau.setRowCount(0);

        for (Vehicule v : vehicules) {
            // Chaque ligne = un tableau d'objets dans l'ordre des colonnes
            Object[] ligne = {
                    v.getId(),
                    v.getImmatriculation(),
                    v.getMarque(),
                    v.getModele(),
                    v.getType(),
                    String.format("%.0f km", v.getKilometrage()),
                    v.getEtat(),
                    v.getPermisRequis()
            };
            modeleTableau.addRow(ligne);
        }
    }

    // Méthode appelée quand on clique sur "Rafraîchir"
    private void rafraichir() {
        try {
            // PROVISOIRE A CHANGER : remplacer par controller.getVehicules() quand MR07 sera fait
            System.out.println("[VehiculesPanel] Rafraîchir appelé (PROVISOIRE A CHANGER : connecter au GarageController MR07)");
        } catch (Exception e) {
            afficherErreur("Erreur lors du rafraîchissement : " + e.getMessage());
        }
    }

    // Méthode qui récupère le véhicule sélectionné et le met en maintenance
    private void mettreEnMaintenanceSelectionne() {
        int ligneSelectionnee = tableVehicules.getSelectedRow();
        if (ligneSelectionnee == -1) {
            afficherErreur("Veuillez sélectionner un véhicule dans le tableau.");
            return;
        }

        // On lit l'ID du véhicule
        String idVehicule = (String) modeleTableau.getValueAt(ligneSelectionnee, 0);

        try {
            // PROVISOIRE A CHANGER
            System.out.println("[VehiculesPanel] Mise en maintenance demandée pour " + idVehicule
                    + " (PROVISOIRE A CHANGER : connecter au GarageController MR07)");
        } catch (Exception e) {
            afficherErreur("Erreur : " + e.getMessage());
        }
    }

    // Méthode qui récupère le véhicule sélectionné et le sort de maintenance
    private void sortirDeMaintenanceSelectionne() {
        int ligneSelectionnee = tableVehicules.getSelectedRow();
        if (ligneSelectionnee == -1) {
            afficherErreur("Veuillez sélectionner un véhicule dans le tableau.");
            return;
        }

        String idVehicule = (String) modeleTableau.getValueAt(ligneSelectionnee, 0);

        try {
            // PROVISOIRE A CHANGER
            System.out.println("[VehiculesPanel] Sortie de maintenance demandée pour " + idVehicule
                    + " (PROVISOIRE A CHANGER : connecter au GarageController MR07)");
        } catch (Exception e) {
            afficherErreur("Erreur : " + e.getMessage());
        }
    }

    // Méthode qui affiche une boîte de dialogue d'erreur simple
    public void afficherErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
