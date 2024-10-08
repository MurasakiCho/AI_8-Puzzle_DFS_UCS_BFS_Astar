import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Path {
    //VARIABLES
    List<Node> path;
    int count;

    //CONSTRUCTOR
    public Path(Node rootNode, Node goalNode, int count){
        path = this.getPath(rootNode, goalNode);
        this.count = count;
    }

    //METHODS
    //creates path from goal node to root node (reverse order)
    public List<Node> getPath(Node rootNode, Node goalNode){
        Node temp = goalNode;
        List<Node> list = new ArrayList<>();

        while(!(temp.equals(rootNode))){
            list.add(temp);
            temp = temp.getParent();
        }
        list.add(rootNode);
        return list;
    }

    //prints the path from root node to goal node
    public void printPath(){
        for(int i = path.size() -1; i>=0; i--){
            System.out.println("Move: " + path.get(i).getDirection());
            System.out.println(Arrays.deepToString(path.get(i).getState()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            System.out.println();
            System.out.println();
        }
        System.out.println(count + " nodes visited.");
    }


}
