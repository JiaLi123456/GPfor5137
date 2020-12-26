package gptest;

public class ParamNode implements Node{
    private int idx;
 
    public ParamNode(int idx) {
        this.idx = idx;
    }
 
    public double evaluate(double[] inp) {
        return inp[this.idx];
    }
 
    @Override
    public void display(int indent) {
        String str = "";
        for (int i=0; i<indent; i++) str += ' ';
        if (this.idx==0)
        {
        	System.out.println(str+ "Util");
        }else if (this.idx==1)
        {
        	System.out.println(str+ "Delay");
        }
        else if (this.idx==2)
        {
        	System.out.println(str+ "Cost");
        }else
        	{
        	System.out.println(String.format("%sp%d", str, this.idx));
        	}
    }
 
    public int getIdx() {
        return idx;
    }
 
    public void setIdx(int idx) {
        this.idx = idx;
    }
 
    @Override
    public Object clone() {
        Node result=null ;
        try {
           result = (Node) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}


