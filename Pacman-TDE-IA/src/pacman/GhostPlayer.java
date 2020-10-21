package pacman;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import ghosts.states.StateMachine;
import util.Pair;

/**
 * An abstract class for class that can choose a ghost's moves in the Pac-Man game.
 * @author grenager
 *
 */
public abstract class GhostPlayer { //EQUIVALENTE À ENTIDADE

  private Color color = Color.PINK;
  private String name =  "";
  protected StateMachine stateMachine;

  //Pertinente ao personagem EXCLUSIVO
  public abstract Move chooseMove(Game game, int ghostIndex, Location target);

  /* Default implementation for deterministic ghosts...will be overridden
   * in nondeterministic ghosts...
   */
  public List<Pair<Move, Double>> getMoveDistribution(Game game, State state, int ghostIndex) {
  		List<Pair<Move, Double>> distribution = new ArrayList<Pair<Move, Double>>();
  		Move choice = chooseMove(game, ghostIndex, );
  		for(Move move : Game.getLegalGhostMoves(state, ghostIndex)) {
  			if(move.equals(choice)) {
  				distribution.add(new Pair<Move, Double>(move, 1.0));
  			} else {
  				distribution.add(new Pair<Move, Double>(move, 0.0));
  			}
  		}
  		return distribution;
  }

//public boolean handleMessage(Message msg) {
//      return stateMachine.handleMesage(msg);
//}

  public Color getColor() {
	  return color;
  }

  public void setColor(Color color) {
	  this.color = color;
  }
  
  public String getName() {
	  return name;
  }

  public void setName(String name) {
	  this.name = name;
  }

  public abstract Move update(Game game,int ghostIndex);
}
