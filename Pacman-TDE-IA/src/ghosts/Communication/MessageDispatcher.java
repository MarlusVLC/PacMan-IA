package ghosts.Communication;

import pacman.GhostPlayer;

public class MessageDispatcher {
    //SINGLETON start
    private static MessageDispatcher INSTANCE = null;

    private MessageDispatcher(){}

    public static MessageDispatcher getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MessageDispatcher();
        }
        return INSTANCE;
    }
    //SINGLETON end

    public void dispatchMessage(GhostPlayer msgSender, GhostPlayer msgReceiver, String message, Object extrainfo){
        //Cria a mensagem
        Message msg = new Message (msgSender, msgReceiver, message, extrainfo);

        //Entrega a mensagem:
        deliverMessage (msgReceiver, msg);
    }

    private void deliverMessage(GhostPlayer receiver, Message msg){
        if(!receiver.handleMessage(msg)){
            System.out.println(receiver.getName() + "não consegue ler a mensagem");
        }
    }
}
