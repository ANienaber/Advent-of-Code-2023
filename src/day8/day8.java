package day8;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import utils.utils;
import day8.Node;

public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = utils.readFile("src\\day8\\test.txt");
        String direction = lines.get(0);
        List<Node> nodes = new ArrayList<>();
        lines.remove(0);
        lines.remove(1);

        for (String line : lines) {
            String thisNode = line.split(" = ")[0];
            String otherNodes = line.split(" = ")[1].split("(")[1].split(")")[0];
            String leftNode = otherNodes.split(", ")[0];
            String rightNode = otherNodes.split(", ")[1];

            if (nodes.contains(new Node(thisNode))){

            }
        }
    }
}
