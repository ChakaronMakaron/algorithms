package structures;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.*;

public class LinkedGraph<T extends Comparable<T>> {

    // private List<T> edges;
    private Map<T, Node<T>> nodes;
    private boolean isDirecred;
    private boolean isWeighted;
    private boolean isRootNodePresent;

    public LinkedGraph(boolean isDirecred, boolean isWeighted) {
        this.isDirecred = isDirecred;
        this.isWeighted = isWeighted;
        this.nodes = new HashMap<>();
    }

    // For not weighted
    public LinkedGraph<T> addNode(T baseNodeValue, T value) {
        return addNode(baseNodeValue, value, null);
    }

    // For weighted
    public LinkedGraph<T> addNode(T baseNodeValue, T value, Integer weight) {
        if (isNull(baseNodeValue) && !isRootNodePresent) {
            nodes.put(value, new Node<>(value));
            isRootNodePresent = true;
            return this;
        }
        if (isNull(baseNodeValue) && isRootNodePresent) {
            throw new IllegalStateException("Root node alredy exists");
        }

        Node<T> baseNode = nodes.get(baseNodeValue);
        if (isNull(baseNode)) {
            throw new IllegalStateException(format("Base node with value '%s' not found", baseNodeValue));
        }

        Node<T> newNode = nodes.get(value);
        if (isNull(newNode)) {
            newNode = new Node<>(value);
        }

        nodes.put(value, newNode);

        Edge<T> linkEdge = new Edge<>(baseNode, newNode, weight);
        newNode.pointedBy.add(linkEdge);
        baseNode.pointsTo.add(linkEdge);

        return this;
    }

    public void removeNode(T value) {
        Node<T> node = nodes.get(value);
        if (isNull(node)) {
            throw new IllegalStateException(format("Node with value '%s' not found", value));
        }

        node.pointedBy.stream()
            .forEach(edge -> edge.source.pointsTo.remove(edge));

        node.pointsTo.stream()
            .forEach(edge -> edge.destination.pointedBy.remove(edge));

        nodes.remove(value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        nodes.forEach((key, node) -> {
            result.append(node + "\n");
            result.append("Pointed by: " + node.pointedBy + "\n");
            result.append("Points to: " + node.pointsTo + "\n");
        });
        return result.toString();
    }

    public boolean isDirecred() {
        return isDirecred;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public boolean isRootNodePresent() {
        return isRootNodePresent;
    }

    private static class Node<T extends Comparable<T>> {
        T value;
        List<Edge<T>> pointsTo;
        List<Edge<T>> pointedBy;

        Node(T value) {
            this.value = value;
            this.pointsTo = new LinkedList<>();
            this.pointedBy = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "Node [" + value + "]";
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node<T> that = (Node<T>) obj;
                return this.value.equals(that.value);
            }
            return false;
        }
    }

    private static class Edge<T extends Comparable<T>> {
        Node<T> source;
        Node<T> destination;
        Integer weight;

        Edge(Node<T> source, Node<T> destination) {
            this(source, destination, null);
        }

        Edge(Node<T> source, Node<T> destination, Integer weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return format("%s --%s--> %s", source, weight, destination);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (obj instanceof Edge) {
                Edge<T> that = (Edge<T>) obj;
                return this.source.equals(that.source) && this.destination.equals(that.destination);
            }
            return false;
        }
    }
}
