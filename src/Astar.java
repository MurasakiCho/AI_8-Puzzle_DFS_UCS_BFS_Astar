import java.util.*;
//A*: f(n) = g(n) + h(n);
//f(n) = cost of optimal path from root to goal
//g(n) = shortest path from root to current node
//h(n) = shortest path from current node to goal
public class Astar {
    //VARIABLES
    private final Node rootNode;

    //CONSTRUCTOR
    public Astar(Node rootNode){
        this.rootNode = rootNode;
    }

    //COMPARATOR

    private static class costComparator implements Comparator<Node>{
        Heuristics h = new Heuristics();
        public int compare(Node x, Node y){
            return (x.getMaxCost() + h.manhattan(x) - (y.getMaxCost() + h.manhattan(y)));
        }
    }

    public void search(){
        Information info = new Information();
        Node node = rootNode;
        info.createPQueue(new costComparator());
        info.pqueue.add(node);
        info.increment();

        while(!(info.pqueue.isEmpty())){
            node = info.pqueue.poll();
            info.visited.put(node.hashcode(), node);

            if(node.isGoal()){
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
