package test;

import static org.junit.Assert.assertEquals;
import static structures.HashGraph.GraphDirection.DIRECTED;
import static structures.HashGraph.GraphDirection.NON_DIRECTED;
import static structures.HashGraph.GraphWeighting.NON_WEIGHTED;
import static structures.HashGraph.GraphWeighting.WEIGHTED;

import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.Test;

import structures.HashGraph;
import structures.HashGraph.GraphDirection;

@SuppressWarnings("unused")
public class HashGraphTest {
    
    @Test
    public void removeNodeTest() {
        HashGraph<String> linkedGraph = getWeightedGraph(DIRECTED);

        System.out.println(linkedGraph);

        System.out.println("----- DELETING -----");

        linkedGraph.removeNode("D");

        System.out.println(linkedGraph);
    }

    @Test
    public void removeEdgeTest() {
        HashGraph<String> linkedGraph = getWeightedGraph(NON_DIRECTED);

        System.out.println(linkedGraph);

        linkedGraph.removeEdge("D", "B");

        System.out.println(linkedGraph);
    }

    @Test
    public void getNodeTest() {
        HashGraph<String> hashGraph = new HashGraph<>(DIRECTED, WEIGHTED);

        hashGraph
            .addNode("A")
            .addWeightedEdge("A", "B", 10)
            .addWeightedEdge("A", "C", 5);

        System.out.println(hashGraph);

        System.out.println(hashGraph.getNodeByValue("A"));
    }

    @Test
    public void nonWeightedTest() {
        HashGraph<String> hashGraph = new HashGraph<>(DIRECTED, NON_WEIGHTED);

        hashGraph
            .addNode("A")
            .addEdge("A", "B")
            .addEdge("A", "C");

        System.out.println(hashGraph);

        System.out.println(hashGraph.getNodeByValue("A"));
    }

    @Test
    public void BFStest() {
        HashGraph<String> linkedGraph = getWeightedGraph(NON_DIRECTED);

        linkedGraph.runBreadthFirstSearch(linkedGraph.getNodeByValue("C"), node -> System.out.println(node));
    }

    @Test
    public void shortestPathBetweenTestUnweightedTest() {
        HashGraph<String> linkedGraph = getUnweightedGraph(NON_DIRECTED);

        linkedGraph.removeEdge("C", "E");

        System.out.println(linkedGraph.shortestPathBetween("A", "E"));
    }

    @Test
    public void topologicalSortTest() {
        HashGraph<String> linkedGraph = getUnweightedGraph(DIRECTED);
        System.out.println(linkedGraph.topoligicalSort("C"));
    }

    @Test
    public void primaTest() {
        HashGraph<String> linkedGraph = getWeightedGraph(NON_DIRECTED);

        HashGraph<String> linkedGraph2 = new HashGraph<>(false, true);
        linkedGraph2.addNode("A")
            .addWeightedEdge("A", "B", 8)
            .addWeightedEdge("A", "C", 16)
            .addWeightedEdge("A", "D", 7)
            .addWeightedEdge("B", "E", 7)
            .addWeightedEdge("C", "E", 3)
            .addWeightedEdge("D", "E", 12)
            .addWeightedEdge("E", "F", 8)
            .addWeightedEdge("E", "G", 2);

        System.out.println(linkedGraph2.getMinimalSpanningTree(linkedGraph2.getNodeByValue("A")));
    }

    @Test
    public void dijkstraTest() {
        HashGraph<String> linkedGraph = getWeightedGraph(NON_DIRECTED);

        System.out.println(linkedGraph.shortestPathBetween("A", "F"));
    }

    @Test
    public void netFlowTest() {
        HashGraph<String> linkedGraph = new HashGraph<>(NON_DIRECTED, WEIGHTED);
        linkedGraph.addNode("A")
            .addWeightedEdge("A", "B", 7)
            .addWeightedEdge("A", "C", 4)
            .addWeightedEdge("B", "E", 2)
            .addWeightedEdge("B", "C", 4)
            .addWeightedEdge("C", "E", 8)
            .addWeightedEdge("C", "D", 4)
            .addWeightedEdge("E", "F", 5)
            .addWeightedEdge("E", "D", 4)
            .addWeightedEdge("D", "F", 12);

        System.out.println(linkedGraph.netFlow(linkedGraph.getNodeByValue("A"), linkedGraph.getNodeByValue("F")));
    }

    @Test
    public void copyGraphTest() {
        HashGraph<String> graph1 = new HashGraph<>(NON_DIRECTED, WEIGHTED);
        graph1.addNode("A")
            .addWeightedEdge("A", "B", 7)
            .addWeightedEdge("A", "C", 4)
            .addWeightedEdge("B", "E", 2)
            .addWeightedEdge("B", "C", 4)
            .addWeightedEdge("C", "E", 8)
            .addWeightedEdge("C", "D", 4)
            .addWeightedEdge("E", "F", 5)
            .addWeightedEdge("E", "D", 4)
            .addWeightedEdge("D", "F", 12);
        HashGraph<String> graph2 = graph1.copy();
        System.out.println(graph1);
        System.out.println("-------------");
        System.out.println(graph2);
        assertEquals(graph1.toString(), graph2.toString());
    }

    public HashGraph<String> getUnweightedGraph(GraphDirection isDirecred) {
        HashGraph<String> linkedGraph = new HashGraph<>(isDirecred, NON_WEIGHTED);

        linkedGraph
            .addNode("A")
            .addEdge("A", "B")
            .addEdge("A", "C");

        linkedGraph
            .addEdge("B", "D");
        
        linkedGraph
            .addEdge("C", "D")
            .addEdge("C", "E");

        linkedGraph
            .addEdge("D", "F");

        linkedGraph
            .addEdge("E", "F");

        return linkedGraph;
    }

    public HashGraph<String> getWeightedGraph(GraphDirection isDirecred) {
        HashGraph<String> hashGraph = new HashGraph<>(isDirecred, WEIGHTED);

        hashGraph
            .addNode("A")
            .addWeightedEdge("A", "B", 10)
            .addWeightedEdge("A", "C", 5);

        hashGraph
            .addWeightedEdge("B", "D", 20);
        
        hashGraph
            .addWeightedEdge("C", "D", 30)
            .addWeightedEdge("C", "E", 25);

        hashGraph
            .addWeightedEdge("D", "F", 50);

        hashGraph
            .addWeightedEdge("E", "F", 35);

        return hashGraph;
    }
}
