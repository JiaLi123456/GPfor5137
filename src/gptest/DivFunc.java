package gptest;

public class DivFunc  implements Func, Cloneable{
	    @Override
	    public double cal(double[] l) {
	    	if (l[1]!=0) {
	        return l[0] / l[1];
	    	}else
	    	{
	    		return 0;
	    	}
	    }
	 
	    @Override
	    public Object clone() {
	        Func f = null;
	        try {
	            f = (Func) super.clone();
	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	        }
	        return f;
	    }
	}



