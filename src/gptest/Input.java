package gptest;

import java.util.ArrayList;
import java.util.List;

//not changed
public class Input {
    private double x;
    private double y;
    private double z;
    private double outputX;
    private double outputY;
    private List<Integer> indexs=new ArrayList<>();
 
    public Input() {}
 
    public Input(double x, double y,double z, double outputX,double outputY,List<Integer> indexs) {
        this.x = x;
        this.y = y;
        this.z=z;
        this.outputX = outputX;
        this.outputY=outputY;
        this.indexs=indexs;

    }
 public void setIndex(List<Integer> indexs) {
	 this.indexs=indexs;
	 
 }
 public List<Integer> getIndex(){
	 return indexs;
 }
    public double getOutputX() {
        return outputX;
    }
    public double getOutputY() {
    	return outputY;
    }
 
    public double getX() {
        return x;
    }
 
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
 
    public void setOutputX(double x) {
        this.outputX = x;
    }
    
    public void setOutputY(double y) {
    	this.outputY=y;
    }
 
    public void setX(double x) {
        this.x = x;
    }
 
    public void setY(double y) {
        this.y = y;
    }
    
    public void setZ(double z) {
        this.z=z;
    }

}
