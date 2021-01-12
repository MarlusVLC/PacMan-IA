package player_2020_1_Equipe01;

import pacman.*;

import java.util.*;

/**
 * Use this class for your basic DFS player implementation.
 * @author grenager
 *
 */
public class BFSPacManPlayer implements PacManPlayer, StateEvaluator {

    /**
     * Chooses a Move for Pac-Man in the Game.
     *
     * @param game
     * @return a Move for Pac-Man
     */

    Move lastMove = Move.NONE;
    Node lastChosenNode = null;

    int depth = 9;



    public Move chooseMove(Game game) {
       double bestScore = Double.NEGATIVE_INFINITY;
       Node bestNode = null;

        Node origin = new Node(game, Move.NONE, game.getCurrentState(), 0);
        List<List<Node>> allNodes = new ArrayList<>(depth+1);
        while (allNodes.size() < depth+1){
            allNodes.add(new ArrayList<>());
        }

        allNodes.get(0).add(origin);
        System.out.println("OriginPos" + origin.getPacManLocation());
        for (int i = 0; i < depth; i++){
            for (Node node : allNodes.get(i)){
                for (Node child : node.getChildren()) {
                    allNodes.get(child.getLevel()).add(child);

                    System.out.print(child.getLevel());
                    System.out.print(child.getMove());
                    System.out.print(child.getParent().getMove());
                    System.out.println(child.getPacManLocation());
                    }

                //EVITA QUE O PACMAN EXECUTE LOOPING
                if (lastMove != Move.NONE && i == 0) {
                    int backwardNode_index = 0;
                    for (Node node1 : allNodes.get(1)) {
                        if (node1.getMove().equals(lastMove.getOpposite()) )
                            backwardNode_index = allNodes.get(1).indexOf(node1);
                    }
                    allNodes.get(1).remove(backwardNode_index);
                }
                //EVITA QUE O PACMAN EXECUTE LOOPING
            }
        }



        int deepestLevel = allNodes.size()-1;
        while (allNodes.get(deepestLevel).isEmpty()){
            allNodes.remove(deepestLevel);
            deepestLevel = allNodes.size()-1;
        }
        for (Node node : allNodes.get(deepestLevel)){
            double currScore = node.evaluateState(this);
            System.out.println(currScore);
            if (currScore >= bestScore){
                bestScore = currScore;
                bestNode = node;
            }
        }

        Node chosenNode = bestNode;

        while (chosenNode.getParent().hasParent()){
            chosenNode = chosenNode.getParent();
        }


        lastMove = chosenNode.getMove();

        System.out.println(" ");

        return chosenNode.getMove();
    }







    /**
     * Computes an estimate of the value of the State.
     * @param s the State to evaluate.
     * @return an estimate of the value of the State.
     */
    public double evaluateState(State state) {
        Location pacLOC = state.getPacManLocation();
        List<Location> allGhostLoc = state.getGhostLocations();
        LocationSet allDotLoc = state.getDotLocations();
        State lastState = state.getParent();


        double heuristic = 1;

        //TESTE DE FUNCAO OBJETIVO
        if (Game.isLosing(state))
            heuristic += Double.NEGATIVE_INFINITY;
        else if (Game.isWinning(state))
            heuristic +=  Double.POSITIVE_INFINITY;
        //TESTE DE FUNCAO OBJETIVO



//
//    //HEURÍSTICA 1: DISTÂNCIA ENTRE PACMAN E O FANTASMA MAIS PRÓXIMO
//        heuristic += pacLOC.manhattanDistanceToClosest(pacLOC, allGhostLoc);



        //HEURÍSTICA 2: PONTOS EXISTENTES NO MAPA
        heuristic -= allDotLoc.size();


        //HEURÍSTICA 3: DISTÂNCIA ENTRE O PACMAN E O PONTO MAIS PRÓXIMO
//        heuristic -= pacLOC.manhattanDistanceToClosest(pacLOC, allDotLoc);

        //HEURÍSTICA 4: DIREÇÃO DOS FANTASMAS todo: finalizar com as coordernadas de todos os fantasmas
//        if (state.getParent() != null) {
//            double lastDist = lastState.getPacManLocation().manhattanDistance(lastState.getPacManLocation(), lastState.getGhostLocations().get(0));
//            double dist = pacLOC.manhattanDistance(pacLOC, allGhostLoc.get(0));
//            if (dist >= lastDist)
//                    heuristic += 25;
//            else
//                heuristic -= 25;
//        }





        return heuristic;

    }

//    private int breadth_first_search(Game game, State state, int depth) {
//        State s = game.getCurrentState();
//
//        HashMap<State, Move> nextNodes = new HashMap<>();
//
//        for (Move m : game.getLegalPacManMoves()) {
//            nextNodes.put(game.getNextState(game.getCurrentState(), m), m);
//        }
//    }




}
