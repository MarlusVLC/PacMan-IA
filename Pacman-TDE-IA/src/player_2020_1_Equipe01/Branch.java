package player_2020_1_Equipe01;

import pacman.Move;
import pacman.StateEvaluator;

import java.util.List;

public class Branch {
    int vertPos;

    private List<Node> trail;


    public void addNewNode(Node node){
        trail.add(node);
    }

    public float totalScore(StateEvaluator evaluator){
        float scoreSum = 0;
        for (Node node : trail){
            scoreSum += node.evaluateState(evaluator);
        }
        return scoreSum;
    }

    public Move getNextMove(){
        return trail.get(0).getMove();

    }

}
