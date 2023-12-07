package day7;

import utils.utils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class day7 {
    public static void main(String[] args) throws FileNotFoundException {
        int solution = solve1("src/day7/test.txt");
        System.out.println("Solution to Part1 is: "+solution);


        solution = solve2("src/day7/test.txt");
        System.out.println("Solution to Part2 is: "+solution);
    }

    private static int solve1(String path) throws FileNotFoundException {
        List<String> lines = utils.readFile(path);
        int totalWinnings = 0;


        for (String line : lines) {
            String hand = line.split(" ")[0];
            int bidding = Integer.parseInt(line.split(" ")[1]);
            int rank = 0;

        }

        return totalWinnings;
    }

    private static int solve2(String path) {
        return 0;
    }
}
