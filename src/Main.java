import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int valeur1 = scanner.nextInt();
        int valeur2 = scanner.nextInt();
        if (valeur1 > valeur2) {
            System.out.println("La valeur la plus haute est : " + valeur1);
        } else {
            System.out.println("La valeur la plus haute est : " + valeur2);
        }

        scanner.close();
    }
}