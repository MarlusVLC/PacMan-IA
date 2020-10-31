package ghosts.states;

import pacman.Game;
import pacman.Move;

public class StateMachine <Ghost> {
    //Defines who's the owner of this object
    private Ghost myOwner;

    //Defines the current state
    private GhostState<Ghost> currentState;

    //Defines the current state
    private GhostState<Ghost> previousState;

    //Defines the current state
    private GhostState<Ghost> globalState;

    public StateMachine(Ghost owner) {
        myOwner = owner;
        currentState = null;
        previousState = null;
        globalState = null;
    }



    //Invoca este método pra atualizar a FSM
    public Move update(Game game, int ghostIndex){
        //Se existir um estado global, inovca o seu método execute, do contrário
        // nada é feito;
        Move move = null;
        if (globalState != null){
           move = globalState.execute(myOwner, game, ghostIndex);
        }

        //Idem para o estado atual:
        if (currentState != null){
           move = currentState.execute(myOwner, game, ghostIndex);
        }

        return move;
    }


    //faz a troca de estados
    public void changeState(GhostState<Ghost> newState){

        //Armazena o estado anterior
        previousState = currentState;

        //Invoca o método de saída do estado atual
        currentState.exit(myOwner);

        //Faz a troca de estados
        currentState = newState;

        //Invoca o método do entrada do novo estado
        currentState.enter(myOwner);
    }


    //Reverte ao estado anterior
    public void revertToPreviousState(){
        changeState(previousState);
    }

//    public boolean handleMessage(Message msg){
//        //Verifica se o estado atual é capaz de lidar com a mensagem recebida:
//        if (currentState.onMessage(myOwner, msg)){
//            return true
//        }
//
//        //Se o estado atual não souber lidar com a mensagem, verifica
//        // se existe um estado global e ele sabe lidar com a mensagem:
//        if (globalState != null && globalState.onMessage(myOwner, msg)){
//            return true;
//        }
//
//        //Do contrário, retorne falso
//        return false;
//    }

    public GhostState<Ghost> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GhostState<Ghost> state) {
        this.currentState = state;
    }

    public GhostState<Ghost> getPreviousState() {
        return previousState;
    }

    public void setPreviousState(GhostState<Ghost> state) {
        this.previousState = state;
    }

    public GhostState<Ghost> getGlobalState() {
        return globalState;
    }

    public void setGlobalState(GhostState<Ghost> State) {
        this.globalState = State;
    }
}
