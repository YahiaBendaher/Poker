package fr.pns.poker.rules;

import fr.pns.poker.model.Card;

import java.util.Comparator;
import java.util.List;

public class StraightRule {

    public static boolean getStraight(List<Card> cards) {
        cards.sort(Comparator.comparingInt(Card::getValue));
        int i = 0 ;
        boolean ok = true;
        while(ok && i < cards.size()-1) {
            if ((cards.get(i + 1).getValue() - cards.get(i).getValue()) == 1) {
                i++;
            } else {
                ok = false;
            }
        }
        return ok;
    }
}
