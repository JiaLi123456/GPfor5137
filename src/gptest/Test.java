package gptest;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args){
        List<Vertex> vertexs = new ArrayList<Vertex>();
        Vertex a = new Vertex("A", 0);
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        vertexs.add(a);
        vertexs.add(b);
        vertexs.add(c);
        vertexs.add(d);
        vertexs.add(e);
        int ab=1;
        int ac=1;
        int cb=1;
        int cd=1;
        int bd=1;
        int be=1;
        int ed=1;
        int[][] edges = {
                {Integer.MAX_VALUE,ab,ac,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,bd,be},
                {Integer.MAX_VALUE,cb,Integer.MAX_VALUE,cd,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,ed,Integer.MAX_VALUE},
                      
        };
        Graph graph = new Graph(vertexs, edges);
        //graph.printGraph();
        graph.search();
        graph.printSSSP();
        Environment.evolve(graph, e,null, 3, 70,100, 7);
        
    }
    
}