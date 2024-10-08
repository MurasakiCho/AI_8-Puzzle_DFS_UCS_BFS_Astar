import java.util.*;

//Best-First Search: informed algorithm that uses the distance from the goal as its comparator
// chooses path to goal with the least amount of nodes
public class BFS {
    //VARIABLES
    private final Node rootNode;
    //CONSTRUCTOR
    public BFS (Node rootNode){
        this.rootNode = rootNode;
    }

    //COMPARATOR CLASS
    private static class costComparator implements Comparator<Node>{
        Heuristics misplaced = new Heuristics();
        public int compare(Node x, Node y){
            return misplaced.misplacedTiles(x) - misplaced.misplacedTiles(y);
        }
    }

    public void search(){
        Information info = new Information();
        info.createPQueue(new costComparator());
        Node node = rootNode;
        info.pqueue.add(node);

        while(!(info.pqueue.isEmpty())){
            node = info.pqueue.poll();
            info.visited.put(node.hashcode(), node);
            info.increment();

            if (node.isGoal()) {
                Path path = new Path(rootNode, node, info.getCount());
                path.printPath();
                return;
            }

            TreeExpansion t = new TreeExpansion();
            List<Node> children = t.expand(node);

            for(Node c: children){
                boolean isVisited = info.visited.containsKey(c.hashcode());
                if(!isVisited && !info.pqueue.contains(c)){
                    info.pqueue.add(c);
                }
            }
        }
    }

}
