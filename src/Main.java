import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Valeur 1 : ");
        int valeur1 = scanner.nextInt();
        System.out.print("Valeur 2 : ");
        int valeur2 = scanner.nextInt();
        if (valeur1 > valeur2) {
            System.out.println("La valeur la plus haute est : " + valeur1);
        } else if (valeur1 < valeur2) {
            System.out.println("La valeur la plus haute est : " + valeur2);
        } else {
            System.out.println("Egalité");
        }
        scanner.close();
    }
}