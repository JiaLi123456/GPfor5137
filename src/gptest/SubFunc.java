package gptest;

public class SubFunc implements Func, Cloneable {
    @Override
    public double cal(double[] l) {
        return l[0] - l[1];
    }
 
    @Override
    public Object clone() {
        Func f =null;
        try {
             f = (Func) super.clone();
            return f;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

    }
}

