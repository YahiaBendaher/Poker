package fr.pns.poker.model;

public enum Value {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    VALET("V",11),
    DAME("D",12),
    ROI("R",13),
    AS("A",14);

    private String symbol;
    private  int cardNumber;

    Value(String symbol, int cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }
    public String getSymbol() { return symbol; }
    public int getCardNumber() { return cardNumber; }

    public static Value getValueFromSymbol(String s) {
        String upperCaseSymbol = s.toUpperCase();
        switch (upperCaseSymbol) {
            case "2": return TWO;
            case "3": return THREE;
            case "4": return FOUR;
            case "5": return FIVE;
            case "6": return SIX;
            case "7": return SEVEN;
            case "8": return EIGHT;
            case "9": return NINE;
            case "10": return TEN;
            case "V": return VALET;
            case "D": return DAME;
            case "R": return ROI;
            case "A": return AS;
            default:
                throw new IllegalArgumentException(s + " est une valeur invalide (attendu: 2..10, V, D, R, A)");
        }
    }

    public static String getNameFromNumber(int number) {
        switch (number) {
            case 14: return "As";
            case 13: return "Roi";
            case 12: return "Dame";
            case 11: return "Valet";
            default: return String.valueOf(number); // pour 2..10
        }
    }



}
