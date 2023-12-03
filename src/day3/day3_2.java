package day3;

import utils.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class day3_2 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = utils.readFile("src/day3/input3.txt");
        int sum = 0;
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
