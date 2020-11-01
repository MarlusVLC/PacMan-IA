package pacman;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import ghosts.Communication.Message;
import ghosts.states.StateMachine;
import util.Pair;

/**
 * An abstract class for class that can choose a ghost's moves in the Pac-Man game.
 * @author grenager
 *
 */
public abstract class GhostPlayer { //EQUIVALENTE À ENTIDADE





  protected Color color = Color.PINK;
  protected String name =  "";
  protected StateMachine stateMachine;
  protected Move lastMove = null;
  protected boolean hasReturned = false;


  public GhostPlayer(String name) {
      this.name = name;

      //Faz o registro no ghostManager:
      GhostManager.getInstance().registerGhost(this);
       System.out.println(name + "Registered");
  }




  //Pertinente ao personagem EXCLUSIVO
  public abstract Move chooseMove(Game game, int ghostIndex, Location target);

  /* Default implementation for deterministic ghosts...will be overridden
   * in nondeterministic ghosts...
   */
  public List<Pair<Move, Double>> getMoveDistribution(Game game, State state, int ghostIndex) {
  		List<Pair<Move, Double>> distribution = new ArrayList<Pair<Move, Double>>();
  		Move choice = chooseMove(game, ghostIndex, state.getPacManLocation());
  		for(Move move : Game.getLegalGhostMoves(state, ghostIndex)) {
  			if(move.equals(choice)) {
  				distribution.add(new Pair<Move, Double>(move, 1.0));
  			} else {
  				distribution.add(new Pair<Move, Double>(move, 0.0));
  			}
  		}
  		return distribution;
  }



public boolean handleMessage(Message msg) {
      return stateMachine.handleMessage(msg);
}




  public Move goBackwards(){
      return lastMove.getOpposite();
  }

  public Move getLastMove(){
      return lastMove;
  }

    public boolean canReturn(){
        //Confere se já fez o movimento de volta. Se não, ele se torna verdade (mesmo que não tenha executado o movimento)
        if (!hasReturned) {
            hasReturned = true;
            //Se tiver feito um movimento anterior, retorne. Feito para para não problemas quando o jogo começa
            //e o fantasma não tem nenhum movimento anterior.
            if (getLastMove() != null) {
                return true;
            }
        }
        return false;
    }

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
