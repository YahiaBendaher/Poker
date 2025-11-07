package fr.pns.poker.utils;

import fr.pns.poker.model.Hand;
import fr.pns.poker.model.CardSeperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOUtils {

    public static Hand readHand(Scanner scanner, int handIndex) {
        boolean isValid = false;
        Hand hand = new Hand();

        while (!isValid) {
            System.out.print("Main " + handIndex + ": ");
            hand = new Hand();

            String line = scanner.nextLine().trim();

            try {
                if (line.isEmpty()) {
                    throw new Exception("Une Main doit contenir exactement 5 cartes!");
                }

                List<String> cardValues = new ArrayList<>(Arrays.asList(line.split("\\s+")));

                if (cardValues.size() != 5) {
                    throw new Exception("Une Main doit contenir exactement 5 cartes!");
                }

                for (String value : cardValues) {
                    hand.addCard(CardSeperator.seperateCard(value));
                }

                isValid = true; // Tout est bon → main valide

            } catch (Exception e) {
                String msg = e.getMessage();
                if (msg.equals("Une Main doit contenir exactement 5 cartes!")) {
                    System.out.println(msg);
                }
                else if (msg.startsWith("Erreur : '")) {
                    System.out.println(msg); // <-- affichera "Erreur : '5Pi' en double."
                }
                else {
                    System.out.println("Types de Cartes invalides! (Format attendu: Valeur+Couleur, ex: 2Tr, 10Ca, VCo, RPi, ATr)");
                }
            }
        }
        return hand;
    }
}
