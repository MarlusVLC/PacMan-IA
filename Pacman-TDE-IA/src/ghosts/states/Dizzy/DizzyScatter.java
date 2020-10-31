package ghosts.states.Dizzy;

import ghosts.Communication.Message;
import ghosts.Communication.MessageDispatcher;
import ghosts.Dizzy;
import ghosts.states.Blinky.BlinkyScatter;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.GhostManager;
import pacman.Move;
import pacman.State;

public class DizzyScatter implements GhostState<Dizzy> {

    //SINGLETON begin
    private static DizzyScatter INSTANCE = null;

    private DizzyScatter(){}

    public static DizzyScatter getInstance(){
        if (INSTANCE == null){
            INSTANCE = new DizzyScatter();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Dizzy dizzy) {
        MessageDispatcher.getInstance().dispatchMessage(dizzy, GhostManager.getInstance().getGhost("Wammy"), "Dizzy is Scattering", null);

    }

    @Override
    public Move execute(Dizzy dizzy, Game game, int ghostIndex) {
        System.out.println("dizzy is scattering");
        dizzy.dotCounter(game);
//        System.out.println(dizzy.canChangeToChase(game));
        if (dizzy.canChangeToChase(game)) {
            dizzy.getStateMachine().changeState(DizzyChase.getInstance());
            return dizzy.goBackwards();
        }

        State s = game.getCurrentState();
        return dizzy.chooseMove(game, ghostIndex, dizzy.runFromPacMan(game));
    }

    @Override
    public void exit(Dizzy dizzy) {

    }

    @Override
    public boolean onMessage(Dizzy dizzy, Message msg) {
        return false;
    }
}
