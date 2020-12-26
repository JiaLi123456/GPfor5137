package gptest;

public class ConstNode implements Node{
    private double v;
 
    public ConstNode(double v) {
        this.v = v;
    }
 
    public double evaluate(double[] inp) {
        return this.v;
    }
 
    @Override
    public void display(int indent) {
        String str = "";
        for (int i=0; i<indent; i++) str += ' ';
        System.out.println(String.format("%s%d", str, (int)this.v));
    }
 
 
    public double getV() {
        return v;
    }
 
    public void setV(double v) {
        this.v = v;
    }
 
    @Override
    public Object clone() {
        Node result = null;
        try {
            result = (Node) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

