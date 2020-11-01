package ghosts.states.Trappy;

import ghosts.Communication.Message;
import ghosts.Trappy;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.Move;
import pacman.State;

public class TrappyScatter implements GhostState<Trappy> {

    //SINGLETON begin
    private static TrappyScatter INSTANCE = null;

    private TrappyScatter(){}

    public static TrappyScatter getInstance(){
        if (INSTANCE == null){
            INSTANCE = new TrappyScatter();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Trappy trappy) {

    }

    @Override
    public Move execute(Trappy trappy, Game game, int ghostIndex) {
//        System.out.println("Trappy's runnin");


        if (trappy.canReturn()) { return trappy.goBackwards(); }


        if (trappy.canChangeToChase(game)) {
            trappy.getStateMachine().changeState(TrappyChase.getInstance());
        }

        State s = game.getCurrentState();
        return trappy.chooseMove(game, ghostIndex, trappy.runFromPacMan());
    }

    @Override
    public void exit(Trappy trappy) {

    }

    @Override
    public boolean onMessage(Trappy trappy, Message msg) {
        return false;
    }
}
