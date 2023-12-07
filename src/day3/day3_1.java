package day3;

import utils.utils;

import java.io.FileNotFoundException;
import java.util.List;

/*
--- Day 3: Gear Ratios ---

You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water
source, but this is as far as he can bring you. You go inside.

It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.

"Aaah!"

You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone!
The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.

The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.

The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers
and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a
"part number" and should be included in your sum. (Periods (.) do not count as a symbol.)

Here is an example engine schematic:

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
In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right)
and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.

Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in
the engine schematic?
 */

public class day3_1 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day3/input3.txt");
        int sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            System.out.print(line+"|| Part numbers ");
            int j = 0;

            while (j < line.length()) {
                int number = 0;
                boolean isPart = false;
                while (j < line.length() && line.charAt(j) >= '0' && line.charAt(j) <= '9') {
                    number *= 10;
                    number += Character.getNumericValue(line.charAt(j));
                    if(checkAdjacentSpaces(lines, i, j)){
                        isPart = true;
                    }
                    j++;
                }
                if (isPart){
                    sum += number;
                    System.out.print(number+" ");
                }
                j++;
            }
            System.out.println();
        }
        System.out.println(sum);
    }

    public static boolean checkAdjacentSpaces(List<String> lines, int row, int column){
        int rowToCheck;
        int columnToCheck;
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
                if( !(charToCheck >= '0' && charToCheck <= '9') && charToCheck != '.'){
                    return true;
                }
            }
        }
        return false;
    }

}
