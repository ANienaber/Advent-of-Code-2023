package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
