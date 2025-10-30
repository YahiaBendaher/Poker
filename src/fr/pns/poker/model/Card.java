package fr.pns.poker.model;

public class Card {
    private int value;


    public Card(int value) {

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public String toString() {
        return String.valueOf(value);
    }

}

