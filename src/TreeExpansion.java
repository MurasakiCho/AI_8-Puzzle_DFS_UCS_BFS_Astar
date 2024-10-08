import java.util.ArrayList;
import java.util.List;

public class TreeExpansion {
    //EMPTY CONSTRUCTOR
    public TreeExpansion(){}

    //METHODS
    //takes in a state and returns list of possible states from it
    public List<Node> expand(Node node){
        List<Node> children = new ArrayList<>();
        int row = node.getBlankRow();
        int col = node.getBlankCol();

        //Move blank space up
        if(row != 0){
            Node upNode = node.createChild(row - 1, col);
            upNode.setDirection(Node.Direction.UP);
            children.add(upNode);
        }
        //move blank space down
        if(row != 2){
            Node downNode = node.createChild(row + 1, col);
            downNode.setDirection(Node.Direction.DOWN);
            children.add(downNode);
        }
        //move blank left
        if(col != 0){
            Node leftNode = node.createChild(row, col - 1);
            leftNode.setDirection(Node.Direction.LEFT);
            children.add(leftNode);
        }
        //move blank right
        if(col != 2){
            Node rightNode = node.createChild(row, col + 1);
            rightNode.setDirection(Node.Direction.RIGHT);
            children.add(rightNode);
        }

        return children;
    }
}
