package player_2020_1_Equipe01;

import pacman.*;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Game game;
    private Move move;
    private State state;
    private Branch branch;
    private Node parent = null;
    private int level;
    private boolean visited = false;


    public Node(Game game, Move move, State state){
        this.game = game;
        this.move = move;
        this.state = state;
    }

    public Node(Game game, Move move, State state, int level){
        this.game = game;
        this.move = move;
        this.state = state;
        this.level = level;
    }


    public void enterBranch(Branch branch){
        branch.addNewNode(this);
    }

    public double evaluateState(StateEvaluator evaluator){
        return evaluator.evaluateState(state);
    }

    public List<Node> getChildren(){
        List<Node> children = new ArrayList<>();
        for (Move m : game.getLegalPacManMoves(state)){
            //TODO:  CORRIGIR ESSA LINHA
//            State nextState = game.getNextState(state, m, getGhostMoves());
            if (game.isFinal(state))
                break;
            State nextState = game.getNextState(state, m);
            Node child = new Node(game, m,  nextState, this.level+1 );
            child.setParent(this);
            if (this.hasParent() && child.getPacManLocation().equals(this.getParent().getPacManLocation()))
                continue;
            children.add(child);
        }
        return children;
    }

    public List<Move> getGhostMoves(){
        List<GhostPlayer> ghostPlayers = game.getGhostPlayers();
        List<Move> ghostMoves = new ArrayList<Move>();
        for (int i = 0; i < ghostPlayers.size(); i++) {
            GhostPlayer player = ghostPlayers.get(i);
//            Move move = player.chooseMove(game, i);
            ghostMoves.add(move);
        }
        return ghostMoves;
    }


    public boolean hasBeenVisited(){
        return visited;
    }



    public State getState(){
        return this.state;
    }

    public Move getMove(){
        return this.move;
    }

    public Location getPacManLocation(){
        return this.state.getPacManLocation();
    }

    public void setLevel(int Level){
        this.level = level;
    }

    public int getLevel (){
        return level;
    }

    public Branch getBranch(){
        return branch;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean hasParent(){
        return parent != null;
    }


}
