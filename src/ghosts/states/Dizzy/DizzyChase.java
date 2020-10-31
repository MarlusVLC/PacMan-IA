package ghosts.states.Dizzy;

import ghosts.Dizzy;
import ghosts.states.Blinky.BlinkyScatter;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.Move;
import pacman.State;

public class DizzyChase implements GhostState<Dizzy> {

    //SINGLETON begin
    private static DizzyChase INSTANCE = null;

    private DizzyChase(){}

    public static DizzyChase getInstance(){
        if (INSTANCE == null){
            INSTANCE = new DizzyChase();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Dizzy dizzy) {

    }

    @Override
    public Move execute(Dizzy dizzy, Game game, int ghostIndex) {
        if (dizzy.canChangeToScatter(game, ghostIndex)) {
            dizzy.getStateMachine().changeState(DizzyScatter.getInstance());
            return dizzy.goBackwards();
        }

        State s = game.getCurrentState();
        return dizzy.chooseMove(game, ghostIndex, s.getPacManLocation());
    }

    @Override
    public void exit(Dizzy dizzy) {

    }
}
