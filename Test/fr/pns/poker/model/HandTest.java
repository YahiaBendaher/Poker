package fr.pns.poker.model;

import fr.pns.poker.exception.InvalidCardFormatException;
import fr.pns.poker.exception.InvalidHandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    // Test 1: Constructeur -> main vide initialisée
    @Test
    void testConstructor_CreatesEmptyHand() {
        Hand newHand = new Hand();
        assertNotNull(newHand);
        assertEquals(0, newHand.size());
        assertNotNull(newHand.getCards());
        assertTrue(newHand.getCards().isEmpty());
    }

    // Test 2: addCard ajoute une carte correctement
    @Test
    void testAddCard_SingleCard() {
        Card card = new Card(Value.AS, Color.PIQUE);
        hand.addCard(card);
        assertEquals(1, hand.size());
        assertTrue(hand.getCards().contains(card));
    }

    // Test 3: addCard refuse plus de 5 cartes
    @Test
    void testAddCard_MoreThanFiveCards_ThrowsException() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));
        hand.addCard(new Card(Value.ROI, Color.COEUR));
        hand.addCard(new Card(Value.DAME, Color.CARREAU));
        hand.addCard(new Card(Value.VALET, Color.TREFLE));
        hand.addCard(new Card(Value.TEN, Color.PIQUE));
        InvalidHandException exception = assertThrows(
                InvalidHandException.class,
                () -> hand.addCard(new Card(Value.NINE, Color.COEUR))
        );
        assertTrue(exception.getMessage().contains("5 cartes"));
    }

    // Test 4: createHand valide avec 5 cartes
    @Test
    void testCreateHand_ValidFiveCards() {
        Hand createdHand = Hand.createHand("APi", "RCo", "DCa", "VTr", "10Pi");
        assertNotNull(createdHand);
        assertEquals(5, createdHand.size());
        assertEquals(Value.AS, createdHand.getCards().get(0).getValue());
        assertEquals(Color.PIQUE, createdHand.getCards().get(0).getColor());
    }

    // Test 5: createHand invalide (<5 cartes) -> exception
    @Test
    void testCreateHand_LessThanFiveCards_ThrowsException() {
        assertThrows(
                InvalidCardFormatException.class,
                () -> Hand.createHand("APi", "RCo", "DCa")
        );
    }
}