package fr.pns.rules;

public enum HandRank {
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIR(3),
    BRELAN(4),
    STRAIGHT(5),          // Suite
    FLUSH(6),             // Couleur
    FULL_HOUSE(7),        // Full
    FOUR_OF_A_KIND(8),    // Carré
    STRAIGHT_FLUSH(9),    // Quinte flush
    ROYAL_FLUSH(10);      // Quinte flush royale

    private final int rankValue;

    HandRank(int rankValue) {
        this.rankValue = rankValue;
    }

    public int getRankValue() {
        return rankValue;
    }
}

