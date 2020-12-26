package gptest;
public class Vertex implements Comparable<Vertex>{

    /**
     * 节点名称(A,B,C,D)
     */
    private String name;
    private String pathfromA="";
    
    /**
     * 最短路径长度
     */
    private int path;
    
    /**
     * 节点是否已经出列(是否已经处理完毕)
     */
    private boolean isMarked;
    public void iniPathfromA() {
    	this.pathfromA="";
    }
    
    public void setMarked(boolean bo) {
    	isMarked=bo;
    };
    
    public Vertex(String name){
        this.name = name;
        this.path = Integer.MAX_VALUE; //初始设置为无穷大
        this.setMarked(false);
    }
    
    public Vertex(String name, int path){
        this.name = name;
        this.path = path;
        this.setMarked(false);
    }
    
    @Override
    public int compareTo(Vertex o) {
        return o.path > path?-1:1;
    }

	public boolean isMarked() {
		// TODO Auto-generated method stub
		return this.isMarked;
	}

	public int getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}

	public void setPath(int distance) {
		this.path=distance;
		
	}
	public String getPathsfromA() {
		// TODO Auto-generated method stub
		return this.pathfromA+this.getName();
	}

	public void setPathsfromA(Vertex neighbor) {
		pathfromA=pathfromA+neighbor.getPathsfromA();
		this.pathfromA=removeRepeat(pathfromA);
		//this.pathfromA=pathfromA.substring(neighbor.getPath());
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public String removeRepeat(String str) {
		//System.out.println(str);
		
		StringBuffer sb = new StringBuffer(str);
		
		String rs = sb.reverse().toString().replaceAll("(.)(?=.*\\1)", "");
		//System.out.println(sb);
		
		StringBuffer out = new StringBuffer(rs);
		
	    return out.reverse().toString();
	}


}