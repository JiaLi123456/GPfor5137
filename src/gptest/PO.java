package gptest;

import java.util.ArrayList;
import java.util.List;

public class PO {
int POsize=0;
public List<RankScore> PO_test(List<RankScore> scores){
List<RankScore> A=new ArrayList<>();
int size=scores.size();
System.out.println(size);
//int POsize=0;
List<RankScore> B=new ArrayList<>();
boolean flag;
for (RankScore i:scores) {
	i.setPO(0);
}
first:for (RankScore i:scores) {
	flag=true;
	List<Double> iscore=i.getScore();
	double a=iscore.get(0);
	double b=iscore.get(1);
	double c=iscore.get(2);
	
	second:for (RankScore ii:scores) {
		if (i!=ii) {
		List<Double> iiscore=ii.getScore();
		double ia=iiscore.get(0);
		double ib=iiscore.get(1);
		double ic=iiscore.get(2);
		if ((a>=ia)&&(b>=ib)&&(c>=ic)) {
			B.add(i);
			flag=false;
			break second;
		}
		}
	}
	if (flag==true) {
		i.setPO(1);
		A.add(i);	
	}else {
		flag=true;
	}
}
int index=0;
/*for (RankScore test:A) {
	System.out.println(test.getScore().get(0)+"|"+test.getScore().get(1)+"|"+test.getScore().get(2));
}
System.out.println("````````````````");
for (RankScore test:B) {
	System.out.println(test.getScore().get(0)+"|"+test.getScore().get(1)+"|"+test.getScore().get(2));
}
*/
this.POsize=A.size();
while (A.size()<scores.size()) {

	A.add(B.get(index));
	index=index+1;
}
//System.out.println(index);
return A;
}
public int getPOSize() {
	return this.POsize;
}
}

