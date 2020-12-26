package gptest;

public class ConstantWapper {
    public static final FunctionWapper addw = new FunctionWapper(new AddFunc(), 2, "add");
    public static final FunctionWapper subw = new FunctionWapper(new SubFunc(), 2, "substract");
    public static final FunctionWapper mulw = new FunctionWapper(new MulFunc(), 2, "multiply");
    public static final FunctionWapper divw=new FunctionWapper(new DivFunc(),2,"divide");
    public static final FunctionWapper[] flist = {addw, mulw, subw, divw};
}


