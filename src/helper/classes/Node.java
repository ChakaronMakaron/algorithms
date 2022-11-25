package helper.classes;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

public class Node {
    
    public int value;
    public Node right;
    public Node left;
    public int color;

    public Node(int value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }

    // @Override
    // public String toString() {
    //     return "Node [value=" + value + ", right=" + right + ", left=" + left + ", color=" + color + "]";
    // }

    @Override
    public String toString() {
        return "(value=" + value + ", right=" + (isNull(right) ? 0 : right.value) + ", left=" + (isNull(left) ? 0 : left.value) + ")";
    }
}
