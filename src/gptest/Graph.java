package gptest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class Graph implements Cloneable {

    /*
     * 顶点
     */
    private List<Vertex> vertexs;

    /*
     * 边
     */
    private int[][] edges;
    
    private double[][] utils;
    
    private double[][] delays;;
    
    private List<Integer> pathIndexs;

    /*
     * 没有访问的顶点
     */
    private Queue<Vertex> unVisited;

    public Graph(List<Vertex> vertexs, int[][] edges) {
        this.vertexs = vertexs;
        this.edges = edges;
        //System.out.println(edges[0][0]);
        //System.out.println(edges[0]);
        double[][] temputil = new double[vertexs.size()][vertexs.size()];
        double[][] tempdelay = new double[vertexs.size()][vertexs.size()];
       
        for (int i=0;i<vertexs.size();i++) {
        	for (int ii=0; ii<edges[0].length;ii++) {
        		if (edges[i][ii]==Integer.MAX_VALUE)
        		{
        			
        			//System.out.println(Integer.MAX_VALUE);
        			//System.out.println(ii);
        			temputil[i][ii]=Integer.MAX_VALUE;
        			tempdelay[i][ii]=Integer.MAX_VALUE;
        		}
        		else {
        			temputil[i][ii]=0.0;
        			tempdelay[i][ii]=0.0;
        		}
        	}
        }
       this.utils=temputil;
       this.delays=tempdelay;
        initUnVisited();
    }

public Graph clone() {
	Graph o=null;
   
            try {
               o = (Graph) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            } return o;
        }
//find a better way
public Graph update(double[] v, double[] newUtil, double[] newDelay) {
	//System.out.println(v.length);
	for (Vertex vi: this.vertexs) {
		if (vi.getName()=="A") {
			vi.setPath(0);
		}else {
		vi.setPath(Integer.MAX_VALUE);
		}
	}

    int ab=(int)v[0];
    int ac=(int)v[1];
    int bd=(int)v[2];
    int be=(int)v[3];
    int cb=(int)v[4];
    int cd=(int)v[5];
    int ed=(int)v[6];

    int[][] edges = {
            {Integer.MAX_VALUE,ab,ac,Integer.MAX_VALUE,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,bd,be},
            {Integer.MAX_VALUE,cb,Integer.MAX_VALUE,cd,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,ed,Integer.MAX_VALUE},
                  
    };
    double[][] Utils = {
            {Integer.MAX_VALUE,newUtil[0],newUtil[1],Integer.MAX_VALUE,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,newUtil[2],newUtil[3]},
            {Integer.MAX_VALUE,newUtil[4],Integer.MAX_VALUE,newUtil[5],Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,newUtil[6],Integer.MAX_VALUE},
    };
    double[][] Delays = {
            {Integer.MAX_VALUE,newDelay[0],newDelay[1],Integer.MAX_VALUE,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,newDelay[2],newDelay[3]},
            {Integer.MAX_VALUE,newDelay[4],Integer.MAX_VALUE,newDelay[5],Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,newDelay[6],Integer.MAX_VALUE},
          
    };
    this.edges=edges;
    this.utils=Utils;
    this.delays=Delays;
	return this;
	
}
    
    
    public List<Vertex> getVertexs() {
    	return this.vertexs;
    }
    public int[][] getEdges(){
    	return this.edges;
    }
    /*
     * 搜索各顶点最短路径
     */
    public void search(){
    	initUnVisited();
    	//System.out.println(unVisited.size());
    	for(Vertex a:this.vertexs) {
    		a.setMarked(false);
    		a.iniPathfromA();
    		if (a.getName()=="A") {
    		a.setPath(0);
    		}else {
    			a.setPath(Integer.MAX_VALUE);
    		}
    	}
        while(!unVisited.isEmpty()){
            Vertex vertex = unVisited.element();
            //顶点已经计算出最短路径，设置为"已访问"
          vertex.setMarked(true);    
            //获取所有"未访问"的邻居
           List<Vertex> neighbors = getNeighbors(vertex);    
           for (Vertex a:neighbors) {
        	   //System.out.println(a.getName());
           }
            //更新邻居的最短路径
            updatesDistance(vertex, neighbors);        
            pop();
        }
        //System.out.println("search over");
    }
    
    /*
     * 更新所有邻居的最短路径
     */
    private void updatesDistance(Vertex vertex, List<Vertex> neighbors){
        for(Vertex neighbor: neighbors){
            updateDistance(vertex, neighbor);
        }
    }
    
    /*
     * 更新邻居的最短路径
     */
    private void updateDistance(Vertex vertex, Vertex neighbor){
        int distance = getDistance(vertex, neighbor) + vertex.getPath();
        //System.out.println(distance);
       //System.out.println(neighbor.getPath());
        if(distance < neighbor.getPath()){
            neighbor.setPath(distance);
            neighbor.setPathsfromA(vertex);
            unVisited.add(neighbor);
        }
    }

    /*
     * 初始化未访问顶点集合
     */
    private void initUnVisited() {
        unVisited = new PriorityQueue<Vertex>();
        for (Vertex v : vertexs) {
            unVisited.add(v);
        }
    }

    /*
     * 从未访问顶点集合中删除已找到最短路径的节点
     */
    private void pop() {
        unVisited.poll();
    }

    /*
     * 获取顶点到目标顶点的距离
     */
    private int getDistance(Vertex source, Vertex destination) {
        int sourceIndex = vertexs.indexOf(source);
        int destIndex = vertexs.indexOf(destination);
        //System.out.println(source.getName()+"|"+destination.getName()+"|"+edges[sourceIndex][destIndex]);
        return edges[sourceIndex][destIndex];
    }

    /*
     * 获取顶点所有(未访问的)邻居
     */
    private List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        int position = vertexs.indexOf(v);
        Vertex neighbor ;
        int distance;
        for (int i = 0; i < vertexs.size(); i++) {
            if (i == position) {
                //顶点本身，跳过
                continue;
            }
            distance = edges[position][i];    //到所有顶点的距离
            if (distance < Integer.MAX_VALUE) {
                //是邻居(有路径可达)
                neighbor = getVertex(i);
                if (!neighbor.isMarked()) {
                    //如果邻居没有访问过，则加入list;
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    /*
     * 根据顶点位置获取顶点
     */
    private Vertex getVertex(int index) {
        return vertexs.get(index);
    }

    /*
     * 打印图
     */
    public void printGraph() {
        int verNums = vertexs.size();
        for (int row = 0; row < verNums; row++) {
            for (int col = 0; col < verNums; col++) {
                if(Integer.MAX_VALUE == edges[row][col]){
                    System.out.print("*");
                    System.out.print(" ");
                    continue;
                }
                System.out.print(edges[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("..............................");
    }
    public void printSSSP() {
    	search();
    	for (Vertex v: vertexs) {
    		//printGraph();
    		System.out.println(v.getName()+"："+v.getPath()+" "+v.getPathsfromA());
    		//System.out.println(v.getPathsfromA());
    	}
    	//System.out.println(".........................................................................");
    	for (int index=0; index<this.getAllEdgesNum();index++) {
    		int i=(int)(index/vertexs.size());
    		int ii=index%vertexs.size();
    		if (this.getDelay(index)!=Integer.MAX_VALUE) {
    		
    		//System.out.println(index+": "+"delay: "+this.getDelay(index)+"    Util: "+this.getUtil(index)+"     weight: "+edges[i][ii]);
    		
    		}//System.out.println();
    		//System.out.println("weight"+this.);
    		
    	}
    	//System.out.println(this.getDelay(index)));
    	printGraph();
    }
    public Double getUtil(int index) {

        int count=0;
        Double result=0.0;
        for (int i=0;i<vertexs.size();i++) {
        	for (int ii=0; ii<this.utils[0].length;ii++) {
        		if (count==index) {
        			result=utils[i][ii];
        			return result;
        		}
        		count=count+1;
        	}
        }
        
                return 0.0;
    }
    public void setUtil(Double[][] util) {
        for (int i=0;i<vertexs.size();i++) {
        	for (int ii=0; ii<util[0].length;ii++) {
        		utils[i][ii]=util[i][ii];
        	}
        }
 
    	
    }
    public Double getDelay(int index) {

        int count=0;
        Double result=0.0;
        for (int i=0;i<vertexs.size();i++) {
        	for (int ii=0; ii<this.delays[0].length;ii++) {
        		if (count==index) {
        			result=delays[i][ii];
        			return result;
        		}
        		count=count+1;
        	}
        }
        
                return 0.0;
    }
    public void setDelays(Double[][] delay) {
        for (int i=0;i<vertexs.size();i++) {
        	for (int ii=0; ii<delay[0].length;ii++) {
        		utils[i][ii]=delay[i][ii];
        	}
        }
 
    	
    }
    public int getEdgesNum() {
    	int count=0;
    	for (int i=0;i<vertexs.size();i++) {
    		for (int ii=0;ii<vertexs.size();ii++) {
    			if (edges[i][ii]!=Integer.MAX_VALUE) {
    				count=count+1;
    			}
    		}
    	}
    	return count;
    }
    public List<Integer> getLinkIndexs(){
    	List<Integer> index=new ArrayList<>();
    	//System.out.println(vertexs.size());
    	for (int i=0;i<vertexs.size();i++) {
    		for(int ii=0;ii<vertexs.size();ii++) {
    		//	System.out.println(i+":::"+ii);
    			if(edges[i][ii]!=Integer.MAX_VALUE) {
    				index.add(i*vertexs.size()+ii);
    			}
    		}
    	}
    	return index;
    }
    
    public int getAllEdgesNum() {
    	int count=0;
    	for (int i=0;i<vertexs.size();i++) {
    		for (int ii=0;ii<vertexs.size();ii++) {
    			//if (edges[i][ii]!=Integer.MAX_VALUE) {
    				count=count+1;
    			//}
    		}
    	}
    	return count;
    }
    public List<Integer> getPathIndexs(Vertex destination){

    	String paths=destination.getPathsfromA();
    	//System.out.println(paths);
    	char[] pathVertex = new char[paths.length()];
    	List<Integer> result = new ArrayList<>();
    	for (int i=0; i<paths.length();i++) {
    		pathVertex[i]=paths.charAt(i);
    		//System.out.println(pathVertex[i]);
    	}
    	int charSize=pathVertex.length;
    	for (int i=0;i<charSize;i++) {
    		int difFromA=pathVertex[i]-'A';
    		if (difFromA!=0) {
    			if (pathVertex[i-1]=='A') {
    				result.add(difFromA);
    			}
    			else {
    				//char pastVertex=pathVertex[i-1];
    				int dif1=Math.abs(pathVertex[i-1]-'A');
    				int dif2=Math.abs(pathVertex[i]-'A');
    				int size=vertexs.size();
    				int indexnew=dif1*size+dif2;
    				result.add(indexnew);
    			}
    		}
    		
    	}
    	return result;
    }
    
}