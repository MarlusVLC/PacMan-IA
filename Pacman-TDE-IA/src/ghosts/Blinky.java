package ghosts;

import ghosts.states.BlinkyChase;
import ghosts.states.StateMachine;
import pacman.*;

import java.util.ArrayList;
import java.util.List;

public class Blinky extends GhostPlayer {

    private static int periodLength = 20;
    private static int numPeriodTypes = 1;

    private Location target = null;
    private Move lastMove = null;



    public Blinky(){
        //Instância a máquina de estados
        this.stateMachine = new StateMachine<Blinky>(this);

        //Seta o estado inicial:
        stateMachine.setCurrentState(BlinkyChase.getInstance());

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



//    private Location getTarget(State s, int ghostIndex) {
//        int step = s.getHistory().size()-1;
////    int step = 4;
//        int numGhosts = s.getGhostLocations().size();
//        if (step%periodLength==(ghostIndex*(periodLength/numGhosts))) { // this makes ghosts change behavior at different points in the period
//            // pick a new target
//            int period = step/periodLength;
//            int type = period%numPeriodTypes;
//            if (type==numPeriodTypes-(ghostIndex*2)-1) { // one of the types
//                // pick pacman as the target
//                target = null;
//            } else if (type==numPeriodTypes-(ghostIndex*2)-2) { // another of the types
//                // pick one of the remaining dots as the target
//                List<Location> dotList = new ArrayList<Location>(s.getDotLocations());
//                target = dotList.get((step+ghostIndex)%dotList.size());
//            } else { // the rest of the types
//                // otherwise, pick any location to protect
//                List<Location> dotList = new ArrayList<Location>(Game.getAllLocations());
//                target = dotList.get((step+ghostIndex)%dotList.size());
//            }
////      System.err.println("Ghost " + ghostIndex + " picked new target: " + target);
//        }
//        if (target!=null) return target;
//        return s.getPacManLocation();
//    }


        @Override
    public Move update(Game game, int ghostIndex) {
       return stateMachine.update(game, ghostIndex);
    }


}



