package ghosts.states.Wammy;

import ghosts.Communication.Message;
import ghosts.states.GhostState;
import ghosts.Wammy;
import ghosts.states.StateMachine;
import ghosts.states.Wammy.*;
import pacman.Game;
import pacman.Move;

public class WammyGlobalState implements GhostState<Wammy> {
    //begin SINGLETON
    private static WammyGlobalState INSTANCE = null;

    private WammyGlobalState(){};

    public static WammyGlobalState getInstance(){
        if (INSTANCE == null){
            INSTANCE = new WammyGlobalState();
        }
        return INSTANCE;
    }
    //SINGLETON end

    @Override
    public void enter(Wammy billy){

    }



    @Override
    public Move execute(Wammy wammy, Game game, int GhostIndex){
        return null;
    }




    @Override
    public void exit(Wammy wammy){
    }



    @Override
    public boolean onMessage(Wammy wammy, Message msg){
        //Verifica se sabe tratar a mensagem:
        if (msg.getMessage().compareToIgnoreCase("Dizzy is Chasing")==0){
            wammy.getStateMachine().changeState(WammyScatter.getInstance());
            return true;
        } else if (msg.getMessage().compareToIgnoreCase("Dizzy is Scattering")==0) {
            wammy.getStateMachine().changeState(WammyChase.getInstance());
            return true;
        }
        return false;
    }

}
