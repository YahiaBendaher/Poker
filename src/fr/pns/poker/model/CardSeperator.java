package fr.pns.poker.model;

public class CardSeperator {
    private CardSeperator(){}

    public static Card seperateCard(String cardString){
        if(cardString==null) throw new IllegalArgumentException("cardString null");
        String cs = cardString.trim();
        if(cs.isEmpty()) throw new IllegalArgumentException("cardString empty");

        //séparer valeur et couleur

        String valuePart;
        String colorPart;
        if(cs.length()>=3 && cs.substring(0,2).equalsIgnoreCase("10")){
            valuePart = "10";
            colorPart = cs.substring(2);
        }else{
            valuePart = cs.substring(0,1);
            colorPart = cs.substring(1);

        }
        Value v = Value.getValueFromSymbol(valuePart);
        Color c = Color.getColorFromCode(colorPart);
        return new Card(v,c);

    }
}
