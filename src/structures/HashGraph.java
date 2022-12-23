package structures;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashGraph<T extends Comparable<T>> {

    private Map<T, Node<T>> nodes;
    private boolean isDirecred;
    private boolean isWeighted;

    public HashGraph(boolean isDirecred, boolean isWeighted) {
        this.isDirecred = isDirecred;
        this.isWeighted = isWeighted;
        this.nodes = new HashMap<>();
    }

    public Node<T> getNodeByValue(T value) {
        return nodes.get(value);
    }

    // For not weighted
    public HashGraph<T> addEdge(T baseNodeValue, T value) {
        return addWeightedEdge(baseNodeValue, value, null);
    }

    // For weighted
    public HashGraph<T> addWeightedEdge(T baseNodeValue, T value, Integer weight) {
        if (isNull(value)) return this;
        // Graph is non-weighted but weight is present
        if (!isWeighted && nonNull(weight)) {
            throw new UnsupportedOperationException("Can't add edge with weight in non-weighted graph");
        }
        // Graph is weighted and weight is null (no weight)
        if (isNull(weight) && isWeighted) {
            throw new UnsupportedOperationException("Can't add edge with 'null' weight in weighted graph");
        }
        // If adding lone node
        if (isNull(baseNodeValue)) {
            nodes.put(value, new Node<>(value));
            return this;
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

        Edge<T> linkEdge = new Edge<>(baseNode, newNode, isDirecred, weight);
        newNode.pointedBy.add(linkEdge);
        baseNode.pointsTo.add(linkEdge);

        return this;
    }

    public T removeNode(T nodeValue) {
        Node<T> node = nodes.get(nodeValue);
        if (isNull(node)) {
            throw new IllegalStateException(format("Node with value '%s' not found", nodeValue));
        }

        node.pointedBy.stream()
            .forEach(edge -> edge.source.pointsTo.remove(edge));

        node.pointsTo.stream()
            .forEach(edge -> edge.destination.pointedBy.remove(edge));

        nodes.remove(nodeValue);

        return node.value;
    }

    public Integer removeEdge(T sourceNodeValue, T destinationNodeValue) {
        Node<T> sourceNode = nodes.get(sourceNodeValue);
        Node<T> destinationNode = nodes.get(destinationNodeValue);
        if (isNull(sourceNode) || isNull(destinationNode)) return null;

        Edge<T> edgeBetween = sourceNode.pointsTo.stream()
            .filter(edge -> edge.equalsByNodes(sourceNode, destinationNode))
            .findFirst()
            .orElse(null);

        if (nonNull(edgeBetween)) {
            sourceNode.pointsTo.remove(edgeBetween);
            destinationNode.pointedBy.remove(edgeBetween);
            return edgeBetween.weight;
        }
        return null;
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

    public static class Node<T extends Comparable<T>> {
        private T value;
        private List<Edge<T>> pointsTo;
        private List<Edge<T>> pointedBy;

        private Node(T value) {
            this.value = value;
            this.pointsTo = new LinkedList<>();
            this.pointedBy = new LinkedList<>();
        }

        public T getValue() {
            return value;
        }

        public List<Edge<T>> getPointsTo() {
            return new LinkedList<>(pointsTo);
        }

        public List<Edge<T>> getPointedBy() {
            return new LinkedList<>(pointedBy);
        }

        @Override
        public String toString() {
            return "\"" + value + "\"";
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

    public static class Edge<T extends Comparable<T>> {
        private Node<T> source;
        private Node<T> destination;
        private boolean isDirecred;
        private Integer weight;

        private Edge(Node<T> source, Node<T> destination, boolean isDirecred) {
            this(source, destination, isDirecred, null);
        }

        private Edge(Node<T> source, Node<T> destination, boolean isDirecred, Integer weight) {
            this.source = source;
            this.destination = destination;
            this.isDirecred = isDirecred;
            this.weight = weight;
        }

        public Node<T> getSource() {
            return source;
        }

        public Node<T> getDestination() {
            return destination;
        }

        public Integer getWeight() {
            return weight;
        }

        public boolean isDirecred() {
            return isDirecred;
        }

        @Override
        public String toString() {
            return format("%s --%s--%s %s", source, isNull(weight) ? "" : weight, isDirecred ? ">" : "", destination);
        }

        public boolean equalsByNodes(Node<T> nodeA, Node<T> nodeB) {
            boolean straightOrderMatch = this.source.equals(nodeA) && this.destination.equals(nodeB);
            boolean reversedOrderMatch = this.source.equals(nodeB) && this.destination.equals(nodeA);
            return straightOrderMatch || reversedOrderMatch;
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
