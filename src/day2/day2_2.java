package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * --- Part Two ---
The Elf says they've stopped producing snow because they aren't getting any water! He isn't sure why the water stopped; however, he can show you how to get to the water source to check it out for yourself. It's just up ahead!

As you continue your walk, the Elf poses a second question: in each game you played, what is the fewest number of cubes of each color that could have been in the bag to make the game possible?

Again consider the example games from earlier:

Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
In game 1, the game could have been played with as few as 4 red, 2 green, and 6 blue cubes. If any color had even one fewer cube, the game would have been impossible.
Game 2 could have been played with a minimum of 1 red, 3 green, and 4 blue cubes.
Game 3 must have been played with at least 20 red, 13 green, and 6 blue cubes.
Game 4 required at least 14 red, 3 green, and 15 blue cubes.
Game 5 needed no fewer than 6 red, 3 green, and 2 blue cubes in the bag.
The power of a set of cubes is equal to the numbers of red, green, and blue cubes multiplied together. The power of the minimum set of cubes in game 1 is 48. In games 2-5 it was 12, 1560, 630, and 36, respectively. Adding up these five powers produces the sum 2286.

For each game, find the minimum set of cubes that must have been present. What is the sum of the power of these sets?
 */

public class day2_2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/day2/input2.txt");
        int sum = 0;
        int maxRed;
        int maxGreen;
        int maxBlue;

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()) {
                maxRed = 0; maxGreen = 0; maxBlue = 0;
                String line = scanner.nextLine();
                System.out.println(line);

                String[] game = line.split(": ");
                String[] turns = game[1].split("; ");
                
                for (int i = 0; i < turns.length; i++) {
                    //Array of {x green, x blue, x red} entries
                    String[] entries = turns[i].split(", ");

                    for (int j = 0; j < entries.length; j++) {
                        String[] ballcounts = entries[j].split(" ");
                        int num = getNumValue(ballcounts[0]);

                        switch (ballcounts[1]) {
                            case "red":     maxRed = Math.max(maxRed, num); break;
                            case "green":   maxGreen = Math.max(maxGreen, num); break;
                            case "blue":    maxBlue = Math.max(maxBlue, num); break;
                            default:        break;
                        }
                    }
                }
                int powerSum = maxRed * maxGreen * maxBlue;
                sum += powerSum;
                System.out.println("max red balls: "+maxRed+" max green balls: "+maxGreen+ " max blue balls: "+maxBlue);
            }
        }
        System.out.println("Sum: "+sum);
    }

    static int getNumValue(String string){
        int num = 0;
        for (int i = 0; i < string.length(); i++) {
            num *= 10;
            num += Character.getNumericValue(string.charAt(i));
        }
        return num;
    }
}
