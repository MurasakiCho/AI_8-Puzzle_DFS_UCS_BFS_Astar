import java.util.*;
public class Node {
    //VARIABLES
    private final int [][] state; //holds the array of the state
    private final List<Node> children; //holds a list of a state's children
    private Node parent; //points to the state's parent
    private int depth; //describes the depth of the state
    private int blankRow; //row of the blank square
    private int blankCol; //column of the blank square
    private Direction direction; //direction moved to get to this state

    private final String stringState; //holds the string version of the state; used for printing

    private int cost; //cost of the state
    private int maxCost; //maximum cost

    final int [][] goalNode;

    //CONSTRUCTOR
    public Node(int[][] state){
        this.state = state;
        this.children = new ArrayList<>();
        this.parent = null;
        this.depth = 0;
        //finds the blank space in the state
        for(int i = 0; i < state.length; i++)
            for(int j = 0; j < state.length; j++)
                if(state[i][j] == 0) {
                    this.blankRow = i;
                    this.blankCol = j;
                    break;
                }
        this.direction = null;
        this.stringState = stringState();
        this.cost = 0;
        this.maxCost = 0;
        goalNode = new int[][] {{1,2,3}, {8,0,4}, {7,6,5}};
    }

    //SETS/GETS
    public int[][] getState(){
        return state;
    }

    public int getBlankRow(){
        return blankRow;
    }

    public int getBlankCol(){
        return blankCol;
    }

    public String getStringState(){
        return stringState;
    }

    public Node getParent(){
        return parent;
    }
    public void setParent(Node parent){
        this.parent = parent;
    }

    public int getDepth(){
        return depth;
    }
    public void setDepth(int depth){
        this.depth = depth;
    }

    public Direction getDirection(){
        return direction;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMaxCost() {
        return maxCost;
    }
    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    //METHODS

    //return string version of node
    public String stringState(){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                string.append(state[i][j]);
            }
        }
        return string.toString();
    }

    //creates a child based off a node's possible states
    public Node createChild(int valueRow, int valueCol){
        int [][] tempState = new int [state.length][state.length];
        for(int i = 0; i < state.length; i++) {
            System.arraycopy(state[i], 0, tempState[i], 0, state.length);
        }
        tempState[blankRow][blankCol] = state[valueRow][valueCol];
        int cost = state[valueRow][valueCol];
        tempState[valueRow][valueCol] = 0;
        Node child = new Node(tempState);
        child.setCost(cost);
        addChild(child);
        return child;
    }

    //adds a child to the children list
    public void addChild(Node child){
        child.setParent(this);
        child.setDepth(this.getDepth() + 1);
        child.setMaxCost(child.getCost());
        this.children.add(child);
    }

    //checks a node to see if it matches the goal state
    public boolean isGoal(){
        boolean isGoal = true;
        for(int i =0; i<3; i++){
            if(!Arrays.equals(goalNode[i], this.state[i])){
                isGoal = false;
                break;
            }
        }
        return isGoal;
    }

    //get Row of value in goal state
    public int getRow(int value){
        int row = 0;
        for(int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (goalNode[i][j] == value) {
                    row = i;
                    break;
                }
            }
        }
        return row;
    }

    //get Col of value in goal state
    public int getCol(int value){
        int col = 0;
        for(int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (goalNode[i][j] == value) {
                    col = i;
                    break;
                }
            }
        }
        return col;
    }

    public int hashcode(){
        int hash = 7;
        hash = 31 * hash + this.getStringState().hashCode();
        return hash;
    }


    //ENUMERATION
    //list of possible actions
    public enum Direction{
        LEFT, RIGHT, UP, DOWN
    }



}
