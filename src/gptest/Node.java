package gptest;

import java.util.List;

public interface Node extends Cloneable{

    double evaluate(double[] inputXYZ);
    void display(int indent);
    Object clone();
}

