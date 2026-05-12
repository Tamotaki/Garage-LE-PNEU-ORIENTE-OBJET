package view;

import controller.GarageController;
import javax.swing.*;

/**
 * Fenêtre principale de l'application Swing avec les onglets.
 * Centralise l'accès au GarageController.
 */
public class MainFrame extends JFrame {

    private final GarageController controller;
    private VehiculesPanel vehiculesPanel;
    private ChauffeursPanel chauffeursPanel;
    private MissionsPanel missionsPanel;

    public MainFrame(GarageController controller) {
        this.controller = controller;

        setTitle("Garage - Le Pneu Orienté Objet");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        vehiculesPanel   = new VehiculesPanel(controller);
        chauffeursPanel  = new ChauffeursPanel(controller);
        missionsPanel    = new MissionsPanel(controller);

        JTabbedPane onglets = new JTabbedPane();
        onglets.addTab("Véhicules", vehiculesPanel);
        onglets.addTab("Chauffeurs", chauffeursPanel);
        onglets.addTab("Missions", missionsPanel);

        add(onglets);

        rafraichirDonnees();
    }

    public void rafraichirDonnees() {
        if (controller != null) {
            vehiculesPanel.afficherVehicules(controller.getVehicules());
            chauffeursPanel.afficherChauffeurs(controller.getChauffeurs());
            missionsPanel.afficherMissions(controller.getMissions());
        }
    }
}
