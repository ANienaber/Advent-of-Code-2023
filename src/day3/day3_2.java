package day3;

import utils.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/*
The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the
closest gondola, finally ready to ascend to the water source.

You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone
labeled "help", so you pick it up and the engineer answers.

Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding
a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station.
You exit the gondola.

The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that
is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.

This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out
which gear needs to be replaced.

Consider the same engine schematic again:

467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35,
so its gear ratio is 16345. The second gear is in the lower right; its gear ratio is 451490.
(The * adjacent to 617 is not a gear because it is only adjacent to one part number.)
Adding up all of the gear ratios produces 467835.

What is the sum of all of the gear ratios in your engine schematic?
 */

public class day3_2 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day3/input3.txt");
        int sum;
        List<Gear> gears = getGears(lines);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int j = 0;
            List<Coordinates> neighbouringGears = new ArrayList<>();

            while (j < line.length()) {
                int number = 0;
                neighbouringGears.clear();
                while (j < line.length() && line.charAt(j) >= '0' && line.charAt(j) <= '9') {
                    number *= 10;
                    number += Character.getNumericValue(line.charAt(j));
                    neighbouringGears.addAll(checkAdjacentSpaces(lines, i, j));
                    j++;
                }
                //if number has been found add it to right gear
                if(number > 0){
                    for (Gear gear : gears) {
                        for (Coordinates neighbouringGear : neighbouringGears) {
                            if (gear.getCor().equals(neighbouringGear)) {
                                gear.addNumber(number);
                                break;
                            }
                        }
                    }
                }

                j++;
            }
        }

        sum = calGearSum(gears);

        System.out.println(sum);

    }

    private static int calGearSum(List<Gear> gears) {
        int sum = 0;
        for (Gear gear:gears) {
            if(!gear.isTooManyNumbers()){
                sum += gear.getMul();
            }
        }
        return sum;
    }

    public static List<Gear> getGears(List<String> lines){
        List<Gear> gears = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if(line.charAt(j) == '*'){
                    gears.add(new Gear(new Coordinates(i,j)));
                }
            }
        }
        return gears;
    }

    public static List<Coordinates> checkAdjacentSpaces(List<String> lines, int row, int column){
        int rowToCheck;
        int columnToCheck;
        List<Coordinates> gears = new ArrayList<>();
        Coordinates[] directions = new Coordinates[]{
                new Coordinates(-1,-1),
                new Coordinates(-1,0),
                new Coordinates(-1,1),
                new Coordinates(0,-1),
                new Coordinates(0,1),
                new Coordinates(1,-1),
                new Coordinates(1,0),
                new Coordinates(1,1)};

        for (Coordinates direction : directions) {
            rowToCheck = row + direction.getX();
            columnToCheck = column + direction.getY();
            if((rowToCheck) >= 0 && (rowToCheck < lines.size())
                    && (columnToCheck >= 0) && (columnToCheck < lines.get(rowToCheck).length())){
                char charToCheck = lines.get(rowToCheck).charAt(columnToCheck);
                if(charToCheck == '*'){
                    gears.add(new Coordinates(rowToCheck, columnToCheck));
                }
            }
        }
        return gears;
    }
}
