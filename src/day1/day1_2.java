package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class day1_2 {
    public static void main(String[] args) throws FileNotFoundException {
        final long startTime = System.currentTimeMillis();

        File file = new File("src/day1/input1_1.txt");
        int sum = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("line without digits: " + line);

                int num = 0;

                line = insertDigit(line);
                System.out.println("line with digits: " + line);

                for (int i = 0; i < line.length(); ++i) {
                    char thisChar = line.charAt(i);
                    if (Character.isDigit(thisChar)) {
                        num += 10 * Character.getNumericValue(thisChar);
                        break;
                    }
                }
                for (int i = line.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(line.charAt(i))) {
                        num += Character.getNumericValue(line.charAt(i));
                        break;
                    }
                }
                sum += num;
                System.out.println("number for last line: " + num);
            }
        }
        System.out.println("final sum " + sum);

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time in milliseconds: " + (endTime - startTime));
    }

    static String insertDigit(String string) {

        HashMap<String, Integer> numberWords = new HashMap<>();
        numberWords.put("one", 1);
        numberWords.put("two", 2);
        numberWords.put("three", 3);
        numberWords.put("four", 4);
        numberWords.put("five", 5);
        numberWords.put("six", 6);
        numberWords.put("seven", 7);
        numberWords.put("eight", 8);
        numberWords.put("nine", 9);

            for (String numberWord : numberWords.keySet()) {
                int idx = string.indexOf(numberWord);
                while (string.indexOf(numberWord, idx) > -1) {
                    idx = string.indexOf(numberWord, idx);
                    //insert in the middle of the word to not cut off cases like eightwo
                    string = string.substring(0, idx + 1) + numberWords.get(numberWord) + string.substring(idx + 1);
                    idx += numberWord.length();
                }
            }

        return string;
    }
}
