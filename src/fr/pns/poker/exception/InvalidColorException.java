package fr.pns.poker.exception;

/**
 * Levée quand la couleur d'une carte (Tr, Ca, Co, Pi) est inconnue.
 */
public class InvalidColorException extends PokerException {
    public InvalidColorException(String code) {
        super("Couleur '" + code + "' invalide (attendu : Tr, Ca, Co, Pi)");
    }
}
