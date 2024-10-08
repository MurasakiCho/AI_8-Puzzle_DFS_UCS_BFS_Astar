import java.util.*;
public class Information {
    //VARIABLES
    public Stack<Node> stack;
    public PriorityQueue<Node> pqueue;
    public HashMap<Integer, Node> visited;
    private int count;


    //CONSTRUCTOR
    public Information(){
        stack = new Stack<>();
        pqueue = new PriorityQueue<>();
        visited = new HashMap<>();
        count = 0;
    }

    //METHODS
    public void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }

    public void createPQueue(Comparator<Node> c){
        pqueue = new PriorityQueue<>(c);
    }
}
