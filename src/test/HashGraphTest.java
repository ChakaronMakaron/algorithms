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
    public void runBFS() {
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
}
