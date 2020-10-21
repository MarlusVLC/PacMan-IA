package ghosts.states;

import ghosts.Blinky;
import pacman.Game;
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
    public Move execute(Blinky blinky, Game game, int ghostIndex) {
        return blinky.chooseMove(game, ghostIndex);
    }

    @Override
    public void exit(Blinky blinky) {

    }
}
