package day7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Hand implements Comparable<Hand>{

    private final String cards;
    private final Type type;
    private final int bid;

    public Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        type = calcType();
    }

    private Type calcType() {
        HashMap<Character, Integer> characterCount = new HashMap<>();
        char[] characters = cards.toCharArray();
        int jokerCount;

        //put number of characters
        for (char chr : characters) {
            if (characterCount.containsKey(chr)){
                characterCount.replace(chr, characterCount.get(chr) + 1);
            }else{
                characterCount.put(chr, 1);
            }
        }

        //Part2
        jokerCount = characterCount.getOrDefault('J', 0);
        //border case all Jokers
        if(jokerCount == 5){
            return Type.FiveOfAKind;
        }

        //Jokers need to be removed so they arent maxChar
        characterCount.remove('J');
        Character maxChar = Collections.max(characterCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        characterCount.put(maxChar, characterCount.get(maxChar) + jokerCount);
        //end part 2

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

    public Type getType() {
        return type;
    }


    @Override
    public int compareTo(Hand otherHand) {
        if (this.type.compareTo(otherHand.getType()) != 0) {
            return this.type.compareTo(otherHand.getType());
        } else {
            char[] thisCards = this.cards.toCharArray();
            char[] otherCards = otherHand.getCards().toCharArray();
            for (int i = 0; i < thisCards.length; i++) {
                int thisVal = (Character.isDigit(thisCards[i])) ? Character.getNumericValue(thisCards[i]) :
                        CardValue.valueOf(String.valueOf(thisCards[i])).getNumVal();
                int otherVal = (Character.isDigit(otherCards[i])) ? Character.getNumericValue(otherCards[i]) :
                        CardValue.valueOf(String.valueOf(otherCards[i])).getNumVal();

                if (thisVal > otherVal){
                    return 1;
                } else if (thisVal < otherVal){
                    return -1;
                }
            }
            //if for finished the Cards are identical
            return 0;
        }
    }
}
