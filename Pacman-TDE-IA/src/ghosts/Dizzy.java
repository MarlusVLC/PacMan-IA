package ghosts;

import ghosts.states.Blinky.BlinkyScatter;
import ghosts.states.Dizzy.DizzyChase;
import ghosts.states.Dizzy.DizzyScatter;
import ghosts.states.StateMachine;
import pacman.*;

import java.util.List;

public class Dizzy extends GhostPlayer {

    private static int transRadius = 3;
//    private static int dotsEaten = 0;
//    private static int dotsLimit = 10;
    private static int pointsLimit = 400;

    private Location target = null;
//    private Move lastMove = null;



    public Dizzy(){
        super("Dizzy");


        //Instância a máquina de estados
        this.stateMachine = new StateMachine<Dizzy>(this);

        //Seta o estado inicial:
        stateMachine.setCurrentState(DizzyChase.getInstance());

        //Seta o estado global:
//        stateMachine.setGlobalState();
    }

    public StateMachine<Dizzy> getStateMachine() { return this.stateMachine; }

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
        //Checa a distância entre o PacMan  e  Dizzy. Se a distância for menor que 4, faz a transição
        Location pacManLoc = game.getCurrentState().getPacManLocation();
        List<Location> allGhostLoc = game.getCurrentState().getGhostLocations();
        double dist = pacManLoc.manhattanDistance(pacManLoc, allGhostLoc.get(ghostIndex) );
        if (dist < transRadius) return true;
        return false;
    }

//    public void dotCounter(Game game) {
//        //Confirma se o estado atual é Chase. Se for, começa a contar
//        if (stateMachine.getCurrentState() == DizzyScatter.getInstance()) {
//            //Confere se a quantidade de pontos coletados é igual ao limite.
//            //Se for, adiciona um ponto ao contador.
//            State s = game.getCurrentState();
//            System.out.println(s.getDotLocations().contains(s.getPacManLocation()));
//            System.out.println(s.getDotLocations());
//            System.out.println(s.getPacManLocation());
//            if(game.eatDot(s.getDotLocations(), s.getPacManLocation())){
//            if (s.getDotLocations().contains(s.getPacManLocation())){
//                dotsEaten++;
//                System.out.println(dotsEaten);
//            }
//        }
//    }

    public boolean canChangeToChase(Game game){
        //Checa se o contador alcançou o limite. Se o tiver, o fantasma pode
        //mudar de estado para Chase.
//        if (dotsEaten >= dotsLimit){
        if ((game.getPoints()%pointsLimit)==0){
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



