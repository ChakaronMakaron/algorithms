package test;

import org.junit.Test;

import structures.HashGraph;

public class HashGraphTest {
    
    @Test
    public void removeNodeTest() {
        HashGraph<String> linkedGraph = new HashGraph<>(true, true);

        linkedGraph
            .addEdge(null, "A")
            .addWeightedEdge("A", "B", 10)
            .addWeightedEdge("A", "C", 5);

        linkedGraph
            .addWeightedEdge("B", "D", 20);
        
        linkedGraph
            .addWeightedEdge("C", "D", 30)
            .addWeightedEdge("C", "E", 25);

        linkedGraph
            .addWeightedEdge("D", "F", 50);

        linkedGraph
            .addWeightedEdge("E", "F", 35);

        System.out.println(linkedGraph);

        System.out.println("----- DELETING -----");

        linkedGraph.removeNode("D");

        System.out.println(linkedGraph);
    }

    @Test
    public void removeEdgeTest() {
        HashGraph<String> linkedGraph = new HashGraph<>(true, true);

        linkedGraph
            .addEdge(null, "A")
            .addWeightedEdge("A", "B", 10)
            .addWeightedEdge("A", "C", 5);

        linkedGraph
            .addWeightedEdge("B", "D", 20);
        
        linkedGraph
            .addWeightedEdge("C", "D", 30)
            .addWeightedEdge("C", "E", 25);

        linkedGraph
            .addWeightedEdge("D", "F", 50);

        linkedGraph
            .addWeightedEdge("E", "F", 35);

        System.out.println(linkedGraph);

        linkedGraph.removeEdge("B", "D");

        System.out.println(linkedGraph);
    }

    @Test
    public void getNodeTest() {
        HashGraph<String> hashGraph = new HashGraph<>(true, true);

        hashGraph
            .addNode("A")
            .addWeightedEdge("A", "B", 10)
            .addWeightedEdge("A", "C", 5);

        System.out.println(hashGraph);

        System.out.println(hashGraph.getNodeByValue("A"));
    }

    @Test
    public void nonWeightedTest() {
        HashGraph<String> hashGraph = new HashGraph<>(true, false);

        hashGraph
            .addNode("A")
            .addEdge("A", "B")
            .addEdge("A", "C");

        System.out.println(hashGraph);

        System.out.println(hashGraph.getNodeByValue("A"));
    }

    @Test
    public void BFStest() {
        HashGraph<String> linkedGraph = new HashGraph<>(false, true);

        linkedGraph
            .addNode("A")
            .addWeightedEdge("A", "B", 10)
            .addWeightedEdge("A", "C", 5);

        linkedGraph
            .addWeightedEdge("B", "D", 20);
        
        linkedGraph
            .addWeightedEdge("C", "D", 30)
            .addWeightedEdge("C", "E", 25);

        linkedGraph
            .addWeightedEdge("D", "F", 50);

        linkedGraph
            .addWeightedEdge("E", "F", 35);

        linkedGraph.runBreadthFirstSearch(linkedGraph.getNodeByValue("C"), node -> System.out.println(node));
    }

    @Test
    public void shortestPathBetweenTestUnweighted() {
        HashGraph<String> linkedGraph = new HashGraph<>(true, false);

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

        linkedGraph.removeEdge("C", "E");

        System.out.println(linkedGraph.shortestPathBetween("A", "E"));
    }

    @Test
    public void dfsTest() {
        HashGraph<String> linkedGraph = getUnweightedGraph(true);
        System.out.println(linkedGraph.topoligicalSort("A"));
        
    }

    public HashGraph<String> getUnweightedGraph(boolean isDirecred) {
        HashGraph<String> linkedGraph = new HashGraph<>(isDirecred, false);

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

    public HashGraph<String> getWeightedGraph(boolean isDirecred) {
        HashGraph<String> hashGraph = new HashGraph<>(isDirecred, true);

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
