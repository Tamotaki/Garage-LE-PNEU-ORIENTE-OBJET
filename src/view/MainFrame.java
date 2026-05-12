package view;

import model.Vehicule;
import util.CsvVehiculeLoader;

import javax.swing.*;
import java.util.List;

// Fenêtre principale de l'application Swing avec les onglets
public class MainFrame extends JFrame {

    private VehiculesPanel vehiculesPanel;
    private ChauffeursPanel chauffeursPanel;
    private MissionsPanel missionsPanel;

    public MainFrame() {
        // Configuration de la fenêtre principale
        setTitle("Garage - Le Pneu Orienté Objet");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centrer l'écran

        // Création des panels
        // PROVISOIRE A CHANGER : passer le GarageController en paramètre quand MR07 sera fait
        vehiculesPanel   = new VehiculesPanel(null);
        chauffeursPanel  = new ChauffeursPanel(null);
        missionsPanel    = new MissionsPanel(null);

        // Onglets
        JTabbedPane onglets = new JTabbedPane();
        onglets.addTab("Véhicules", vehiculesPanel);
        onglets.addTab("Chauffeurs", chauffeursPanel);
        onglets.addTab("Missions", missionsPanel);

        add(onglets);

        // Charger et afficher les véhicules au démarrage
        chargerVehicules();
    }

    // Charge les véhicules depuis le CSV et les affiche
    // PROVISOIRE A CHANGER : utiliser controller.getVehicules() quand MR07 sera prêt
    private void chargerVehicules() {
        List<Vehicule> vehicules = CsvVehiculeLoader.charger("resources/vehicules_test.csv");
        vehiculesPanel.afficherVehicules(vehicules);
    }
}
