package fr.pns.poker.exception;

/**
 * Levée quand une main contient trop de cartes ou une carte nulle.
 */
public class InvalidHandException extends PokerException {
    public InvalidHandException(String message) {
        super("Main invalide : " + message);
    }
}
