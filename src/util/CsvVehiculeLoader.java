package util;

import model.EtatVehicule;
import model.Vehicule;
import model.VehiculeLeger;
import model.VehiculeLourd;
import model.VehiculeSpecial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvVehiculeLoader {
    //Méthode pour retourner une liste de véhicules, on fournit le chemin du fichier CSV en paramètre
    public static List<Vehicule> charger(String cheminFichier) {
        //Création d'une liste vide qui va se remplir au fur et à mesure
        List<Vehicule> vehicules = new ArrayList<>();

        //Ouvre et lit le fichier ligne par ligne, ensuite il ferme le fichier automatiquement même en cas d'erreur
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean premiereLigne = true;

            //readLine() lit une ligne et retourne null quand il n'y a plus rien, arrêt de la boucle
            while ((ligne = reader.readLine()) != null) {
                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                ligne = ligne.trim();
                if (ligne.isEmpty()) continue;

                //Essayer de transformer chaque ligne en objet Vehicule, si une ligne est mal formée, on l'ignore et on affiche un avertissement
                try {
                    Vehicule v = parseLigne(ligne);
                    vehicules.add(v);
                } catch (Exception e) {
                    System.err.println("Ligne ignorée : " + ligne);
                    System.err.println("Raison : " + e.getMessage());
                }
            }

        //Si fichier n'existe pas, on affiche une erreur
        } catch (IOException e) {
            System.err.println("Fichier introuvable : " + cheminFichier);
            e.printStackTrace();
        }

        return vehicules;
    }

    private static Vehicule parseLigne(String ligne) {
        String[] champs = ligne.split(",", -1);

        //Si la ligne n'a pas de 7 colonnes, on lance une exception
        if (champs.length < 7) {
            throw new IllegalArgumentException(
                    "Il faut 7 colonnes, il y en a " + champs.length
            );
        }

        //Récuperation de chaque colonne par son index de 0 à 6
        String id = champs[0].trim();
        String type = champs[1].trim();
        String immatriculation = champs[2].trim();
        String marque = champs[3].trim();
        String modele = champs[4].trim();
        double kilometrage = Double.parseDouble(champs[5].trim());
        EtatVehicule etat = EtatVehicule.valueOf(champs[6].trim().toUpperCase());

        String specialisation = champs.length > 7 ? champs[7].trim() : "";

        Vehicule vehicule;

        //Selon le type dans le CSV, on crée le bon sous type de véhicule
        switch (type) {
            case "Léger":
                vehicule = new VehiculeLeger(id, immatriculation, marque, modele, kilometrage);
                break;
            case "Lourd":
                vehicule = new VehiculeLourd(id, immatriculation, marque, modele, kilometrage);
                break;
            case "Special":
                vehicule = new VehiculeSpecial(id, immatriculation, marque, modele, kilometrage, specialisation);
                break;
            default:
                throw new IllegalArgumentException("Type inconnu : " + type);
        }

        //Application de l'état depuis CSV: véhicule dispo, en maintenance etc.)
        vehicule.setEtat(etat);
        return vehicule;
    }
}
