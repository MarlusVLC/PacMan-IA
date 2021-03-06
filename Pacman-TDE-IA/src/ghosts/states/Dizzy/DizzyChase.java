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
        //Avisa o Wammy que começou a Perseguir
        try {
            MessageDispatcher.getInstance().dispatchMessage(dizzy, GhostManager.getInstance().getGhost("Wammy"), "Dizzy is Chasing", null);
        }
        catch (Exception e){
           throw new RuntimeException("<DizzyChase> Mensagem não pode ser lida");
        }
    }

    @Override
    public Move execute(Dizzy dizzy, Game game, int ghostIndex) {
//        System.out.println("dizzy is chasing");

        if (dizzy.canReturn()) { return dizzy.goBackwards(); }


        if (dizzy.canChangeToScatter(game, ghostIndex)) {
            dizzy.getStateMachine().changeState(DizzyScatter.getInstance());
        }

        State s = game.getCurrentState();
        return dizzy.chooseMove(game, ghostIndex, s.getPacManLocation());
    }

    @Override
    public void exit(Dizzy dizzy) {

    }

    @Override
    public boolean onMessage(Dizzy dizzy, Message msg) {
        return false;
    }
}
