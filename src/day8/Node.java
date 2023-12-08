package day8;


public class Node {
    private static final Exception FalseTypeException = null;
    private String thisNode;
    private Node leftNode;
    private Node rightNode;
    
    public Node(String thisNode) {
        this.thisNode = thisNode;
    }

    public Node(String thisNode, Node leftNode, Node rightNode) {
        this.thisNode = thisNode;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public String getThisNode() {
        return thisNode;
    }
    public Node getLeftNode() {
        return leftNode;
    }
    public Node getRightNode() {
        return rightNode;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node otherNode)){
            try {
                throw FalseTypeException;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            if(otherNode.getThisNode().equals(otherNode.getThisNode())){
                return true;
            }
        }
        return false;
    }
}
