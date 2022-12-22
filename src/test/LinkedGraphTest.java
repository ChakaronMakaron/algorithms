package test;

import org.junit.Test;

import structures.LinkedGraph;

public class LinkedGraphTest {
    
    @Test
    public void test() {
        LinkedGraph<String> linkedGraph = new LinkedGraph<>(true, true);

        linkedGraph
            .addNode(null, "A")
            .addNode("A", "B", 10)
            .addNode("A", "C", 5);

        linkedGraph
            .addNode("B", "D", 20);
        
        linkedGraph
            .addNode("C", "D", 30)
            .addNode("C", "E", 25);

        linkedGraph
            .addNode("D", "F", 50);

        linkedGraph
            .addNode("E", "F", 35);

        System.out.println(linkedGraph);

        System.out.println("########## DELETING");

        linkedGraph.removeNode("D");

        System.out.println(linkedGraph);

        System.out.println(new Object());
    }
}
