package fr.pns.rules;

public class HandEvaluation {
    private HandRank rank;
    private int mainValue;  // valeur principale de la combinaison (ex: paire de 10)
    private int kicker;     // carte la plus haute restante (utile en cas d’égalité)

    public HandEvaluation(HandRank rank, int mainValue, int kicker) {
        this.rank = rank;
        this.mainValue = mainValue;
        this.kicker = kicker;
    }

    public HandRank getRank() {
        return rank;
    }

    public int getMainValue() {
        return mainValue;
    }

    public int getKicker() {
        return kicker;
    }

    @Override
    public String toString() {
        return rank + " (valeur: " + mainValue + ", kicker: " + kicker + ")";
    }


}
