package fr.pns.poker;

import fr.pns.poker.model.Hand;
import fr.pns.poker.evaluator.HandComparator;
import fr.pns.poker.utils.DisplayUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Hand main1 = DisplayUtils.readHand(sc, 1);
        Hand main2 = DisplayUtils.readHand(sc, 2);
        String resultat = HandComparator.compareHands(main1, main2);
        System.out.println(resultat);
        sc.close();

    }
}