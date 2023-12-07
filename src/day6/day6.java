package day6;

import utils.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class day6 {
    public static void main(String[] args) throws FileNotFoundException {
        final long startTime = System.currentTimeMillis();

        long solution1 = solve(1);
        System.out.println("Solution to 1 is: "+solution1);

        long solution2 = solve(2);
        System.out.println("Solution to 2 is: "+solution2);

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time in milliseconds: " + (endTime - startTime));
    }

    private static long solve(int num) throws FileNotFoundException {
        long mul = 1;
        long possibilities = 0;
        List<Long> times;
        List<Long> distances;
        if (num == 1) {
            times = getAllNumbers(0);
            distances = getAllNumbers(1);
        } else{
            times = getAsOneNumber(0);
            distances = getAsOneNumber(1);
        }

        for (int i = 0; i < times.size(); i++) {
            for (int j = 0; j < times.get(i); j++) {
                long myDistance = j * (times.get(i) - j);
                if(myDistance > distances.get(i)){
                    possibilities++;
                }
            }
            System.out.println("For race time "+ times.get(i) + " there are "+possibilities+" possibilities");
            mul *= possibilities;
            possibilities = 0;
        }

        return mul;
    }

    private static List<Long> getAsOneNumber(int lineIdx) throws FileNotFoundException {
        String line = utils.readFile("src/day6/input.txt").get(lineIdx).split(":")[1];
        List<Long> times = new ArrayList<>();
        long num = 0;
        for (int i = 0; i < line.length(); i++) {
            if(!Character.isDigit(line.charAt(i))){
                continue;
            }
            num *= 10;
            num += Character.getNumericValue(line.charAt(i));
        }
        times.add(num);
        return times;
    }

    private static List<Long> getAllNumbers(int lineIdx) throws FileNotFoundException {
        String line = utils.readFile("src/day6/input.txt").get(lineIdx);
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if(!Character.isDigit(line.charAt(i))){
                continue;
            }
            int start = i;
            while( i < line.length() && Character.isDigit(line.charAt(i))){
                i++;
            }
            times.add(Long.parseLong(line.substring(start, i)));
        }
        return times;
    }
}
