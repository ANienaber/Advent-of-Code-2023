package day1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
--- Day 1: Trebuchet?! ---
Something is wrong with global snow production, and you've been selected to take a look. The Elves have
even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.

You've been doing this long enough to know that to restore snow operations, you need to check all fifty
stars by December 25th.

Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar;
the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even
ending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang
on did you just say the sky ("of course, where do you think snow comes from") when you realize that the
Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").

As they're making the final adjustments, they discover that their calibration document (your puzzle input)
has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently,
the Elves are having trouble reading the values on the document.

The newly-improved calibration document consists of lines of text; each line originally contained a specific
calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining
the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
 */

public class day1_1 {

    public static void main(String[] args) throws IOException {
        File file = new File("src/day1/input1_1.txt");
        int sum = 0;

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                int num = 0;

                for (int i = 0; i < line.length(); ++i) {
                    if (Character.isDigit(line.charAt(i))) {
                        num += 10 * Character.getNumericValue(line.charAt(i));
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
                System.out.println("number for last line: "+num);
            }
        }
        System.out.println("final sum "+sum);
    }

}
