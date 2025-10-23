import fr.pns.poker.Card;
import fr.pns.poker.Hand;
import fr.pns.rules.PokerRules;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Main 1 ===");
        Hand main1 = new Hand();
        for (int i = 0; i < 5; i++) {
            int valeur = sc.nextInt();
            main1.addCard(new Card(valeur));
        }

        System.out.println("\n=== Main 2 ===");
        Hand main2 = new Hand();
        for (int i = 0; i < 5; i++) {
            int valeur = sc.nextInt();
            main2.addCard(new Card(valeur));
        }

        System.out.println("\n=== RÉSULTAT ===");
        System.out.print("Main 1 : ");
        main1.printHand();
        System.out.print("Main 2 : ");
        main2.printHand();

        // Vérification des paires
        int paire1 = PokerRules.getPair(main1.getCards());
        int paire2 = PokerRules.getPair(main2.getCards());

        System.out.println("Paire Main 1 : " + (paire1 > 0 ? "Oui (" + paire1 + ")" : "Non"));
        System.out.println("Paire Main 2 : " + (paire2 > 0 ? "Oui (" + paire2 + ")" : "Non"));

        // Comparaison
        String resultat = PokerRules.compareWith(main1, main2) ;
        System.out.println("\n " + resultat);
        sc.close();
    }
}