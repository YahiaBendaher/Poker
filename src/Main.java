import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Carte> liste1 = new ArrayList<Carte>();
        System.out.println("=== Main 1 ===");
        System.out.print("Entrez la première carte : ");
        Carte c1 = new Carte (sc.nextInt());
        System.out.print("Entrez la deuxieme carte : ");
        Carte c2 = new Carte (sc.nextInt());
        liste1.add(c1);
        liste1.add(c2);



        List<Carte> liste2 = new ArrayList<Carte>();
        System.out.println("=== Main 2 ===");
        System.out.print("Entrez la première carte : ");
        Carte c3 = new Carte (sc.nextInt());
        System.out.print("Entrez la deuxieme carte : ");
        Carte c4 = new Carte (sc.nextInt());
        liste2.add(c3);
        liste2.add(c4);

        System.out.println("\nMain1 : " + liste1.get(0).getValeur() + ", " + liste1.get(1).getValeur());
        System.out.println("Main2 : " + liste2.get(0).getValeur() + ", " + liste2.get(1).getValeur());

        int max1 = Math.max(liste1.get(0).getValeur(), liste1.get(1).getValeur());
        int min1 = Math.min(liste1.get(0).getValeur(), liste1.get(1).getValeur());

        int max2 = Math.max(liste2.get(0).getValeur(), liste2.get(1).getValeur());
        int min2 = Math.min(liste2.get(0).getValeur(), liste2.get(1).getValeur());


        if (max1 > max2) {
            System.out.println("Main 1 gagne (Carte la plus haute : " + max1 + ")");
        }
        else if (max2 > max1) {
            System.out.println("Main 2 gagne (Carte la plus haute : " + max2 + ")");
        }
        else {
            // Les cartes hautes sont égales, on compare les kickers
            if (min1 > min2) {
                System.out.println("Main 1 gagne (Kicker : " + min1 + ")");
            } else if (min2 > min1) {
                System.out.println("Main 2 gagne (Kicker : " + min2 + ")");
            } else {
                // Les deux cartes sont identiques
                System.out.println("Égalité");
            }
        }
        sc.close();
    }
}