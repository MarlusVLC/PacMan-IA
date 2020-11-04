package ghosts;

import ghosts.states.Blinky.BlinkyScatter;
import ghosts.states.StateMachine;
import pacman.*;

import java.util.List;

public class Blinky extends GhostPlayer {

    private static int periodLength = 20;
    private static int protelator = 5; //Variável que determina um atraso na tranaição de estados
                                        //Quanto menor, maior o tempo de atraso

    private Location target = null;


    public Blinky(){
        super("Blinky");

        //Instância a máquina de estados
        this.stateMachine = new StateMachine<Blinky>(this);

        //Seta o estado inicial:
        stateMachine.setCurrentState(BlinkyScatter.getInstance());

        //Seta o estado global:
//        stateMachine.setGlobalState();
    }

    public StateMachine<Blinky> getStateMachine() { return this.stateMachine; }

    @Override
    public Move chooseMove(Game game, int ghostIndex, Location target) {
                    State s = game.getCurrentState();
//        Location target = s.getPacManLocation();
                    Location oldLoc = s.getGhostLocations().get(ghostIndex);
                    List<Move> legalMoves = game.getLegalGhostMoves(ghostIndex);
                    Move bestMove = null;
                    double minDistance = Double.POSITIVE_INFINITY;
                    for (Move m : legalMoves) {
                        Location newLoc = Game.getNextLocation(oldLoc, m);
                        double distance = Location.euclideanDistance(newLoc, target);
                        if (distance<minDistance) {
                            minDistance = distance;
                            bestMove = m;
            }
        }
        if (bestMove==null) throw new RuntimeException("Legal moves for ghost " +ghostIndex+": " + legalMoves);
        lastMove = bestMove;
        return bestMove;

        }



    //Define a condição de transição do estado Chase para Scatter  vice-versa
    public boolean canChangeToChase(Game game){
        //Checa se um limite tempo espcífico após a última transição foi atingido.
        //O tempo de transicao aumenta conforme o tempo passa
        //periodLength = Tempo limite pra transição
        return  (game.getTime()%periodLength+Math.floorDiv(game.getTime(),protelator) == 0);
    }

    public boolean canChangeToScatter(Game game){
        return (game.getTime()%periodLength-Math.floorDiv(game.getTime(), protelator) == 0);
    }

        @Override
    public Move update(Game game, int ghostIndex) {
       return stateMachine.update(game, ghostIndex);
    }


}



