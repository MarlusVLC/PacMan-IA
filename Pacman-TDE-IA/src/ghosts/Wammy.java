package ghosts;

import ghosts.states.StateMachine;
import ghosts.states.Wammy.WammyChase;
import ghosts.states.Wammy.WammyGlobalState;
import pacman.*;

import java.util.List;

public class Wammy extends GhostPlayer {

    private static int transRadius = 3;
//    private static int dotsEaten = 0;
//    private static int dotsLimit = 10;

    private Location target = null;
//    private Move lastMove = null;



    public Wammy(){
        super("Wammy");

        //Instância a máquina de estados
        this.stateMachine = new StateMachine<Wammy>(this);

        //Seta o estado inicial:
        stateMachine.setCurrentState(WammyChase.getInstance());

//        Seta o estado global:
        stateMachine.setGlobalState(WammyGlobalState.getInstance());
    }

    public StateMachine<Wammy> getStateMachine() { return this.stateMachine; }

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
    public boolean canChangeToScatter(Game game, int ghostIndex){
        //Checa a distância entre o PacMan  e  Dizzy. Se a distância for menor que 3, faz a transição
        Location pacManLoc = game.getCurrentState().getPacManLocation();
        List<Location> allGhostLoc = game.getCurrentState().getGhostLocations();
        double dist = pacManLoc.manhattanDistance(pacManLoc, allGhostLoc.get(ghostIndex) );
        if (dist < transRadius) return true;
        return false;
    }

    public boolean canChangeToChase(Game game){
        //Checa se o contador alcançou o limite. Se o tiver, o fantasma pode
        //mudar de estado para Chase.
//        if (dotsEaten >= dotsLimit){
        if ((game.getPoints()%500)==0){
//            dotsEaten = 0;
            return true;
        }
        return false;
    }

    public Location runFromPacMan(Game game){
        //O fantasma sempre vai pro quadrante oposto ao qual o PacMan está localizado.
        Location PacManLoc = game.getCurrentState().getPacManLocation();
        int halfMapX = Math.floorDiv(game.xDim,2);
        int halfMapY = Math.floorDiv(game.yDim, 2);
        if (PacManLoc.getX() <= halfMapX){
            if (PacManLoc.getY() <= halfMapY){
                return new Location(game.xDim-1, game.yDim-1);
            } //else
            return new Location(game.xDim-1, 0);
        }
        else{
            if (PacManLoc.getY() <= halfMapY){
                return new Location(0, game.yDim-1);
            } //else
            return new Location(0, 0);
        }
    }



        @Override
    public Move update(Game game, int ghostIndex) {
       return stateMachine.update(game, ghostIndex);
    }


}



