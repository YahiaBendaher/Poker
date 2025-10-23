package fr.pns.poker;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card c) {
        if (cards.size() < 5) {
            cards.add(c);
        } else {
            System.out.println("Une main ne peut pas avoir plus de 5 cartes !");
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void printHand() {
        for (Card c : cards) {
            System.out.print(c.getValue() + " ");
        }
        System.out.println();
    }

    public int getMax() {
        int max = 0;
        for (Card c : cards) {
            if (c.getValue() > max) max = c.getValue();
        }
        return max;
    }

    public boolean hasPair() {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getValue() == cards.get(j).getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
