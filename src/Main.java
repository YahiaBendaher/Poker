import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import fr.pns.poker.rules.PairRule;
import fr.pns.poker.evaluator.HandComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean verif(int valeur){
        if (valeur < 2 || valeur > 14) {
            return false;
        }
        return true;
    }

    public static Hand lireMain(Scanner sc , int indiceOfMain){
        boolean ok = false ;
        Hand main = new Hand();
        while (!ok) {
            System.out.println("=== Main "+indiceOfMain+" ===");
            main =  new Hand();
            try {
                String line = sc.nextLine().trim();

                if (line.isEmpty()){
                    throw new Exception("Vous devez entrer 5 cartes !");
                }
                List<String> myList = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                if (myList.size() != 5){
                    throw new Exception("Vous devez entrer 5 cartes !");
                }
                for (String s : myList){
                    int valeur = Integer.parseInt(s);
                    if (!verif(valeur)){
                        throw new Exception("Les valeurs doivent être comprises entre 2 et 14 !");
                    }
                    main.addCard(new Card(valeur));
                }

                ok = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return main;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Hand main1 = lireMain(sc , 1);
        Hand main2 = lireMain(sc , 2);

        System.out.println("\n=== RÉSULTAT ===");
        System.out.print("Main 1 : ");
        main1.printHand();
        System.out.print("Main 2 : ");
        main2.printHand();

        // Vérification des paires
        int paire1 = PairRule.getPair(main1.getCards());
        int paire2 = PairRule.getPair(main2.getCards());

        System.out.println("Paire Main 1 : " + (paire1 > 0 ? "Oui (" + paire1 + ")" : "Non"));
        System.out.println("Paire Main 2 : " + (paire2 > 0 ? "Oui (" + paire2 + ")" : "Non"));

        // Comparaison
        String resultat = HandComparator.compareHands(main1, main2);
        System.out.println("\n" + resultat);
        sc.close();

    }
}