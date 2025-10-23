import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Carte> cartes;

    public Hand() {
        this.cartes = new ArrayList<>();
    }

    public void ajouterCarte(Carte c) {
        if (cartes.size() < 5) {
            cartes.add(c);
        } else {
            System.out.println("Une main ne peut pas avoir plus de 5 cartes !");
        }
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void afficher() {
        for (Carte c : cartes) {
            System.out.print(c.getValeur() + " ");
        }
        System.out.println();
    }

    public int getMax(){
        int max = 0;
        for(Carte c : cartes){
            if (c.getValeur()>max) max = c.getValeur();
        }
        return max;
    }
}
