package gptest;

import java.util.ArrayList;
import java.util.List;

//used for sort (finding Pareto front)
public class RankScore{
	 
    private List<Double> score=new ArrayList<>();
    private Node t;
    int POorNot=0;

    public RankScore(List<Double> score, Node t) {
    	this.score=score;
    	this.t=t;
    	this.POorNot=0;
    }
 
    public List<Double> getScore() {
        return score;
    }
 
    public void setScore(List<Double> score) {
        this.score.add(score.get(0));
        this.score.add(score.get(1));
        this.score.add(score.get(2));
    }
 
    public Node getT() {
        return this.t;
    }
 
 
    public void setT(Node n) {
        this.t=n;
    }
    public void setPO (int i) {
    	this.POorNot=i;
    }
    public int POorNot() {
    	return POorNot;
    }
    
}

