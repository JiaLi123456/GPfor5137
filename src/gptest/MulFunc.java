package gptest;

public class MulFunc implements Func, Cloneable {
    @Override
    public double cal(double[] l) {
        return l[0] * l[1];
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

