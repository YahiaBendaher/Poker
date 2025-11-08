package fr.pns.poker.utils;

import fr.pns.poker.exception.InvalidCardFormatException;
import fr.pns.poker.exception.PokerException;
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
                    throw new InvalidCardFormatException("Une main doit contenir exactement 5 cartes !");
                }

                List<String> cardValues = new ArrayList<>(Arrays.asList(line.split("\\s+")));

                if (cardValues.size() != 5) {
                    throw new InvalidCardFormatException("Une main doit contenir exactement 5 cartes !");
                }

                for (String value : cardValues) {
                    hand.addCard(CardSeperator.seperateCard(value));
                }

                isValid = true; // Tout est bon → main valide

            } catch (PokerException e) {
                System.out.println(e.getMessage());
            }
        }
        return hand;
    }
}
