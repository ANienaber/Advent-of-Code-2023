package day13;

import day14.Joker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class day13 {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Puzzle 1:-----------------------------------");
        //solve1("src/day13/input.txt");
        System.out.println("Puzzle 2:-----------------------------------");
        solve2("src/day13/input.txt");
    }

    private static void solve1(String path) throws FileNotFoundException {
        List<List<String>> sets = getSets(path);
        int sum = 0;
        for (List<String> set : sets) {
            System.out.println("Set: ");
            for ( String line : set) {
                System.out.println(line);
            }
            int setSum = getSum(set);
            System.out.println("Sum for this set is: "+setSum);
            sum += setSum;
        }
        System.out.println("\nOverall sum is: "+sum);
    }

    private static int getSum(List<String> set) {
        Joker joker = new Joker(false);
        int rowSum = getRowMirror(set, joker);
        if(rowSum > 0){
            return rowSum;
        } else {
            return getColumnMirror(set, joker);
        }
    }

    private static void solve2(String path) throws FileNotFoundException {
        List<List<String>> sets = getSets(path);
        int sum = 0;
        for (List<String> set : sets) {
            System.out.println("Set: ");
            for (String line : set) {
                System.out.println(line);
            }
            int setSum = getSmudgeSum(set);
            System.out.println("Sum for this set is: "+setSum);
            sum += setSum;
        }
        System.out.println("\nOverall sum is: "+sum);
    }

    private static int getSmudgeSum(List<String> set) {
        Joker jokerRow = new Joker(true);
        Joker jokerCol = new Joker(true);
        int rowSum = getRowMirror(set, jokerRow);
        if(rowSum > 0){
            return rowSum;
        } else {
            return getColumnMirror(set, jokerCol);
        }
    }



    private static int getRowMirror(List<String> set, Joker joker) {
        int lol;
        boolean startJoker = joker.isJoker();
        boolean found = false;
        int mirrorIdx = 0;
        for (int i = mirrorIdx; i < set.size() - 1; i++) {
            joker.setJoker(startJoker);
            //System.out.println("Joker returned!");
            if(rowMatches(set, i, i+1, joker)){
                //System.out.println("New Mirror line found");
                found = true;
                mirrorIdx = i;
                //check other line
                for (int j = 1; j < set.size()/2 ; j++) {
                    // mirrorIdx +j +1 bc mirrorIdx is first of the two mirrored lines
                    int rightIdx = mirrorIdx + j + 1;
                    int leftIdx = mirrorIdx - j;
                    if(leftIdx >= 0 && rightIdx < set.size()
                            && !(rowMatches(set, leftIdx, rightIdx, joker))){
                        found = false;
                        break;
                    }
                }
            }
            if (found){
                if(!joker.isJoker()) {
                    System.out.println("Mirror between row "+(mirrorIdx+1)+" and "+(mirrorIdx+2));
                    return 100 * (mirrorIdx +1);
                } else found = false;
            }
        }
        System.out.println("Row not found");
        return 0;
    }

    private static boolean rowMatches(List<String> set, int i1, int i2, Joker joker) {
        String row1 = set.get(i1);
        String row2 = set.get(i2);
        for (int i = 0; i < row1.length(); i++) {
            if (row1.charAt(i) != row2.charAt(i)) {
                if (joker.isJoker()){
                    joker.setJoker(false);
                    //System.out.println("Smudge has been fixed in row "+(i1+1)+" / "+(i2+1)+" and col "+(i+1));
                } else return false;
            }
        }
        return true;
    }

    private static int getColumnMirror(List<String> set, Joker joker) {
        boolean startJoker = joker.isJoker();
        boolean found = false;
        int mirrorCol = 0;
        int lineLength = set.get(1).length();
        for (int i = mirrorCol; i < lineLength - 1; i++) {
            joker.setJoker(startJoker);
            //if column matches
            if (colMatches(set, i, i + 1, joker)) {

                //System.out.println("New Mirror line found");
                found = true;
                mirrorCol = i;
                //check other columns
                for (int j = 1; j < lineLength / 2; j++) {
                    // mirrorIdx +j +1 bc mirrorIdx is first of the two mirrored lines
                    int rightCol = mirrorCol + j + 1;
                    int leftCol = mirrorCol - j;
                    if (leftCol >= 0 && rightCol < lineLength
                            && !(colMatches(set, leftCol, rightCol, joker))) {
                        found = false;
                        break;
                    }
                }
            }

            if (found){
                if(!joker.isJoker()) {
                    System.out.println("Mirror between col " + (mirrorCol+1) + " and " + (mirrorCol + 2));
                    return mirrorCol + 1;
                } else found = false;
            }
        }
        System.out.println("Col not found");
        return 0;
    }



    private static boolean colMatches(List<String> set, int col1, int col2, Joker joker) {
        int i = 0;
        for (String s : set) {
            if (s.charAt(col1) != s.charAt(col2)) {
                if (joker.isJoker()){
                    joker.setJoker(false);
                    //System.out.println("Smudge has been fixed in col "+(col1+1)+" / "+(col2+1)+" and col "+(i+1));
                } else return false;
            }
            i++;
        }
        return true;
    }

    private static List<List<String>> getSets(String path) throws FileNotFoundException {
        List<String> lines = utils.utils.readFile(path);
        List<List<String>> sets = new ArrayList<>();
        int startIdx = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty() || i == lines.size() - 1){
                sets.add(new ArrayList<>(lines.subList(startIdx, i)));
                startIdx = i+1;
            }
        }
        return sets;
    }
}
