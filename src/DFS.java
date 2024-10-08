import java.util.*;

//Depth-First Search: Uninformed search that looks through each possible path for the goal, then returns the path
public class DFS {
    //VARIABLES
    private Node rootNode;

    //CONSTRUCTOR
    public DFS (Node rootNode){
        this.rootNode = rootNode;
    }

    //METHODS
    //searches for the goal node by the root node into all possible actions; uses stack for LIFO
    public void search(){
        Information info = new Information();
        Node node = rootNode;
        info.stack.push(node);

        while(!(info.stack.isEmpty())){
            node = info.stack.pop();
            info.visited.put(node.hashcode(), node);
            info.increment();
            if(node.isGoal()){
                Path path = new Path(rootNode, node, info.getCount());
                path.printPath();
                break;
            }

            TreeExpansion t = new TreeExpansion();
            List<Node> children = t.expand(node);
            for (Node c: children) {
                boolean isVisited = info.visited.containsKey(c.hashcode());
                if(!isVisited && !info.stack.contains(c)){
                    info.stack.push(c);
                }
            }

        }
    }
}
