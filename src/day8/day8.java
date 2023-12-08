package day8;

import java.io.FileNotFoundException;
import java.util.*;

import utils.utils;

public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = utils.readFile("src\\day8\\input.txt");
        String directions = lines.get(0);
        String[][] nodes = new String[lines.size()][3];
        //lines.removeFirst(); lines.removeFirst(); lines.removeFirst();


        for (int i = 2; i < lines.size(); i++) {
            String thisNode = lines.get(i).split(" = ")[0];
            String otherNodes = lines.get(i).split(" = ")[1].split("\\(")[1].split("\\)")[0];
            String leftNode = otherNodes.split(", ")[0];
            String rightNode = otherNodes.split(", ")[1];

            nodes[i-2][0] = thisNode;
            nodes[i-2][1] = leftNode;
            nodes[i-2][2] = rightNode;
        }

        int steps = 0;
        int direction = 0;
        int i = 0;
        int j = 0;
        while (!nodes[i][0].equals("ZZZ")){
            String[] currentNode = nodes[i];
            char currDir = directions.charAt(direction);
            if (currDir == 'L'){
                String destination = currentNode[1];
                for (j = 0; j < nodes.length; j++) {
                    if(nodes[j][0].equals(destination)){
                        break;
                    }
                }
            }else if (currDir == 'R'){
                String destination = currentNode[2];
                for (j = 0; j < nodes.length; j++) {
                    if(nodes[j][0].equals(destination)){
                        break;
                    }
                }
            }
            i = j;
            direction = (direction + 1) % 3;
            steps++;
        }
        System.out.println("Number of Steps taken: "+steps);
    }
}
