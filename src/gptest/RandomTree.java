package gptest;

import java.util.ArrayList;
import java.util.List;

public class RandomTree {
	 
    public static Node makeRandomTree(int pc) {
        return makeRandomTree(pc,3,0.5, 0.6);
    }
 
    public static Node makeRandomTree(int pc, int maxdepth) {
        return makeRandomTree(pc,maxdepth,0.5, 0.6);
    }
 
    public static Node makeRandomTree(int pc, int maxdepth, double fpr) {
        return makeRandomTree(pc,maxdepth,fpr, 0.6);
    }
 
    public static Node makeRandomTree(int pc, int maxdepth, double fpr, double ppr) {
        if (Math.random() < fpr && maxdepth > 0) {
            FunctionWapper f = ConstantWapper.flist[(int)(Math.random()*4)];
            List<Node> children = new ArrayList<>();
            for (int i=0; i<f.getChildCount(); i++) {
                children.add(makeRandomTree(pc, maxdepth-1, fpr, ppr));
            }
            return new FuncNode(f, children);
        } else if (Math.random() < ppr) {
            return new ParamNode((int)(Math.random()*pc));
        } else {
            return new ConstNode((int)(Math.random()*10));
        }
    }
}


