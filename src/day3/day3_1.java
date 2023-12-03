package day3;

import utils.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
                    isPart = false;
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
