package fr.pns.poker.exception;

/**
 * Levée quand une carte est mal formée (valeur ou couleur manquante, etc.)
 */
public class InvalidCardFormatException extends PokerException {
    public InvalidCardFormatException(String message) {
        super(message);
    }
}
