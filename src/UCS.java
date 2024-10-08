import java.util.*;
//Uniform-Cost Search: uses lowest cumulative cost to find path from root to goal
public class UCS {
    //VARIABLES
    private final Node rootNode;

    //CONSTRUCTOR
    public UCS(Node rootNode){
        this.rootNode = rootNode;
    }

    //INNER CLASS
    private static class costComparator implements Comparator<Node>{
        public int compare (Node x, Node y){
            return x.getMaxCost() - y.getMaxCost();
        }
    }

    //METHODS
    public void search(){
        Information info = new Information();
        Node node = rootNode;
        info.createPQueue(new costComparator());
        info.pqueue.add(node);

        while(!(info.pqueue.isEmpty())){
            node = info.pqueue.poll();
            info.visited.put(node.hashcode(), node);
            info.increment();

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
