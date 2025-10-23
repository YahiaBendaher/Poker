import fr.pns.poker.Card;
import fr.pns.poker.Hand;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Main 1 ===");
        Hand hand1 = new Hand();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            Card c = new Card(sc.nextInt());
            hand1.addCard(c);
        }

        /*System.out.println("\n=== Main 2 ===");
        fr.pns.poker.Hand hand2 = new fr.pns.poker.Hand();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            fr.pns.poker.Card c = new fr.pns.poker.Card(sc.nextInt());
            hand2.addCard(c);
        }*/

        System.out.print("\nMain 1 : ");
        hand1.printHand();

        if(hand1.hasPair()){
            System.out.println("Il y a une paire dans la main.");
        }else{
            System.out.println("Il n'y a pas de paire dans la main.");
        }

        /*System.out.print("Main 2 : ");
        hand2.printHand();


        int resultat = 0;

        // On compare jusqu'à 5 cartes (max puis kicker(s))
        for (int i = 0; i < 5; i++) {

            int max1 = hand1.getMax();
            int max2 = hand2.getMax();

            if (max1 > max2) {
                if(i==0) {
                    System.out.println("Main 1 gagne (carte la plus haute : " + max1 + ")");
                }else{
                    System.out.println("Main 1 gagne (Kicker : " + max1 + ")");
                }
                resultat = 1;
                break;
            } else if (max2 > max1) {
                if(i==0){
                    System.out.println("Main 2 gagne (carte la plus haute : " + max2 + ")");
                }else{
                    System.out.println("Main 2 gagne (Kicker : " + max2 + ")");
                }
                resultat = 1;
                break;
            } else {
                // si max1 == max2 == 0 (deux mains sont vides), on ne peut pas déclarer de vainqueur
                if(max1 == 0 &&  max2 == 0){
                    break;
                }
                // égalité pour ce round, on retire une fois le max des cartes
                for (fr.pns.poker.Card c : hand1.getCards()) {
                    if (c.getValue() == max1) {
                        c.setValue(0);
                        break;
                    }
                }
                for (fr.pns.poker.Card c : hand2.getCards()) {
                    if (c.getValue() == max2) {
                        c.setValue(0);
                        break;
                    }
                }
            }
        }

        if (resultat == 0) {
            System.out.println("Égalité parfaite !");
        }


        //   Détection de Paire de 2 cartes
     /*   List<fr.pns.poker.Card> handPaire2Cartes = new ArrayList<>();

        // On demande 2 cartes pour ce test
        for (int i = 1; i <= 2; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            fr.pns.poker.Card c = new fr.pns.poker.Card(sc.nextInt());
            handPaire2Cartes.add(c);
        }

        System.out.print("\nMain : ");
        for (fr.pns.poker.Card c : handPaire2Cartes) {
            System.out.print(c.getValeur() + " ");
        }
        System.out.println();

        if (handPaire2Cartes.get(0).getValeur() == handPaire2Cartes.get(1).getValeur()) {
            System.out.println("Résultat : Paire de " + handPaire2Cartes.get(0).getValeur());
        } else {
            System.out.println("Résultat : Pas de paire");
        }


*/

        sc.close();
    }
}