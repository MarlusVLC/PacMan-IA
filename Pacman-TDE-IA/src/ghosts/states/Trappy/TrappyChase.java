package ghosts.states.Trappy;

import ghosts.Communication.Message;
import ghosts.Trappy;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.Move;
import pacman.State;

public class TrappyChase implements GhostState<Trappy> {

    //SINGLETON begin
    private static TrappyChase INSTANCE = null;

    private TrappyChase(){}

    public static TrappyChase getInstance(){
        if (INSTANCE == null){
            INSTANCE = new TrappyChase();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Trappy trappy) {

    }

    @Override
    public Move execute(Trappy trappy, Game game, int ghostIndex) {
//        System.out.println("Trappy's chasin");

        if (trappy.canReturn()) { return trappy.goBackwards(); }


        if (trappy.canChangeToScatter(game)) {
            trappy.getStateMachine().changeState(TrappyScatter.getInstance());
        }

        State s = game.getCurrentState();
        return trappy.chooseMove(game, ghostIndex, trappy.getProjectedTarget(game.getCurrentState(), game));
    }

    @Override
    public void exit(Trappy trappy) {

    }

    @Override
    public boolean onMessage(Trappy trappy, Message msg) {
        return false;
    }
}
