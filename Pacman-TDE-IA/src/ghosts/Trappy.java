package ghosts;

import ghosts.states.Blinky.BlinkyScatter;
import ghosts.states.StateMachine;
import ghosts.states.Trappy.TrappyChase;
import pacman.*;

import java.util.List;

public class Trappy extends GhostPlayer {

    private static int periodLength = 20;
//    private static int numPeriodTypes = 1;
    private static int projectedTile = 4; //Quantidade de blocos à frente do PacMan a ser coletado como alvo

    private Location target = null;
    private Move lastMove = null;



    public Trappy(){
        super("Trappy");

        //Instância a máquina de estados
        this.stateMachine = new StateMachine<Trappy>(this);

        //Seta o estado inicial:
        stateMachine.setCurrentState(TrappyChase.getInstance());
    }

    public StateMachine<Trappy> getStateMachine() { return this.stateMachine; }

    @Override
    public Move chooseMove(Game game, int ghostIndex, Location target) {
//        System.out.println("<Trappy>: Alvo atual" + target);
//        System.out.println("<Trappy>: Último movimento do PacMan " + game.getLastPacManMove());
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


    //Retorna uma posição quatro blocos à frente do PacMan, relativo à posição à qual o PacMan está virado.
    public Location getProjectedTarget(State s, Game game) {
//        System.out.println("<Trappy> ULTIMO MOVIMENTO DO PAC-MAN: " + game.getLastPacManMove());

        if (game.getLastPacManMove() != null && game.getLastPacManMove() != Move.NONE) {


            List<State> onwardPacManMoveSet = game.getProjectedStates(s, game.getLastPacManMove());
            Location pacManProjectedLoc = null;


            if (onwardPacManMoveSet == null){
                return s.getPacManLocation();
            }
            else if (onwardPacManMoveSet.size() >= projectedTile) {
                pacManProjectedLoc = onwardPacManMoveSet.get(projectedTile-1).getPacManLocation();
            } else {
                pacManProjectedLoc = onwardPacManMoveSet.get(onwardPacManMoveSet.size() - 1).getPacManLocation();
            }
            return pacManProjectedLoc;
        }
        return new Location(game.xDim-1, game.yDim-1);
    }

    //Trafega pelos cantos do mapa na seguinte ordem:
    //Superior direito, inferior esquerdo, inferior direito, superior esquerdo
    public Location runFromPacMan(){
        return new Location(0,0);
    }


    //Define a condição de transição do estado Chase para Scatter
    public boolean canChangeToScatter(Game game){
        //Checa se um limite tempo espcífico, que muda conforme os pontos coletados pelo PacMan e o nível de jogo
        //periodLength = Tempo limite pra transição
        return  (game.getTime()%((periodLength+game.getPoints()/10)*game.getLevel()*0.5) == 0);
    }


    //Define a condição de transição do estado Scatter para Chase
    public boolean canChangeToChase(Game game){
        //Checa se um limite tempo espcífico, que muda conforme somente os pontos coletados pelo PacMan
        //periodLength = Tempo limite pra transição
        return  (game.getTime()%(periodLength-game.getPoints()/10) == 0);
    }





        @Override
    public Move update(Game game, int ghostIndex) {
       return stateMachine.update(game, ghostIndex);
    }


}



