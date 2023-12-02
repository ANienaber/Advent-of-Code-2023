package day1;

import org.apache.groovy.contracts.generation.TryCatchBlockGenerator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

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
