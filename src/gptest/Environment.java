package gptest;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    public static List<RankScore> evolve(Graph graph, Vertex destination,List<Node> p,int pc, int popsize) {
        return evolve(graph, destination,p, pc, popsize,500);
    }
    
    public static List<RankScore> evolve(Graph graph, Vertex destination,List<Node> p,int pc, int popsize,  int maxgen) {
        return evolve(graph, destination,p, pc, popsize,  maxgen,0.1);
    }
 
 
    public static List<RankScore> evolve(Graph graph, Vertex destination,List<Node> p,int pc, int popsize,  int maxgen, double mutationrate) {
        return evolve(graph, destination,p, pc, popsize,  maxgen, mutationrate, 0.4);
    }
 
    public static List<RankScore> evolve(Graph graph, Vertex destination,List<Node> p,int pc, int popsize, int maxgen,double mutationrate, double breedingrate) {
        return evolve(graph, destination,p, pc, popsize, maxgen, mutationrate, breedingrate, 0.7);
    }
 
    public static List<RankScore> evolve(Graph graph, Vertex destination,List<Node> p,int pc, int popsize,  int maxgen, double mutationrate, double breedingrate, double pexp) {
        return evolve(graph, destination,p, pc, popsize, maxgen, mutationrate, breedingrate, pexp,0.05);
    }
    public static List<RankScore> evolve(Graph graph, Vertex destination,List<Node> p, int pc, int popsize, int maxgen,double mutationrate, double breedingrate, double pexp, double pnew) {
        List<Node> population ;
        FitnessFunc rankFunc=new FitnessFunc();
        if (p == null) {
            population = new ArrayList<>();
            //System.out.println(popsize);
            for (int i = 0; i < popsize; i++) {
                population.add(RandomTree.makeRandomTree(pc));
                //population.getT().display(2);
            }
        } else {
            population = p;
        }
        List<RankScore> scores = new ArrayList<>();
        List<Node> newpop = null;
       // System.out.println(popsize);
        //System.out.println(maxgen);
        for (int i = 0; i < maxgen; i++) {
        	//System.out.println(rankFunc.getRankFunction(population,graph, destination));
            scores =rankFunc.getRankFunction(population,graph, destination);

           // int groupNumberIndex=0;
            newpop = new ArrayList<>();
            
           // while (groupNumberIndex<groups) {
            int iadd=0;
            List<Node>tempNewPop=new ArrayList<>();
            while (tempNewPop.size()<popsize/2) {
            	tempNewPop.add(scores.get(iadd).getT());
            	iadd=iadd+1;
           	}
           	while (tempNewPop.size()<popsize) {
           		if (Math.random()>pnew) {
           			//choose parents randomly, need to be updated
           			int index1=(int)(Math.random()*popsize);
           			int index2=(int)(Math.random()*popsize);
           			tempNewPop.add(Mutate.mutate(Crossover.crossover(scores.get(index1).getT(),scores.get(index2).getT(), breedingrate), pc,mutationrate));
      
           		}
           		else {
           			tempNewPop.add(scores.get(tempNewPop.size()).getT());
            		}
            		
            	}
            	for (int index=0;index<tempNewPop.size();index++) {
            		newpop.add(tempNewPop.get(index));
            		
            	}
            	population=newpop;
            	
            }
        PO po = new PO();
        List<RankScore>scoresPO=po.PO_test(scores);	
        int poSize=po.POsize;
        List<RankScore> POscores=new ArrayList<>();
        //int indexpo=0;
        for (int indexpo=0;indexpo<poSize;indexpo++){
        	if(scores.get(indexpo).getT() instanceof FuncNode) {
        	POscores.add(scores.get(indexpo));
        	}
        	//indexpo=indexpo+1;
        	//System.out.println(x.getScore().get(0)+"|"+x.getScore().get(1)+"|"+x.getScore().get(2));

        }
      //  for (RankScore m:POscores) {
        	//System.out.println(m.getScore().get(0)+"|"+m.getScore().get(1)+"|"+m.getScore().get(2));
       // }
        while(POscores.size()>=4) {
        	int indexA=0;
        	double maxA=0;
        	int indexB=0;
        	double maxB=0;
        	int indexC=0;
        	double maxC=0;
        	for (int indexD=0;indexD<POscores.size();indexD++) {
        		if (POscores.get(indexD).getScore().get(0)>maxA) {
        			maxA=POscores.get(indexD).getScore().get(0);
        			indexA=indexD;
        		}
        	}
        	//System.out.println(indexA+"|"+indexB+"|"+indexC);
        	POscores.remove(indexA);
        	for (int indexD=0;indexD<POscores.size();indexD++) {
        		if (POscores.get(indexD).getScore().get(1)>maxB) {
        			maxB=POscores.get(indexD).getScore().get(1);
        			indexB=indexD;
        		}
        	}
        	POscores.remove(indexB);
        	for (int indexD=0;indexD<POscores.size();indexD++) {
        		if (POscores.get(indexD).getScore().get(2)>maxC) {
        			maxC=POscores.get(indexD).getScore().get(2);
        			indexC=indexD;
        		}
        	}
        	POscores.remove(indexC);
        	POscores=po.PO_test(POscores);
        	//System.out.println(POscores.size());
        	
        	
        }
        
        System.out.println();
        
        
        POscores.get(0).getT().display(2);
        System.out.println("-------------------");
       // scores.get(1).getT().display(2);
      //  System.out.println("-------------------");
       // scores.get(2).getT().display(2);

        return scores;
}
 
    }



