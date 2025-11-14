package fr.pns.poker.model;

import fr.pns.poker.exception.DuplicateCardException;
import fr.pns.poker.exception.InvalidCardFormatException;
import fr.pns.poker.exception.InvalidHandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    // Tests du constructeur
    @Test
    void testConstructor_CreatesEmptyHand() {
        Hand newHand = new Hand();
        assertNotNull(newHand);
        assertEquals(0, newHand.size());
        assertNotNull(newHand.getCards());
        assertTrue(newHand.getCards().isEmpty());
    }

    // Tests de addCard
    @Test
    void testAddCard_SingleCard() {
        Card card = new Card(Value.AS, Color.PIQUE);
        hand.addCard(card);

        assertEquals(1, hand.size());
        assertTrue(hand.getCards().contains(card));
    }

    @Test
    void testAddCard_MultipleCards() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.ROI, Color.COEUR);
        Card card3 = new Card(Value.DAME, Color.CARREAU);

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        assertEquals(3, hand.size());
        assertTrue(hand.getCards().contains(card1));
        assertTrue(hand.getCards().contains(card2));
        assertTrue(hand.getCards().contains(card3));
    }

    @Test
    void testAddCard_FiveCards() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.ROI, Color.COEUR);
        Card card3 = new Card(Value.DAME, Color.CARREAU);
        Card card4 = new Card(Value.VALET, Color.TREFLE);
        Card card5 = new Card(Value.TEN, Color.PIQUE);

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        hand.addCard(card4);
        hand.addCard(card5);

        assertEquals(5, hand.size());
    }

    @Test
    void testAddCard_NullCard_ThrowsException() {
        InvalidHandException exception = assertThrows(
                InvalidHandException.class,
                () -> hand.addCard(null)
        );
        assertTrue(exception.getMessage().contains("Carte nulle"));
    }

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

    @Test
    void testAddCard_DuplicateCard_ThrowsException() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.AS, Color.PIQUE); // Même carte

        hand.addCard(card1);

        DuplicateCardException exception = assertThrows(
                DuplicateCardException.class,
                () -> hand.addCard(card2)
        );
        assertTrue(exception.getMessage().contains("double"));
        assertTrue(exception.getMessage().contains("APi"));
    }

    @Test
    void testAddCard_SameValueDifferentColor_NoException() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.AS, Color.COEUR); // Même valeur, couleur différente

        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(2, hand.size());
    }

    @Test
    void testAddCard_SameColorDifferentValue_NoException() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.ROI, Color.PIQUE); // Même couleur, valeur différente

        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(2, hand.size());
    }

    // Tests de getCards
    @Test
    void testGetCards_ReturnsCorrectList() {
        Card card1 = new Card(Value.AS, Color.PIQUE);
        Card card2 = new Card(Value.ROI, Color.COEUR);

        hand.addCard(card1);
        hand.addCard(card2);

        List<Card> cards = hand.getCards();
        assertNotNull(cards);
        assertEquals(2, cards.size());
        assertEquals(card1, cards.get(0));
        assertEquals(card2, cards.get(1));
    }

    @Test
    void testGetCards_EmptyHand() {
        List<Card> cards = hand.getCards();
        assertNotNull(cards);
        assertTrue(cards.isEmpty());
    }

    // Tests de size
    @Test
    void testSize_EmptyHand() {
        assertEquals(0, hand.size());
    }

    @Test
    void testSize_OneCard() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));
        assertEquals(1, hand.size());
    }

    @Test
    void testSize_FiveCards() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));
        hand.addCard(new Card(Value.ROI, Color.COEUR));
        hand.addCard(new Card(Value.DAME, Color.CARREAU));
        hand.addCard(new Card(Value.VALET, Color.TREFLE));
        hand.addCard(new Card(Value.TEN, Color.PIQUE));

        assertEquals(5, hand.size());
    }

    // Tests de getValues
    @Test
    void testGetValues_EmptyHand() {
        List<Integer> values = hand.getValues();
        assertNotNull(values);
        assertTrue(values.isEmpty());
    }

    @Test
    void testGetValues_SingleCard() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));

        List<Integer> values = hand.getValues();
        assertEquals(1, values.size());
        assertEquals(14, values.get(0));
    }

    @Test
    void testGetValues_MultipleCards() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));      // 14
        hand.addCard(new Card(Value.ROI, Color.COEUR));     // 13
        hand.addCard(new Card(Value.VALET, Color.CARREAU)); // 11
        hand.addCard(new Card(Value.FIVE, Color.TREFLE));   // 5
        hand.addCard(new Card(Value.TWO, Color.PIQUE));     // 2

        List<Integer> values = hand.getValues();
        assertEquals(5, values.size());
        assertEquals(14, values.get(0));
        assertEquals(13, values.get(1));
        assertEquals(11, values.get(2));
        assertEquals(5, values.get(3));
        assertEquals(2, values.get(4));
    }

    @Test
    void testGetValues_ReturnsCorrectIntegerValues() {
        hand.addCard(new Card(Value.TEN, Color.PIQUE));
        hand.addCard(new Card(Value.NINE, Color.COEUR));

        List<Integer> values = hand.getValues();
        assertEquals(10, values.get(0));
        assertEquals(9, values.get(1));
    }

    // Tests de createHand (méthode statique)
    @Test
    void testCreateHand_ValidFiveCards() {
        Hand createdHand = Hand.createHand("APi", "RCo", "DCa", "VTr", "10Pi");

        assertNotNull(createdHand);
        assertEquals(5, createdHand.size());

        List<Card> cards = createdHand.getCards();
        assertEquals(Value.AS, cards.get(0).getValue());
        assertEquals(Color.PIQUE, cards.get(0).getColor());
        assertEquals(Value.ROI, cards.get(1).getValue());
        assertEquals(Color.COEUR, cards.get(1).getColor());
    }

    @Test
    void testCreateHand_WithNumbers() {
        Hand createdHand = Hand.createHand("2Tr", "3Ca", "4Co", "5Pi", "6Tr");

        assertNotNull(createdHand);
        assertEquals(5, createdHand.size());

        List<Integer> values = createdHand.getValues();
        assertTrue(values.contains(2));
        assertTrue(values.contains(3));
        assertTrue(values.contains(4));
        assertTrue(values.contains(5));
        assertTrue(values.contains(6));
    }

    @Test
    void testCreateHand_NullArray_ThrowsException() {
        InvalidCardFormatException exception = assertThrows(
                InvalidCardFormatException.class,
                () -> Hand.createHand(null)
        );
        assertTrue(exception.getMessage().contains("manquante"));
    }

    @Test
    void testCreateHand_LessThanFiveCards_ThrowsException() {
        assertThrows(
                InvalidCardFormatException.class,
                () -> Hand.createHand("APi", "RCo", "DCa")
        );
    }

    @Test
    void testCreateHand_MoreThanFiveCards_ThrowsException() {
        assertThrows(
                InvalidCardFormatException.class,
                () -> Hand.createHand("APi", "RCo", "DCa", "VTr", "10Pi", "9Co")
        );
    }

    @Test
    void testCreateHand_EmptyArray_ThrowsException() {
        assertThrows(
                InvalidCardFormatException.class,
                () -> Hand.createHand()
        );
    }

    @Test
    void testCreateHand_WithDuplicateCards_ThrowsException() {
        assertThrows(
                DuplicateCardException.class,
                () -> Hand.createHand("APi", "RCo", "DCa", "VTr", "APi")
        );
    }

    @Test
    void testCreateHand_WithInvalidCard_ThrowsException() {
        assertThrows(
                Exception.class,
                () -> Hand.createHand("APi", "RCo", "XYZ", "VTr", "10Pi")
        );
    }

    @Test
    void testCreateHand_WithSpaces() {
        Hand createdHand = Hand.createHand(" APi ", "RCo", " DCa", "VTr ", "10Pi");

        assertNotNull(createdHand);
        assertEquals(5, createdHand.size());
    }

    @Test
    void testCreateHand_MixedCase() {
        Hand createdHand = Hand.createHand("api", "RCO", "dCa", "vTR", "10pi");

        assertNotNull(createdHand);
        assertEquals(5, createdHand.size());
    }

    // Tests d'intégration
    @Test
    void testHand_AddAndRemoveCheck() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));
        assertEquals(1, hand.size());

        hand.addCard(new Card(Value.ROI, Color.COEUR));
        assertEquals(2, hand.size());
    }

    @Test
    void testHand_CompleteWorkflow() {
        // Créer une main complète
        Hand fullHand = Hand.createHand("APi", "RCo", "DCa", "VTr", "10Pi");

        // Vérifier la taille
        assertEquals(5, fullHand.size());

        // Vérifier les valeurs
        List<Integer> values = fullHand.getValues();
        assertEquals(5, values.size());
        assertTrue(values.contains(14)); // AS
        assertTrue(values.contains(13)); // ROI
        assertTrue(values.contains(12)); // DAME
        assertTrue(values.contains(11)); // VALET
        assertTrue(values.contains(10)); // TEN

        // Vérifier les cartes
        List<Card> cards = fullHand.getCards();
        assertEquals(5, cards.size());
    }

    @Test
    void testHand_AllDifferentValues() {
        hand.addCard(new Card(Value.TWO, Color.PIQUE));
        hand.addCard(new Card(Value.FOUR, Color.COEUR));
        hand.addCard(new Card(Value.SIX, Color.CARREAU));
        hand.addCard(new Card(Value.EIGHT, Color.TREFLE));
        hand.addCard(new Card(Value.TEN, Color.PIQUE));

        List<Integer> values = hand.getValues();
        assertEquals(5, values.size());

        // Vérifier que toutes les valeurs sont différentes
        assertEquals(values.size(), values.stream().distinct().count());
    }

    @Test
    void testHand_AllSameColor() {
        hand.addCard(new Card(Value.AS, Color.PIQUE));
        hand.addCard(new Card(Value.ROI, Color.PIQUE));
        hand.addCard(new Card(Value.DAME, Color.PIQUE));
        hand.addCard(new Card(Value.VALET, Color.PIQUE));
        hand.addCard(new Card(Value.TEN, Color.PIQUE));

        List<Card> cards = hand.getCards();
        for (Card card : cards) {
            assertEquals(Color.PIQUE, card.getColor());
        }
    }

    @Test
    void testHand_MultipleInstancesAreIndependent() {
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        hand1.addCard(new Card(Value.AS, Color.PIQUE));

        assertEquals(1, hand1.size());
        assertEquals(0, hand2.size());
    }
}