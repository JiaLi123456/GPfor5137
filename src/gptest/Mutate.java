package gptest;

public class Mutate {
	    public static Node mutate(Node t, int pc) {
	        return mutate(t, pc, 0.1);
	    }
	 
	 
	    public static Node mutate(Node t, int pc, double probchange) {
	        //FuncNode result = null;
	        if (Math.random() > probchange) {
	            return t;
	        } else {
	            if (!(t instanceof FuncNode)) {
	                if (t instanceof ConstNode) {
	                	((ConstNode) t).setV(Math.random()*10);
	                	return t;
	                }else if (t instanceof ParamNode){
	                	((ParamNode)t).setIdx((int)(Math.random()*3));
	                	return t;
	                }
	            }
	            FuncNode result = (FuncNode) t.clone();
	            FuncNode temp = (FuncNode) t;
	            for (int i=0; i<temp.getChildren().size(); i++) {
	                result.getChildren().set(i, mutate(temp.getChildren().get(i), pc, probchange));
	            }
	            
	            return result;
	        }
	       // return result;
	    }
	}



