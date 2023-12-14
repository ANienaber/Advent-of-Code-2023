package day14;

import java.io.FileNotFoundException;
import java.util.List;

public class day14 {
    public static void main(String[] args) throws FileNotFoundException {
        solve1();
        solve2();
    }

    private static void solve1() throws FileNotFoundException {
        List<String> lines = utils.utils.readFile("src/day14/input");
        Character[][] platform = getPlatform(lines);
        while(checkRollability(platform, Direction.NORTH)){
            rollOnce(platform, Direction.NORTH);
            printPlatform(platform);
        }
        System.out.println("The total load is: "+getLoad(platform));
    }

    private static void solve2() throws FileNotFoundException {
        List<String> lines = utils.utils.readFile("src/day14/test.txt");
        Character[][] platform = getPlatform(lines);
        for (int i = 0; i < 1000000000; i++) {
            while(checkRollability(platform, Direction.NORTH)){
                rollOnce(platform, Direction.NORTH);
            }
            while(checkRollability(platform, Direction.WEST)){
                rollOnce(platform, Direction.WEST);
            }
            while(checkRollability(platform, Direction.SOUTH)){
                rollOnce(platform, Direction.SOUTH);
            }
            while(checkRollability(platform, Direction.EAST)){
                rollOnce(platform, Direction.EAST);
            }
            //printPlatform(platform);
        }

        System.out.println("The total load is: "+getLoad(platform));
    }

    private static void printPlatform(Character[][] platform) {
        System.out.println();
        for (Character[] characters : platform) {
            for (int j = 0; j < platform[0].length; j++) {
                System.out.print(characters[j]);
            }
            System.out.println();
        }
    }

    private static Character[][] getPlatform(List<String> lines) {
        Character[][] platform = new Character[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                platform[i][j] = lines.get(i).charAt(j);
            }
        }
        return platform;
    }

    private static void rollOnce(Character[][] platform, Direction direction) {
        int top = 0, bottom = 0, left = 0, right = 0, x = 0, y = 0;
        switch (direction){
            case NORTH -> {
                top = 1;
                y = -1 ;
            }
            case EAST -> {
                right = 1;
                x = 1;
            }
            case SOUTH -> {
                bottom = 1;
                y = 1;
            }
            case WEST -> {
                left = 1;
                x = -1;
            }
        }

        for (int j = 0 + top; j < platform.length - bottom; j++) {
            for (int k = 0 + left; k < platform[0].length - right; k++) {
                if (platform[j][k] == 'O'){
                    if (platform[j + y][k + x] == '.'){
                        platform[j][k] = '.';
                        platform[j + y][k + x] = 'O';
                    }
                }
            }
        }
    }

    private static void roll(Character[][] platform, Direction direction) {
        int top = 0, bottom = 0, left = 0, right = 0, x = 0, y = 0;
        switch (direction){
            case NORTH -> {
                top = 1;
                y = -1 ;
            }
            case EAST -> {
                right = 1;
                x = 1;
            }
            case SOUTH -> {
                bottom = 1;
                y = 1;
            }
            case WEST -> {
                left = 1;
                x = -1;
            }
        }

        for (int i = 0 + top; i < platform.length - bottom; i++) {
            for (int j = 0 + left; j < platform[0].length - right; j++) {
                for (int k = 0 + top; k < platform.length - bottom; k++) {
                    for (int l = 0 + left; l < platform[0].length - right; l++) {
                        if (platform[i][j] == 'O'){
                            if (platform[i + y][j + x] == '.'){
                                platform[i][j] = '.';
                                platform[i + y][j + x] = 'O';
                            }
                        }
                    }

                }

            }
        }
    }

    private static boolean checkRollability(Character[][] platform, Direction direction) {
        int top = 0, bottom = 0, left = 0, right = 0, x = 0, y = 0;
        switch (direction){
            case NORTH -> {
                top = 1;
                y = -1 ;
            }
            case EAST -> {
                right = 1;
                x = 1;
            }
            case SOUTH -> {
                bottom = 1;
                y = 1;
            }
            case WEST -> {
                left = 1;
                x = -1;
            }
        }

        for (int i = 0 + top; i < platform.length - bottom; i++) {
            for (int j = 0 + left; j < platform[0].length - right; j++) {
                if (platform[i][j] == 'O' && platform[i + y][j + x] == '.'){
                    return true;
                }
            }
        }
        return false;
    }

    private static int getLoad(Character[][] platform) {
        int sum = 0;
        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[0].length; j++) {
                if (platform[i][j] == 'O'){
                    sum += platform.length - i;
                }
            }
        }
        return sum;
    }
}
