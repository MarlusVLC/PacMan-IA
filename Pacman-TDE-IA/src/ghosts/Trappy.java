//package ghosts;
//
//import ghosts.states.Blinky.BlinkyScatter;
//import ghosts.states.StateMachine;
//import pacman.*;
//
//import java.util.List;
//
//public class Trappy extends GhostPlayer {
//
//    private static int periodLength = 20;
//    private static int numPeriodTypes = 1;
//
//    private Location target = null;
//    private Move lastMove = null;
//
//
//
//    public Trappy(){
//        //Instância a máquina de estados
//        this.stateMachine = new StateMachine<Trappy>(this);
//
//        //Seta o estado inicial:
//        stateMachine.setCurrentState(BlinkyScatter.getInstance());
//
//        //Seta o estado global:
////        stateMachine.setGlobalState();
//    }
//
//    public StateMachine<Trappy> getStateMachine() { return this.stateMachine; }
//
//    @Override
//    public Move chooseMove(Game game, int ghostIndex, Location target) {
//        State s = game.getCurrentState();
////        Location target = s.getPacManLocation();
//        Location oldLoc = s.getGhostLocations().get(ghostIndex);
//        List<Move> legalMoves = game.getLegalGhostMoves(ghostIndex);
//        Move bestMove = null;
//        double minDistance = Double.POSITIVE_INFINITY;
//        for (Move m : legalMoves) {
//            Location newLoc = Game.getNextLocation(oldLoc, m);
//            double distance = Location.euclideanDistance(newLoc, target);
//            if (distance<minDistance) {
//                minDistance = distance;
//                bestMove = m;
//            }
//        }
//        if (bestMove==null) throw new RuntimeException("Legal moves for ghost " +ghostIndex+": " + legalMoves);
//        lastMove = bestMove;
//        return bestMove;
//
//        }
//
//
//    //Retorna uma posição quatro blocos à frente do PacMan, relativo à posição à qual o PacMan está virado.
//    private Location getTarget(State s, int ghostIndex, Game game) {
//        List<Move> pacManLegalMoves = game.getLegalPacManMoves();
//        Move latMove = pacManLegalMoves;
//        onwardPacManMoveSet = game.getProjectedStates(s, )
//    }
//
//    //Define a condição de transição do estado Chase para Scatter  vice-versa
//    public boolean canChangeState(Game game){
//        //Checa se um limite tempo espcífico, imutável, após a última transição foi atingido.
//        //periodLength = Tempo limite pra transição
//        return  (game.getTime()%periodLength == 0);
//    }
//
//        @Override
//    public Move update(Game game, int ghostIndex) {
//       return stateMachine.update(game, ghostIndex);
//    }
//
//
//}
//
//
//