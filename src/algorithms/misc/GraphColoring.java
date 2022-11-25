package algorithms.misc;

import static java.util.Objects.nonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import helper.classes.Node;
import helper.classes.TreeProvider;

public class GraphColoring {
    
    public static void graphColoring(Node root) {
        Stack<Node> stack = new Stack<>();
        Set<Node> seen = new HashSet<>();

        root.color = 1;

        stack.push(root);
        seen.add(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            
            for (Node childNode : current.getChildren()) {
                if (nonNull(childNode)) {
                    childNode.color = pickColorExcept(current.color);
                    stack.push(childNode);
                    seen.add(childNode);
                }
            }
        }
    }

    public static int pickColorExcept(int except) {
        return List.of(1, 2, 3).stream()
            .filter(c -> !c.equals(except))
            .findFirst().get();
    }

    public static void main(String[] args) {
        Node root = TreeProvider.getTree();
        graphColoring(root);
        System.out.println(root);
    }
}
