package algorithms.search;

import static java.util.Objects.nonNull;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import helper.classes.Node;
import helper.classes.TreeProvider;

public class Search_BFS {
    
    public static Node breadthFirstSearch(Node root, int valueToFind) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> seen = new HashSet<>();

        queue.add(root);
        seen.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.value == valueToFind) {
                return current;
            }
            for (Node childNode : current.getChildren()) {
                if (nonNull(childNode)) {
                    queue.add(childNode);
                    seen.add(childNode);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(breadthFirstSearch(TreeProvider.getTree(), 17));
    }
}
