package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Color;
import fr.pns.poker.model.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SameColorRuleTest {

    @Test
    @DisplayName("Deux cartes de même couleur → true")
    void sameColorShouldReturnTrue() {
        List<Card> cards = List.of(
                new Card(Value.TWO, Color.PIQUE),
                new Card(Value.FIVE, Color.PIQUE)
        );
        assertTrue(SameColorRule.haveSameColor(cards));
    }

    @Test
    @DisplayName("Deux cartes de couleur différente → false")
    void differentColorShouldReturnFalse() {
        List<Card> cards = List.of(
                new Card(Value.TWO, Color.PIQUE),
                new Card(Value.FIVE, Color.COEUR)
        );
        assertFalse(SameColorRule.haveSameColor(cards));
    }

    @Test
    @DisplayName("Liste vide → false")
    void emptyListShouldReturnFalse() {
        List<Card> cards = List.of();
        assertFalse(SameColorRule.haveSameColor(cards));
    }

    @Test
    @DisplayName("Trois cartes dont une différente → false")
    void mixedColorsShouldReturnFalse() {
        List<Card> cards = List.of(
                new Card(Value.AS, Color.TREFLE),
                new Card(Value.ROI, Color.CARREAU),
                new Card(Value.DAME, Color.TREFLE)
        );
        assertFalse(SameColorRule.haveSameColor(cards));
    }
    @Test
    @DisplayName("Trois cartes de même couleur → devrait retourner true avec haveSameColor()")
    void threeCardsSameColorShouldReturnTrue_withHaveSameColor() {
        List<Card> cards = List.of(
                new Card(Value.TWO, Color.PIQUE),
                new Card(Value.FIVE, Color.PIQUE),
                new Card(Value.ROI, Color.PIQUE)
        );

        assertTrue(SameColorRule.haveSameColor(cards),
                "Les trois cartes sont des PIQUE, donc haveSameColor() doit retourner true");
    }

    @Test
    @DisplayName("Trois cartes dont une couleur différente → devrait retourner false avec haveSameColor()")
    void threeCardsDifferentColorShouldReturnFalse_withHaveSameColor() {
        List<Card> cards = List.of(
                new Card(Value.TWO, Color.PIQUE),
                new Card(Value.FIVE, Color.COEUR),
                new Card(Value.ROI, Color.PIQUE)
        );

        assertFalse(SameColorRule.haveSameColor(cards),
                "Toutes les cartes ne sont pas de la même couleur, donc false attendu");
    }

}
