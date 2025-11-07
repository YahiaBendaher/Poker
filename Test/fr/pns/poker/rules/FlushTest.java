package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Color;
import fr.pns.poker.model.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlushTest {

    @Test
    @DisplayName("Main de 5 cartes de même couleur → Couleur (Flush)")
    void testGetCouleurTrue() {
        List<Card> cards = List.of(
                new Card(Value.TWO, Color.PIQUE),
                new Card(Value.FIVE, Color.PIQUE),
                new Card(Value.NINE, Color.PIQUE),
                new Card(Value.VALET, Color.PIQUE),
                new Card(Value.ROI, Color.PIQUE)
        );
        assertTrue(Flush.getCouleur(cards));
    }

    @Test
    @DisplayName("Main avec couleurs différentes → Pas Couleur")
    void testGetCouleurFalse() {
        List<Card> cards = List.of(
                new Card(Value.TWO, Color.PIQUE),
                new Card(Value.FIVE, Color.COEUR),
                new Card(Value.NINE, Color.PIQUE),
                new Card(Value.VALET, Color.PIQUE),
                new Card(Value.ROI, Color.CARREAU)
        );
        assertFalse(Flush.getCouleur(cards));
    }
}
