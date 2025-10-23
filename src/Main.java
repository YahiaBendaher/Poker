import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Carte> liste1 = new ArrayList<>();
        System.out.println("=== Main 1 ===");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            Carte c = new Carte(sc.nextInt());
            liste1.add(c);
        }

        List<Carte> liste2 = new ArrayList<>();
        System.out.println("=== Main 2 ===");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Entrez la carte " + i + " : ");
            Carte c = new Carte(sc.nextInt());
            liste2.add(c);
        }

        System.out.println("\nMain1 : ");
        for (Carte c : liste1) {
            System.out.print(c.getValeur() + " ");
        }
        System.out.println("\n");
        System.out.println("Main2 : ");
        for (Carte c : liste2) {
            System.out.print(c.getValeur() + " ");
        }
        System.out.println("\n");

        int max1 = 0;
        int max2 = 0;

        for (Carte c : liste1) {
            if (c.getValeur() > max1) {
                max1 = c.getValeur();
            }
        }

        for (Carte c : liste2) {
            if (c.getValeur() > max2) {
                max2 = c.getValeur();
            }
        }

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