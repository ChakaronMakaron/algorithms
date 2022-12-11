package helper.classes;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class BTree<T extends Comparable<T>> {

    private Node<T> root;

    public BTree() {}

    public BTree(T item) {
        root = new Node<T>(null, item, null, null);
    }
    
    public boolean add(T item) {
        if (isNull(root)) {
            root = new Node<T>(null, item, null, null);
            return true;
        }
        return searchAndAdd(root, item);
    }

    public boolean remove(T item) {
        if (root.value.equals(item)) {
            root = null;
            return true;
        }
        return searchAndRemove(root, item, Branch.ROOT);
    }

    public T minimum() {
        return getMinimalNode(root).value;
    }

    public T maximum() {
        return getMaximalNode(root).value;
    }

    private Node<T> getMinimalNode(Node<T> node) {
        if (isNull(node)) return null;
        if (isNull(node.left)) return node;
        return getMinimalNode(node.left);
    }

    private Node<T> getMaximalNode(Node<T> node) {
        if (isNull(node)) return null;
        if (isNull(node.right)) return node;
        return getMaximalNode(node.right);
    }

    private boolean searchAndAdd(Node<T> node, T item) {
        if (item.compareTo(node.value) < 0) { // If item < node.value
            if (isNull(node.left)) {
                node.left = new Node<T>(node, item, null, null);
                return true;
            } else {
                return searchAndAdd(node.left, item);
            }
        }
        else if (item.compareTo(node.value) > 0) { // If item > node.value
            if (isNull(node.right)) {
                node.right = new Node<T>(node, item, null, null);
                return true;
            } else {
                return searchAndAdd(node.right, item);
            }
        }
        return false;
    }

    private boolean searchAndRemove(Node<T> node, T item, Branch branch) {
        if (isNull(node)) return false;

        if (node.value.equals(item)) {
            if (isNull(node.left) && isNull(node.right)) {
                setParentBranch(node, branch, null);
            }

            else if (nonNull(node.left) && nonNull(node.right)) {
                Node<T> min = getMinimalNode(node.right);
                min.parent.left = null;
                min.right = node.right;
                min.left = node.left;
                min.parent = node.parent;
                setParentBranch(node, branch, min);
            }

            else if (nonNull(node.left) || nonNull(node.right)) {
                Node<T> nonNullChildBranch = nonNull(node.left) ? node.left : node.right;
                setParentBranch(node, branch, nonNullChildBranch);
            }

            return true;
        }

        return searchAndRemove(node.left, item, Branch.LEFT) || searchAndRemove(node.right, item, Branch.RIGHT);
    }

    private void setParentBranch(Node<T> node, Branch branch, Node<T> value) {
        if (branch.equals(Branch.LEFT)) {
            node.parent.left = value;
        }
        if (branch.equals(Branch.RIGHT)) {
            node.parent.right = value;
        }
    }

    @Override
    public String toString() {
        if (isNull(root)) return "{}";
        return root.toString();
    }

    private static class Node<T> {

        private Node<T> parent;
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(Node<T> parent, T value, Node<T> left, Node<T> right) {
            this.parent = parent;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node {value=" + value + ", left=" + left + ", right=" + right + "}";
        }
    }

    private static enum Branch {

        LEFT, RIGHT, ROOT
    }
}
