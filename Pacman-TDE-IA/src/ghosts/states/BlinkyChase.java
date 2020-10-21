package ghosts.states;

import ghosts.Blinky;
import pacman.Game;
import pacman.Move;
import pacman.State;

public class BlinkyChase implements GhostState<Blinky> {

    //SINGLETON begin
    private static BlinkyChase INSTANCE = null;

    private BlinkyChase(){}

    public static BlinkyChase getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BlinkyChase();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Blinky blinky) {

    }

    @Override
    public Move execute(Blinky blinky, Game game, int ghostIndex) {
        //Transição de estados baseada na quantidade de ponto
        // coletados
        State s = game.getCurrentState();

        return blinky.chooseMove(game, ghostIndex, s.getPacManLocation());
    }

    @Override
    public void exit(Blinky blinky) {

    }
}
