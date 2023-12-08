package day7;

import utils.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day7 {
    public static void main(String[] args) throws FileNotFoundException {
        int solution = solve("src/day7/input.txt");
        System.out.println("Solution to Part1 is: "+solution);
    }

    private static int solve(String path) throws FileNotFoundException {
        List<String> lines = utils.readFile(path);
        int totalWinnings = 0;
        List<Hand> hands = new ArrayList<>();


        for (String line : lines) {
            String cards = line.split(" ")[0];
            int bidding = Integer.parseInt(line.split(" ")[1]);
            Hand hand = new Hand(cards, bidding);
            hands.add(hand);
        }

        for ( Hand hand: hands) {
            if(hand.getType() == null){
                System.out.println(hand.getCards());
            }
        }
        Collections.sort(hands);

        for (int i = 0; i < hands.size(); i++) {
            totalWinnings += hands.get(i).getBid() * (i + 1);
        }

        return totalWinnings;
    }
}
