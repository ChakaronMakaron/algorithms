package algorithms.search;

import static java.util.Objects.nonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import helper.classes.Node;
import helper.classes.TreeProvider;

public class Search_DFS {
    
    public static Node depthFirstSearch(Node root, int valueToFind) {
        Stack<Node> stack = new Stack<>();
        Set<Node> seen = new HashSet<>();

        stack.push(root);
        seen.add(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.value == valueToFind) {
                return current;
            }
            for (Node childNode : current.getChildren()) {
                if (nonNull(childNode)) {
                    stack.push(childNode);
                    seen.add(childNode);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(depthFirstSearch(TreeProvider.getTree(), 3));
    }
}
