package fr.pns.poker.exception;

/**
 * Exception de base pour toutes les erreurs liées au poker.
 */
public class PokerException extends RuntimeException {
    public PokerException(String message) {
        super(message);
    }
}
