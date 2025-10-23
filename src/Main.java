import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Main 1 ===");
        Hand hand1 = new Hand();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            Carte c = new Carte(sc.nextInt());
            hand1.ajouterCarte(c);
        }

        System.out.println("\n=== Main 2 ===");
        Hand hand2 = new Hand();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            Carte c = new Carte(sc.nextInt());
            hand2.ajouterCarte(c);
        }

        System.out.print("\nMain 1 : ");
        hand1.afficher();

        System.out.print("Main 2 : ");
        hand2.afficher();

        int resultat = 0;

        // On compare jusqu'à 5 cartes (max puis kicker(s))
        for (int i = 0; i < 5; i++) {

            int max1 = hand1.getMax();
            int max2 = hand2.getMax();

            if (max1 > max2) {
                System.out.println("Main 1 gagne (carte la plus haute : " + max1 + ")");
                resultat = 1;
                break;
            } else if (max2 > max1) {
                System.out.println("Main 2 gagne (carte la plus haute : " + max2 + ")");
                resultat = 1;
                break;
            } else {
                // égalité pour ce round, on retire une fois le max des cartes
                for (Carte c : hand1.getCartes()) {
                    if (c.getValeur() == max1) {
                        c.setValeur(0);
                        break;
                    }
                }
                for (Carte c : hand2.getCartes()) {
                    if (c.getValeur() == max2) {
                        c.setValeur(0);
                        break;
                    }
                }
            }
        }

        if (resultat == 0) {
            System.out.println("Égalité parfaite !");
        }
        sc.close();
    }
}