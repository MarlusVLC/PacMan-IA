package ghosts.states.Wammy;

import ghosts.Wammy;
import ghosts.Communication.Message;
import ghosts.states.GhostState;
import pacman.Game;
import pacman.Move;
import pacman.State;

public class WammyChase implements GhostState<Wammy> {

    //SINGLETON begin
    private static WammyChase INSTANCE = null;

    private WammyChase(){}

    public static WammyChase getInstance(){
        if (INSTANCE == null){
            INSTANCE = new WammyChase();
        }
        return INSTANCE;
    }
    //SINGLETON end


    @Override
    public void enter(Wammy wammy) {

    }

    @Override
    public Move execute(Wammy wammy, Game game, int ghostIndex) {
        System.out.println("wammy is chasing");
        //Executa o mesmo padrão de perseguição do Blinky.
        //Setando o PacMan como alvo
        State s = game.getCurrentState();
        return wammy.chooseMove(game, ghostIndex, s.getPacManLocation());
    }

    @Override
    public void exit(Wammy wammy) {

    }

    @Override
    public boolean onMessage(Wammy wammy, Message msg) {
        return false;
    }
}
