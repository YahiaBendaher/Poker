package fr.pns.poker.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card(Value.AS, Color.PIQUE);
    }

    // Test 1: Construction + getters
    @Test
    void testConstructor() {
        Card newCard = new Card(Value.ROI, Color.COEUR);
        assertNotNull(newCard);
        assertEquals(Value.ROI, newCard.getValue());
        assertEquals(Color.COEUR, newCard.getColor());
    }

    // Test 2: Mapping des valeurs numériques clés (AS, TWO, VALET)
    @Test
    void testGetValueAsInt() {
        assertEquals(14, card.getValueAsInt()); // As
        Card cardTwo = new Card(Value.TWO, Color.CARREAU);
        assertEquals(2, cardTwo.getValueAsInt()); // Deux
        Card cardValet = new Card(Value.VALET, Color.COEUR);
        assertEquals(11, cardValet.getValueAsInt()); // Valet
    }

    // Test 3: toString sur quelques formats représentatifs (As Pique, Roi Coeur, 10 Carreau)
    @Test
    void testToString() {
        assertEquals("APi", card.toString()); // As Pique
        Card card2 = new Card(Value.ROI, Color.COEUR);
        assertEquals("RCo", card2.toString());
        Card card3 = new Card(Value.TEN, Color.CARREAU);
        assertEquals("10Ca", card3.toString());
    }

    // Test 4: Setters combinés (valeur + couleur) + cohérence toString & valueAsInt
    @Test
    void testSettersModifyCardCorrectly() {
        Card modifiableCard = new Card(Value.TWO, Color.TREFLE);
        modifiableCard.setValue(Value.AS);
        modifiableCard.setColor(Color.PIQUE);
        assertEquals(Value.AS, modifiableCard.getValue());
        assertEquals(Color.PIQUE, modifiableCard.getColor());
        assertEquals("APi", modifiableCard.toString());
        assertEquals(14, modifiableCard.getValueAsInt());
    }

    // Test 5: Indépendance de plusieurs instances
    @Test
    void testMultipleCardsAreIndependent() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.ROI, Color.COEUR);
        card1.setValue(Value.DAME);
        assertEquals(Value.DAME, card1.getValue());
        assertEquals(Value.ROI, card2.getValue());
    }
}
