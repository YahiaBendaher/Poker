package fr.pns.poker.utils;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DisplayUtils {
    public static boolean validateCardValue(int value) {
        return value >= 2 && value <= 14;
    }

    public static Hand readHand(Scanner scanner, int handIndex) {
        boolean isValid = false;
        Hand hand = new Hand();

        while (!isValid) {
            System.out.println("Main " + handIndex + " : ");
            hand = new Hand();
            try {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    throw new Exception("You must enter 5 cards!");
                }
                List<String> cardValues = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                if (cardValues.size() != 5) {
                    throw new Exception("You must enter 5 cards!");
                }
                for (String value : cardValues) {
                    int cardValue = Integer.parseInt(value);
                    if (!validateCardValue(cardValue)) {
                        throw new Exception("Values must be between 2 and 14!");
                    }
                    hand.addCard(new Card(cardValue));
                }

                isValid = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return hand;
    }
}
