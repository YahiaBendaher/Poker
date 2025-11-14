package fr.pns.poker.model;

import fr.pns.poker.exception.InvalidCardFormatException;
import fr.pns.poker.exception.InvalidColorException;
import fr.pns.poker.exception.InvalidValueException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardSeperatorTest {

    // Tests des cartes valides
    @Test
    void testSeperateCard_ValidCard_AsPique() {
        Card card = CardSeperator.seperateCard("APi");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_RoiCoeur() {
        Card card = CardSeperator.seperateCard("RCo");
        assertNotNull(card);
        assertEquals(Value.ROI, card.getValue());
        assertEquals(Color.COEUR, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_DameCarreau() {
        Card card = CardSeperator.seperateCard("DCa");
        assertNotNull(card);
        assertEquals(Value.DAME, card.getValue());
        assertEquals(Color.CARREAU, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_ValetTrefle() {
        Card card = CardSeperator.seperateCard("VTr");
        assertNotNull(card);
        assertEquals(Value.VALET, card.getValue());
        assertEquals(Color.TREFLE, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Ten() {
        Card card = CardSeperator.seperateCard("10Ca");
        assertNotNull(card);
        assertEquals(Value.TEN, card.getValue());
        assertEquals(Color.CARREAU, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Nine() {
        Card card = CardSeperator.seperateCard("9Pi");
        assertNotNull(card);
        assertEquals(Value.NINE, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Eight() {
        Card card = CardSeperator.seperateCard("8Co");
        assertNotNull(card);
        assertEquals(Value.EIGHT, card.getValue());
        assertEquals(Color.COEUR, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Seven() {
        Card card = CardSeperator.seperateCard("7Tr");
        assertNotNull(card);
        assertEquals(Value.SEVEN, card.getValue());
        assertEquals(Color.TREFLE, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Six() {
        Card card = CardSeperator.seperateCard("6Ca");
        assertNotNull(card);
        assertEquals(Value.SIX, card.getValue());
        assertEquals(Color.CARREAU, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Five() {
        Card card = CardSeperator.seperateCard("5Pi");
        assertNotNull(card);
        assertEquals(Value.FIVE, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Four() {
        Card card = CardSeperator.seperateCard("4Co");
        assertNotNull(card);
        assertEquals(Value.FOUR, card.getValue());
        assertEquals(Color.COEUR, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Three() {
        Card card = CardSeperator.seperateCard("3Tr");
        assertNotNull(card);
        assertEquals(Value.THREE, card.getValue());
        assertEquals(Color.TREFLE, card.getColor());
    }

    @Test
    void testSeperateCard_ValidCard_Two() {
        Card card = CardSeperator.seperateCard("2Ca");
        assertNotNull(card);
        assertEquals(Value.TWO, card.getValue());
        assertEquals(Color.CARREAU, card.getColor());
    }

    // Tests avec toutes les couleurs
    @Test
    void testSeperateCard_AllColors_Trefle() {
        Card card = CardSeperator.seperateCard("ATr");
        assertEquals(Color.TREFLE, card.getColor());
    }

    @Test
    void testSeperateCard_AllColors_Carreau() {
        Card card = CardSeperator.seperateCard("ACa");
        assertEquals(Color.CARREAU, card.getColor());
    }

    @Test
    void testSeperateCard_AllColors_Coeur() {
        Card card = CardSeperator.seperateCard("ACo");
        assertEquals(Color.COEUR, card.getColor());
    }

    @Test
    void testSeperateCard_AllColors_Pique() {
        Card card = CardSeperator.seperateCard("APi");
        assertEquals(Color.PIQUE, card.getColor());
    }

    // Tests avec espaces
    @Test
    void testSeperateCard_WithLeadingSpaces() {
        Card card = CardSeperator.seperateCard("  APi");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_WithTrailingSpaces() {
        Card card = CardSeperator.seperateCard("APi  ");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_WithSpacesAround() {
        Card card = CardSeperator.seperateCard("  APi  ");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_TenWithSpaces() {
        Card card = CardSeperator.seperateCard(" 10Ca ");
        assertNotNull(card);
        assertEquals(Value.TEN, card.getValue());
        assertEquals(Color.CARREAU, card.getColor());
    }

    // Tests des cas d'erreur - Carte null ou vide
    @Test
    void testSeperateCard_NullString_ThrowsException() {
        InvalidCardFormatException exception = assertThrows(
                InvalidCardFormatException.class,
                () -> CardSeperator.seperateCard(null)
        );
        assertTrue(exception.getMessage().contains("Carte vide"));
    }

    @Test
    void testSeperateCard_EmptyString_ThrowsException() {
        InvalidCardFormatException exception = assertThrows(
                InvalidCardFormatException.class,
                () -> CardSeperator.seperateCard("")
        );
        assertTrue(exception.getMessage().contains("Carte vide"));
    }

    @Test
    void testSeperateCard_BlankString_ThrowsException() {
        InvalidCardFormatException exception = assertThrows(
                InvalidCardFormatException.class,
                () -> CardSeperator.seperateCard("   ")
        );
        assertTrue(exception.getMessage().contains("Carte vide"));
    }

    // Tests des cas d'erreur - Valeur invalide
    @Test
    void testSeperateCard_InvalidValue_ThrowsException() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("XPi"));
    }

    @Test
    void testSeperateCard_InvalidValue_Number1() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("1Ca"));
    }

    @Test
    void testSeperateCard_InvalidValue_Number11() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("11Co"));
    }

    @Test
    void testSeperateCard_InvalidValue_Zero() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("0Tr"));
    }

    // Tests des cas d'erreur - Couleur invalide
    @Test
    void testSeperateCard_InvalidColor_ThrowsException() {
        assertThrows(InvalidColorException.class, () -> CardSeperator.seperateCard("AXX"));
    }

    @Test
    void testSeperateCard_InvalidColor_SingleChar() {
        assertThrows(InvalidColorException.class, () -> CardSeperator.seperateCard("AT"));
    }

    @Test
    void testSeperateCard_InvalidColor_WrongCode() {
        assertThrows(InvalidColorException.class, () -> CardSeperator.seperateCard("AHe"));
    }

    // Tests des cas d'erreur - Carte incomplète
    @Test
    void testSeperateCard_OnlyValue_ThrowsException() {
        assertThrows(InvalidCardFormatException.class, () -> CardSeperator.seperateCard("A"));
    }

    @Test
    void testSeperateCard_OnlyColor_ThrowsException() {
        InvalidValueException exception = assertThrows(
                InvalidValueException.class,
                () -> CardSeperator.seperateCard("Pi")
        );
        assertTrue(exception.getMessage().contains("valeur manquante"));
    }

    @Test
    void testSeperateCard_OnlyColor_Trefle() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("Tr"));
    }

    @Test
    void testSeperateCard_OnlyColor_Carreau() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("Ca"));
    }

    @Test
    void testSeperateCard_OnlyColor_Coeur() {
        assertThrows(InvalidValueException.class, () -> CardSeperator.seperateCard("Co"));
    }

    // Tests de casse (minuscule/majuscule)
    @Test
    void testSeperateCard_LowerCase() {
        Card card = CardSeperator.seperateCard("api");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_MixedCase() {
        Card card = CardSeperator.seperateCard("aPi");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_UpperCase() {
        Card card = CardSeperator.seperateCard("API");
        assertNotNull(card);
        assertEquals(Value.AS, card.getValue());
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSeperateCard_TenLowerCase() {
        Card card = CardSeperator.seperateCard("10ca");
        assertNotNull(card);
        assertEquals(Value.TEN, card.getValue());
        assertEquals(Color.CARREAU, card.getColor());
    }

    // Test de réutilisation - vérifier que chaque appel retourne une nouvelle instance
    @Test
    void testSeperateCard_MultipleCallsReturnDifferentInstances() {
        Card card1 = CardSeperator.seperateCard("APi");
        Card card2 = CardSeperator.seperateCard("APi");

        assertNotSame(card1, card2);
        assertEquals(card1.getValue(), card2.getValue());
        assertEquals(card1.getColor(), card2.getColor());
    }


}

