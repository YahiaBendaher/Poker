package fr.pns.poker.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card c)  {
        if (c == null){
            throw new IllegalArgumentException("Carte nulle");
        }
        for (Card existing : cards) {
            if (existing.getValue() == c.getValue() && existing.getColor() == c.getColor()){
                throw new IllegalArgumentException("Erreur : '" + c + "' en double.");
            }
        }
        if (cards.size() >= 5) {
            throw new IllegalStateException("Une main ne peut contenir que 5 cartes");
        }
        cards.add(c);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    public List<Integer> getValues() {
        List<Integer> vals = new ArrayList<>();
        for (Card c : cards) vals.add(c.getValueAsInt());
        return vals;
    }

    public void printHand() {
        for (Card c : cards) {
            System.out.print(c.getValue() + " ");
        }
    }



    public static Hand createHand(String... cardsString) {
        if (cardsString == null || cardsString.length != 5) {
            throw new IllegalArgumentException("Une main doit être créée avec exactement 5 cartes.");
        }
        Hand h = new Hand();
        for (int i = 0; i < cardsString.length; i++) {
            Card card = CardSeperator.seperateCard(cardsString[i]);
            h.addCard(card);
        }
        return h;
    }

    }
