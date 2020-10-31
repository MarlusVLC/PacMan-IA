package ghosts.Communication;

import pacman.GhostPlayer;

public class Message {
    private GhostPlayer sender;
    private GhostPlayer receiver;
    private String msg;
    private Object extrainfo;

    public Message (GhostPlayer msgSender, GhostPlayer msgReceiver, String message, Object extrainfo){
        this.sender = msgSender;
        this.receiver = msgReceiver;
        this.msg = message;
        this.extrainfo = extrainfo;
    }


    //Retorna o conteúdo da mensagem:
    public String getMessage() { return msg; }
}
