package pacman;

import java.util.HashMap;

public class GhostManager {
    //HashMap onde são registradas todas as instâncias do jogo:
    private HashMap<String, GhostPlayer> map;

    //SINGLETON begin
    private static GhostManager INSTANCE = null;

    private GhostManager(){ map = new HashMap<String, GhostPlayer>(); }

    public static GhostManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GhostManager();
        }
        return INSTANCE;
    }

    //SINGLETON end

    public void registerGhost(GhostPlayer newGhost) { map.put(newGhost.getName(), newGhost);}

    public GhostPlayer getGhost(String name){
        GhostPlayer ghost = map.get(name);
        return ghost;
    }

    public void removeGhost(GhostPlayer ghost) {map.remove(ghost.getName());}

    public HashMap getMap(){
        return map;
    }
}
