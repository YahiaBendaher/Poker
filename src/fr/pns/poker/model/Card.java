package fr.pns.poker.model;

public class Card {
    private Value value;
    private Color color;


    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public Value getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }

    public int getValueAsInt() {
        return value.getCardNumber();
    }

    public void setValue(Value v) {
        this.value = v;
    }
    public void setColor(Color c){
        this.color = c;
    }

    @Override
    public String toString() {
        return value.getSymbol() + color.getCode();
    }
}

