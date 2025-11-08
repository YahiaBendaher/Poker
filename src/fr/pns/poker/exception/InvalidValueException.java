package fr.pns.poker.exception;

/**
 * Levée quand la valeur d'une carte (2, 10, V, D, R, A) est inconnue.
 */
public class InvalidValueException extends PokerException {
    public InvalidValueException(String symbol) {
        super("Valeur '" + symbol + "' invalide (attendu : 2..10, V, D, R, A)");
    }

    public InvalidValueException() {
        super("Carte invalide : valeur manquante avant la couleur (ex : 10Ca, VCo, RPi, ATr)");
    }
}
