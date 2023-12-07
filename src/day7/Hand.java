package day7;

import java.util.HashMap;

public class Hand implements Comparable{

    private String cards;
    private Type type;
    private int bid;

    public Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;

        type = calcType();
    }

    private Type calcType() {
        HashMap<Character, Integer> characterCount = new HashMap<>();
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
        for (int i = 5; i >= 0; i--) {
            if (characterCount.containsValue(i)){
                switch(i){
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
            }
        }
        
        return null;

    }

    private boolean checkTwoPairs(HashMap<Character, Integer> characterCount) {
        int pairCount = 0;
        for (int charCount : characterCount.values()) {
            if (charCount == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
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

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Hand)) {
            throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        } 
        Hand otherHand = (Hand) o;
        if(this.cards.equals(otherHand.getCards())){
            return 0;
        }
        if(this.type.compareTo(otherHand.type) != 0){
            return this.type.compareTo(otherHand.type);
        } else{
            //Rank is the same now iterate String to find higher card 
        }
}
