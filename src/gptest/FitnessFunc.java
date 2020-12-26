package gptest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FitnessFunc{
	//different inputs and outputs for different groups
    public static List<Input> inputAndOutput(Graph graph,Vertex destination) {
        List<Input> inputs = new ArrayList<>();
        List<Integer> paths=graph.getPathIndexs(destination);
        int [][]weights=graph.getEdges();
        //System.out.println(destination.getName());
       // System.out.println(paths.size());
        for (int index:paths) {
        	//System.out.println("index:"+index);
        	
        //no z, to be checked, 保留做参照，input class not complicated
        double inputDelay=Math.random();
        double inputUtil=Math.random();
        int i=(int)(index/(graph.getVertexs().size()));
       
        int ii=index%(graph.getVertexs().size());
       // System.out.println(i);
        //System.out.println(ii);
        double weight=weights[i][ii]*1.0;
        double OutputDelay=Math.random();
        double OutputUtil=Math.random();
        Input inputAdd=new Input();
        inputAdd.setX(inputDelay);
        inputAdd.setY(inputUtil);
        inputAdd.setZ(weight);
        inputAdd.setOutputX(OutputDelay);
        inputAdd.setOutputY(OutputUtil);
        inputAdd.setIndex(paths);
        inputs.add(inputAdd);
        }
       // System.out.println(inputs.size());
        return inputs;
    }

    //fitness functions
    //return a list contains three element: 1.FitUtil; 2.Delay; 3.Cost
     public  List<Double> fitness(Node trees,Graph graph, Vertex destination) {
            //double result = 0;
            List<Double> returns=new ArrayList<>();
           // System.out.println("00000000000000000000000"+graph.getPathIndexs(destination).size());

           // int[] indexChange=new int[trees.size()];
           // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa"+trees.size());
            Double returnMaxUtil=0.0;
    		List<Input>inAndOuts=inputAndOutput(graph,destination);
            double[] v = new double[inAndOuts.size()];
            double[] newUtil=new double[inAndOuts.size()];
            double[] newDelay=new double[inAndOuts.size()];
            List<Integer> updateEdgesIndex=new ArrayList<>();
    		//System.out.println("inAndOuts:"+inAndOuts.size());
            for (int i=0;i<inAndOuts.size();i++) {
            //	System.out.println("tree number:" +i);
            	double[] inputXYZ=new double[3];
            	double[] outputXY=new double[2];
            	Input inAndOut=inAndOuts.get(i);
            	inputXYZ[0]=inAndOut.getX();
            	inputXYZ[1]=inAndOut.getY();
            	inputXYZ[2]=inAndOut.getZ();
            	
            	outputXY[0]=inAndOut.getOutputX();
            	outputXY[1]=inAndOut.getOutputY();
            	//System.out.println("X:"+inputXYZ[0]+"; Y:"+inputXYZ[1]+"; Z:"+inputXYZ[2]+"; OutputX: "+outputXY[0]+"; OutputY:"+outputXY[1]);
            		
            	v[i]=Math.abs(trees.evaluate(inputXYZ));
            	newUtil[i]=outputXY[0];
            	newDelay[i]=outputXY[1];
            	updateEdgesIndex=inAndOut.getIndex();
            	//System.out.println("weight:"+v[i]);
            	};
             Graph newGraph=graph.clone();
             //newGraph.printSSSP();
             for (double ii:v) {
            	System.out.println(ii);
             }
             List <Integer> linkIndex=graph.getLinkIndexs();
             
             //v-a
             double[]va=new double[graph.getEdgesNum()];
             //newUtil-b
             double[] Utilb=new double[graph.getEdgesNum()];
             //newDelay-c
             double[] Delayc=new double[graph.getEdgesNum()];
             int flag=0;
             a:for (int lIndex=0;lIndex<linkIndex.size();lIndex++) {
            	 b:for (int lIndex2=0;lIndex2<updateEdgesIndex.size();lIndex2++) {
            		// System.out.println(linkIndex.get(lIndex)+",,,,,,"+updateEdgesIndex.get(lIndex2));
            		// System.out.println();
            		 //System.out.println(".......................................");
            		 if (linkIndex.get(lIndex)==updateEdgesIndex.get(lIndex2)) {
            			 va[lIndex]=v[lIndex2];
            			 Utilb[lIndex]=newUtil[lIndex2];
            			 Delayc[lIndex]=newDelay[lIndex2];
            			 flag=1;
            			 break b;
            		 }
            	 }
             if (flag==0) {
            	 va[lIndex]=(graph.getEdges())[(int)(linkIndex.get(lIndex)/graph.getVertexs().size())][linkIndex.get(lIndex)%graph.getVertexs().size()];
            	// System.out.println(lIndex+ " "+(int)(linkIndex.get(lIndex)/(graph.getVertexs().size()))+"---------"+linkIndex.get(lIndex)%(graph.getVertexs().size()));
            	 Utilb[lIndex]=graph.getUtil(linkIndex.get(lIndex));
            	 Delayc[lIndex]=graph.getDelay(linkIndex.get(lIndex));
             }else {
            	 flag=0;
             }
            	 
             }
             
             newGraph.update(va,Utilb,Delayc);
             //newGraph.search(destination);
             newGraph.printSSSP();
             //graph.printSSSP();
             List<Integer> edgesIndex=newGraph.getPathIndexs(destination);
             Double maxUtil=0.0;
             Double delay=0.0;
 
             for (int i:edgesIndex) {
            	// System.out.println(i);
            	 if (newGraph.getUtil(i)>maxUtil) {
            		// System.out.println("edgeIndex:"+i+"|"+newGraph.getUtil(i));
            		 maxUtil=newGraph.getUtil(i);
            	 };
             }
             returnMaxUtil=maxUtil;
            returns.add(returnMaxUtil);
            if (returnMaxUtil<=0.8) {
            	for (int i:edgesIndex) {
            		//System.out.println(newGraph.getDelay(i));
            		delay=delay+newGraph.getDelay(i);
            	}
            }else
            {
            	delay=(double)Integer.MAX_VALUE;
            }
            returns.add(delay);
            //edit distance for delay
            double cost=0.0;
            graph.search();
            String a=destination.getPathsfromA();
           // System.out.println(a);
           // System.out.println(destination.getPath());
            newGraph.search();
            String b=destination.getPathsfromA();
           // System.out.println(b);
           // System.out.println(destination.getPath());
            cost=getDistance(a, b);
            returns.add(cost);
           // System.out.println("Util:"+returnMaxUtil+" | delay:"+delay+" | cost:"+cost);
            //System.out.println(".................................");
            return returns;           
        }

    // Rank the trees
    public  List<RankScore> getRankFunction(List<Node> population,Graph graph, Vertex destination) {
        List<RankScore> scores = new ArrayList<>();
        int i=0;
        int pop=population.size();
        for (; i<pop; i++) {
        	System.out.println("popnumber"+i);
           // RankScore rankScore = new RankScore();
            Node tree=population.get(i);
            RankScore rankScore=new RankScore(fitness(tree,graph,destination),tree);
            //rankScore.getClass();
            //(fitness(tree, graph, destination),tree),
           // System.out.println(rankScores.size());
           // rankScore.setT(tree);
            //rankScore.setScore(rankScores);
            scores.add(rankScore);
        }
        PO po = new PO();
        List<RankScore>scoresPO=po.PO_test(scores);
        return scoresPO;
    }


    
 public double getDistance(String A, String B) {
        if(A.equals(B)) {
            //System.out.println(0);
            return 0.0;
        }
        //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for(int i = 1;i <= A.length();i++)
            dp[i][0] = i;
        for(int j = 1;j <= B.length();j++)
            dp[0][j] = j;
        for(int i = 1;i <= A.length();i++) {
            for(int j = 1;j <= B.length();j++) {
                if(A.charAt(i - 1) == B.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
       // System.out.println(dp[A.length()][B.length()]);
        return dp[A.length()][B.length()];
    }
}


