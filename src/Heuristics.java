//Heuristic definitions for Best-First and A* Search Algorithms
public class Heuristics {
    public Heuristics(){}

    //tells us the number of misplaced tiles
    public int misplacedTiles (Node node){
        int count = 0;
        int [][] state = node.getState();
        for(int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (node.goalNode[i][j] != state[i][j]) {
                    count += 1;
                }
            }
        }
        return count;
    }

    //tells us how far node tiles are from their goal positions
    public int manhattan(Node node){
        int distance = 0;
        int [][] state = node.getState();
        for(int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                int value = state[i][j];
                distance += Math.abs(i - node.getRow(value)) + Math.abs(j - node.getCol(value));
            }
        }
        return distance;
    }

}
