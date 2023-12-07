package day7;

import java.util.HashMap;

public class Hand {

    private String cards;
    private Type type;
    private int bid;

    public Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;

        type = calcType();
    }

    private Type calcType() {
        boolean hasThreeOfAKind;
        boolean hasTwoOfAKind;
        HashMap<Character, Integer> characterCount = new HashMap<>();
        Type type;
        char[] characters = cards.toCharArray();

        //put number of characters
        for (char chr : characters) {
            if (characterCount.containsKey(chr)){
                characterCount.replace(chr, characterCount.get(chr) + 1);
            }else{
                characterCount.put(chr, 1);
            }
        }

        //calc type
        switch (characterCount.containsValue()){
            case 5: return Type.FiveOfAKind;
            case 4: return Type.FourOfAKind;
            case 3:
                if (checkFullHouse(characterCount)){
                    return Type.FullHouse;
                } else return Type.ThreeOfAKind;
            case 2:
                if(checkTwoPairs(characterCount)){
                    return Type.TwoPair;
                } else return Type.OnePair;
            default: return Type.HighCard;
        }

        return type;
    }

    private boolean checkTwoPairs(HashMap<Character, Integer> characterCount) {
    }

    private boolean checkFullHouse(HashMap<Character, Integer> characterCount) {
        return characterCount.containsValue(2) && characterCount.containsValue(3);
    }

    public String getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }
}
