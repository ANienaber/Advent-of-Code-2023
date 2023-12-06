package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utils {

    public static List<String> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    public static long getLongNumValue(String numString){
        long num = 0;
        for (int i = 0; i < numString.length(); i++) {
            num *= 10;
            num += Character.getNumericValue(numString.charAt(i));
        }
        return num;
    }

    public static int getIntNumValue(String numString){
        int num = 0;
        for (int i = 0; i < numString.length(); i++) {
            num *= 10;
            num += Character.getNumericValue(numString.charAt(i));
        }
        return num;
    }

    public static long[] getLongNumValues(String line) {
        String[] stringNums = line.split(" ");
        long[] nums = new long[stringNums.length];

        for (int i = 0; i < stringNums.length; i++) {
            nums[i] = getLongNumValue(stringNums[i]);
        }
        return nums;
    }

    public static int[] getIntNumValues(String line) {
        String[] stringNums = line.split(" ");
        int[] nums = new int[stringNums.length];

        for (int i = 0; i < stringNums.length; i++) {
            nums[i] = Integer.parseInt(stringNums[i]);
        }
        return nums;
    }

    public static long getMinimum(long[] nums) {
        long min = Long.MAX_VALUE;
        for (long num : nums) {
            min = Math.min(num, min);
        }
        return min;
    }

    public static Long getMinimum(ArrayList<Long> nums) {
        long min = Long.MAX_VALUE;
        for (long num : nums) {
            min = Math.min(num, min);
        }
        return min;
    }
}
