package ghosts.states.Blinky;

import ghosts.Blinky;
import ghosts.Communication.Message;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.Location;
import pacman.Move;

public class BlinkyScatter implements GhostState<Blinky> {

    //SINGLETON begin
    private static BlinkyScatter INSTANCE = null;

    private BlinkyScatter(){}

    public static BlinkyScatter getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BlinkyScatter();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Blinky blinky) {

    }

    @Override
    //Referencia ao personagem, à classe da instância atual de jogo e a posição
    // do fantasma para acessar o array de fantasmas dentro da classe game
    public Move execute(Blinky blinky, Game game, int ghostIndex) {

        if (blinky.canReturn()) { return blinky.goBackwards(); }

        if (blinky.canChangeToChase(game)) {
           blinky.getStateMachine().changeState(BlinkyChase.getInstance());
       }
       //Se não estiver na hora. Blinky objetiva ir até o canto inferior esquerdo do mapa.
        return blinky.chooseMove(game, ghostIndex, new Location(game.xDim-1,0));
    }

    @Override
    public void exit(Blinky blinky) {

    }

    @Override
    public boolean onMessage(Blinky blinky, Message msg) {
        return false;
    }
}
