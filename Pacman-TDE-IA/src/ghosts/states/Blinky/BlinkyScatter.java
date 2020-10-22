package ghosts.states.Blinky;

import ghosts.Blinky;
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
       if (blinky.canChangeState(game)) {
           blinky.getStateMachine().changeState(BlinkyChase.getInstance());
       }
        return blinky.chooseMove(game, ghostIndex, new Location(0,0));
    }

    @Override
    public void exit(Blinky blinky) {

    }
}
