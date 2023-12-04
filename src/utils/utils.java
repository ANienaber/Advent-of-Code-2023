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

    public static int getNumValue(String numString){
        int num = 0;
        for (int i = 0; i < numString.length(); i++) {
            num *= 10;
            num += Character.getNumericValue(numString.charAt(i));
        }
        return num;
    }

}
