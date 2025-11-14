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

    @Test
    void testConstructor() {
        Card newCard = new Card(Value.ROI, Color.COEUR);
        assertNotNull(newCard);
        assertEquals(Value.ROI, newCard.getValue());
        assertEquals(Color.COEUR, newCard.getColor());
    }

    @Test
    void testGetValue() {
        assertEquals(Value.AS, card.getValue());
    }

    @Test
    void testGetColor() {
        assertEquals(Color.PIQUE, card.getColor());
    }

    @Test
    void testSetValue() {
        card.setValue(Value.DAME);
        assertEquals(Value.DAME, card.getValue());
    }

    @Test
    void testSetColor() {
        card.setColor(Color.TREFLE);
        assertEquals(Color.TREFLE, card.getColor());
    }

    @Test
    void testGetValueAsInt() {
        assertEquals(14, card.getValueAsInt());

        Card cardTwo = new Card(Value.TWO, Color.CARREAU);
        assertEquals(2, cardTwo.getValueAsInt());

        Card cardValet = new Card(Value.VALET, Color.COEUR);
        assertEquals(11, cardValet.getValueAsInt());
    }

    @Test
    void testToString() {
        assertEquals("APi", card.toString());

        Card card2 = new Card(Value.ROI, Color.COEUR);
        assertEquals("RCo", card2.toString());

        Card card3 = new Card(Value.TEN, Color.CARREAU);
        assertEquals("10Ca", card3.toString());
    }

    @Test
    void testToStringWithAllValues() {
        Card cardTwo = new Card(Value.TWO, Color.TREFLE);
        assertEquals("2Tr", cardTwo.toString());

        Card cardThree = new Card(Value.THREE, Color.TREFLE);
        assertEquals("3Tr", cardThree.toString());

        Card cardFour = new Card(Value.FOUR, Color.TREFLE);
        assertEquals("4Tr", cardFour.toString());

        Card cardFive = new Card(Value.FIVE, Color.TREFLE);
        assertEquals("5Tr", cardFive.toString());

        Card cardSix = new Card(Value.SIX, Color.TREFLE);
        assertEquals("6Tr", cardSix.toString());

        Card cardSeven = new Card(Value.SEVEN, Color.TREFLE);
        assertEquals("7Tr", cardSeven.toString());

        Card cardEight = new Card(Value.EIGHT, Color.TREFLE);
        assertEquals("8Tr", cardEight.toString());

        Card cardNine = new Card(Value.NINE, Color.TREFLE);
        assertEquals("9Tr", cardNine.toString());

        Card cardValet = new Card(Value.VALET, Color.TREFLE);
        assertEquals("VTr", cardValet.toString());

        Card cardDame = new Card(Value.DAME, Color.TREFLE);
        assertEquals("DTr", cardDame.toString());
    }

    @Test
    void testToStringWithAllColors() {
        Card cardTrefle = new Card(Value.AS, Color.TREFLE);
        assertEquals("ATr", cardTrefle.toString());

        Card cardCarreau = new Card(Value.AS, Color.CARREAU);
        assertEquals("ACa", cardCarreau.toString());

        Card cardCoeur = new Card(Value.AS, Color.COEUR);
        assertEquals("ACo", cardCoeur.toString());

        Card cardPique = new Card(Value.AS, Color.PIQUE);
        assertEquals("APi", cardPique.toString());
    }

    @Test
    void testCardCreationWithDifferentCombinations() {
        Card card1 = new Card(Value.TWO, Color.TREFLE);
        assertNotNull(card1);
        assertEquals(Value.TWO, card1.getValue());
        assertEquals(Color.TREFLE, card1.getColor());
        assertEquals(2, card1.getValueAsInt());

        Card card2 = new Card(Value.DAME, Color.CARREAU);
        assertNotNull(card2);
        assertEquals(Value.DAME, card2.getValue());
        assertEquals(Color.CARREAU, card2.getColor());
        assertEquals(12, card2.getValueAsInt());
    }

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

    @Test
    void testMultipleCardsAreIndependent() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.ROI, Color.COEUR);

        card1.setValue(Value.DAME);

        assertEquals(Value.DAME, card1.getValue());
        assertEquals(Value.ROI, card2.getValue());
    }
}

