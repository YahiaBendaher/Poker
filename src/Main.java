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

        int max1 = hand1.getMax();
        int max2 = hand2.getMax();

        if (max1 > max2) {
            System.out.println("Main 1 gagne (Carte la plus haute : " + max1 + ")");
        }
        else if (max2 > max1) {
            System.out.println("Main 2 gagne (Carte la plus haute : " + max2 + ")");
        }
        /*else {
            // Les cartes hautes sont égales, on compare les kickers
            if (min1 > min2) {
                System.out.println("Main 1 gagne (Kicker : " + min1 + ")");
            } else if (min2 > min1) {
                System.out.println("Main 2 gagne (Kicker : " + min2 + ")");
            } else {
                // Les deux cartes sont identiques
                System.out.println("Égalité");
            }
        }*/
        sc.close();
    }
}