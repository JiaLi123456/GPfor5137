package gptest;

import java.util.List;

public interface RankFunction {
    List<RankScore> getRankFunction(List<Node> population, Graph graph, Vertex destination);
}

