package pacman;

import pacman.Game;

public class TestStuff {
    Game game;

    public TestStuff(Game game){
        this.game = game;
    }

    public void printLocations(){
        System.out.println(game.getAllLocationsCopy());
    }
}
