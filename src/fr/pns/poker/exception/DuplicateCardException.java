package fr.pns.poker.exception;

/**
 * Levée quand une même carte est ajoutée deux fois dans une main.
 */
public class DuplicateCardException extends PokerException {
    public DuplicateCardException(String cardName) {
        super("Erreur : '" + cardName + "' en double.");
    }
}
