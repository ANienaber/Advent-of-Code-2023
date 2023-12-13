package day8;

import java.io.FileNotFoundException;
import java.util.*;

import groovy.lang.GString;
import utils.utils;

public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = utils.readFile("src\\day8\\input.txt");
        String directions = lines.get(0);
        String[][] nodes = new String[lines.size()-2][5];
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




        long steps = 0;
        int direction = 0;
        int i = 0;
        int j = 0;
        List<String[]> currentNodes = new ArrayList<>();
        List<String[]> nextNodes = new ArrayList<>();

        //find AAA for start
        for (i = 0; i < nodes.length ; i++) {
            if(nodes[i][0].charAt(2) == 'A'){
                currentNodes.add(nodes[i]);
            }
        }
        int startNodeCount = currentNodes.size();


        while (!checkNodesForZ(currentNodes)){
            //System.out.println(nodes[i][0] + " " + nodes[i][1] + " " + nodes[i][2] + " " + nodes[i][3] +" "+nodes[i][4] +" Direction: "+directions.charAt(direction));
            for (String[] currentNode: currentNodes) {
                String nodeLabel = currentNode[0];
                char currDir = directions.charAt(direction);
                if (currDir == 'L') {
                    if (currentNode[3] != null) {
                        j = Integer.parseInt(currentNode[3]);
                    } else {
                        String destination = currentNode[1];
                        for (j = 0; j < nodes.length; j++) {
                            if (nodes[j][0].equals(destination)) {
                                break;
                            }
                        }
                        currentNode[3] = String.valueOf(j);
                    }
                } else if (currDir == 'R') {
                    if (currentNode[4] != null) {
                        j = Integer.parseInt(currentNode[4]);
                    } else {
                        String destination = currentNode[2];
                        for (j = 0; j < nodes.length; j++) {
                            if (nodes[j][0].equals(destination)) {
                                break;
                            }
                        }
                        currentNode[4] = String.valueOf(j);
                    }
                }
                //System.out.print("Destination: "+nodes[j][0]+" ");
                //System.out.println("Went "+directions.charAt(direction)+ " at index " + direction +" with "+steps+" steps");
                nextNodes.add(nodes[j]);
            }
            direction = (direction + 1) % directions.length();
            steps++;
            System.out.println(steps);
            currentNodes.clear();
            currentNodes.addAll(nextNodes);
            nextNodes.clear();
            //System.out.println(steps);
        }
        System.out.println("Number of Steps taken: "+steps);
    }

    private static boolean checkNodesForZ(List<String[]> currentNodes) {
        for (String[] node : currentNodes ) {
            if (node[0].charAt(2) != 'Z'){
                return false;
            }
        }
        return true;
    }
}
