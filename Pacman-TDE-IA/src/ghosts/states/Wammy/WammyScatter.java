package ghosts.states.Wammy;

import ghosts.Communication.Message;
import ghosts.Wammy;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.Move;
import pacman.State;

public class WammyScatter implements GhostState<Wammy> {

    //SINGLETON begin
    private static WammyScatter INSTANCE = null;

    private WammyScatter(){}

    public static WammyScatter getInstance(){
        if (INSTANCE == null){
            INSTANCE = new WammyScatter();
        }
        return INSTANCE;
    }
    //SINGLETON end


    boolean hasReturned = false; //Variável que diz se o o fantasma fez o movimento de retorno


    @Override
    public void enter(Wammy wammy) {
        hasReturned = false;
    }

    @Override
    public Move execute(Wammy wammy, Game game, int ghostIndex) {

        if (wammy.canReturn()) { return wammy.goBackwards(); }

        System.out.println("wammy is scattering");
        State s = game.getCurrentState();
        return wammy.chooseMove(game, ghostIndex, wammy.runFromPacMan(game));
    }

    @Override
    public void exit(Wammy wammy) {

    }

    @Override
    public boolean onMessage(Wammy wammy, Message msg) {
        return false;
    }
}
