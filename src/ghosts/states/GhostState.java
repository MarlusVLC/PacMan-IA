package ghosts.states;

import pacman.Game;
import pacman.Move;

public interface GhostState <Ghost> {
    public void enter (Ghost ghost);

    public Move execute (Ghost ghost, Game game, int ghostIndex);

    public void exit (Ghost ghost);

//    boolean onMessage(Ghost ghost, Message msg);
}
