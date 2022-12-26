package structures;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Collections.reverse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class HashGraph<T extends Comparable<T>> {

    private Map<T, Node<T>> nodes;
    private boolean isDirecred;
    private boolean isWeighted;
    private Map<Node<T>, Node<T>> childrenParentsBFS;

    public HashGraph(GraphDirection isDirecred, GraphWeighting isWeighted) {
        this(isDirecred.value, isWeighted.value);
    }

    public HashGraph(boolean isDirecred, boolean isWeighted) {
        this.isDirecred = isDirecred;
        this.isWeighted = isWeighted;
        this.nodes = new HashMap<>();
        this.childrenParentsBFS = new HashMap<>();
    }

    public HashGraph<T> addNode(T value) {
        if (isNull(nodes.get(value))) {
            nodes.put(value, new Node<>(value));
        }
        return this;
    }

    public Node<T> getNodeByValue(T value) {
        return nodes.get(value);
    }

    // For not weighted
    public HashGraph<T> addEdge(T sourceNodeValue, T targetNodeValue) {
        return addWeightedEdge(sourceNodeValue, targetNodeValue, null);
    }

    // For weighted
    public HashGraph<T> addWeightedEdge(T sourceNodeValue, T targetNodeValue, Integer weight) {
        // If graph is weighted and weight is null (no weight)
        if (isWeighted && isNull(weight)) {
            throw new UnsupportedOperationException("Can't add edge with no weight in weighted graph");
        }
        // If graph is non-weighted but weight is present
        if (!isWeighted && nonNull(weight)) {
            throw new UnsupportedOperationException("Can't add edge with weight in non-weighted graph");
        }

        if (isNull(targetNodeValue) || isNull(sourceNodeValue)) {
            throw new IllegalArgumentException(format("Source node value or target node value is null. Base node: '%s', new node value: '%s'",
                sourceNodeValue, targetNodeValue));
        };
        
        Node<T> sourceNode = nodes.get(sourceNodeValue);
        if (isNull(sourceNode)) {
            throw new IllegalStateException(format("Source node with value '%s' not found", sourceNodeValue));
        }

        Node<T> targetNode = nodes.get(targetNodeValue);
        if (isNull(targetNode)) {
            targetNode = new Node<>(targetNodeValue);
        }

        nodes.put(targetNodeValue, targetNode);

        Edge<T> linkEdge = new Edge<>(sourceNode, targetNode, isDirecred, weight);
        targetNode.pointedBy.add(linkEdge);
        sourceNode.pointsTo.add(linkEdge);

        return this;
    }

    public T removeNode(T nodeValue) {
        Node<T> node = nodes.get(nodeValue);
        if (isNull(node)) return null;

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

        boolean isFoundInDirectSearch = false;
        Edge<T> linkEdge = sourceNode.pointsTo.stream()
            .filter(edge -> edge.equalsByNodes(sourceNode, destinationNode))
            .findFirst()
            .orElse(null);
        isFoundInDirectSearch = nonNull(linkEdge);

        // For non-directed graph when direction of arguments (nodes) might not be kept
        boolean isFoundInReversedSearch = false;
        if (isNull(linkEdge) && !isDirecred) {
            linkEdge = sourceNode.pointedBy.stream()
            .filter(edge -> edge.equalsByNodes(sourceNode, destinationNode))
            .findFirst()
            .orElse(null);
            isFoundInReversedSearch = nonNull(linkEdge);
        }

        if (nonNull(linkEdge)) {
            if (isFoundInDirectSearch) {
                sourceNode.pointsTo.remove(linkEdge);
                destinationNode.pointedBy.remove(linkEdge);
            }
            if (isFoundInReversedSearch) {
                sourceNode.pointedBy.remove(linkEdge);
                destinationNode.pointsTo.remove(linkEdge);
            }
            return linkEdge.weight;
        }
        return null;
    }

    public boolean isDirecred() {
        return isDirecred;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public boolean isEmpty() {
        return nodes.size() == 0;
    }

    public HashGraph<T> getMinimalSpanningTree(Node<T> startNode) {
        if (isDirecred) throw new UnsupportedOperationException("Not implemented for directed graphs");
        if (!isWeighted) throw new UnsupportedOperationException("Minimal spanning tree is not supported for non-weigted graphs");

        // Init distances
        Map<Node<T>, Integer> nodesDistances = new HashMap<>();
        nodes.values().forEach(node -> nodesDistances.put(new Node<>(node), Integer.MAX_VALUE));

        // Init parents
        Map<Node<T>, Node<T>> childrenParents = new HashMap<>();
        
        HashGraph<T> minTree = new HashGraph<>(isDirecred, isWeighted);
        if (isEmpty()) return minTree;

        nodes.values().forEach(node -> minTree.addNode(node.value));

        Set<Node<T>> visitedNodes = new HashSet<>();

        Node<T> currentNode = startNode;
        nodesDistances.put(currentNode, 0);
        childrenParents.put(currentNode, null);

        while (!visitedNodes.contains(currentNode)) {
            visitedNodes.add(currentNode);

            Set<Edge<T>> adjacentEdges = new HashSet<>();
            adjacentEdges.addAll(currentNode.pointsTo);
            adjacentEdges.addAll(currentNode.pointedBy.stream()
                .map(edge -> edge.getReversedEdge())
                .collect(Collectors.toSet()));

            // Updating distances to newly discovered nodes
            for (Edge<T> edge : adjacentEdges) {
                Node<T> adjacentNode = edge.destination;
                Integer adjacentNodeDistance = nodesDistances.get(adjacentNode);
                if (adjacentNodeDistance > edge.weight && !visitedNodes.contains(adjacentNode)) {
                    nodesDistances.put(adjacentNode, edge.weight);
                    childrenParents.put(adjacentNode, currentNode);
                }
            }

            // Finding next min distance node
            int nextMinDistance = Integer.MAX_VALUE;
            for (Entry<Node<T>, Integer> entry : nodesDistances.entrySet()) {
                if (!visitedNodes.contains(entry.getKey()) && entry.getValue() < nextMinDistance) {
                    nextMinDistance = entry.getValue();
                    currentNode = entry.getKey();
                }
            }
        }

        // Building min tree
        childrenParents.forEach((child, parent) -> {
            if (nonNull(parent)) {
                minTree.addWeightedEdge(parent.value, child.value, nodesDistances.get(child));
            }
        });

        return minTree;
    }

    public List<Node<T>> topoligicalSort(T startNodeValue) {
        if (!isDirecred) throw new UnsupportedOperationException("Topological sort is not supported by non-directed graphs");

        Node<T> startNode = nodes.get(startNodeValue);
        if (isNull(startNode)) return emptyList();

        List<Node<T>> result = new ArrayList<>();
        runDFS(startNode, result, new HashSet<>());
        reverse(result);

        return result;
    }

    // For topological sort
    private void runDFS(Node<T> currentNode, List<Node<T>> topologicalOrder, Set<Node<T>> processedNodes) {
        if (processedNodes.contains(currentNode)) return;
        processedNodes.add(currentNode);

        Set<Node<T>> neighbouringNodes = currentNode.pointsTo
            .stream()
            .map(edge -> edge.destination)
            .collect(Collectors.toSet());

        neighbouringNodes.stream()
            .forEach(node -> runDFS(node, topologicalOrder, processedNodes));

        topologicalOrder.add(currentNode);
    }

    public void runDepthFirstSearchStack(Node<T> startNode, Consumer<Node<T>> consumer) {
        if (isNull(startNode)) return;

        Set<Node<T>> processedNodes = new HashSet<>();
        Set<Node<T>> discoveredNodes = new HashSet<>();

        Stack<Node<T>> stack = new Stack<>();
        stack.add(startNode);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (processedNodes.contains(currentNode)) continue;

            processedNodes.add(currentNode);
            discoveredNodes.add(currentNode);

            consumer.accept(currentNode);

            Set<Node<T>> neighbouringNodes = currentNode.pointsTo
                .stream()
                .map(edge -> edge.destination)
                .collect(Collectors.toSet());

            if (!isDirecred) {
                neighbouringNodes.addAll(currentNode.pointedBy
                    .stream()
                    .map(edge -> edge.source)
                    .collect(Collectors.toSet())
                );
            }

            stack.addAll(neighbouringNodes);
        }
    }

    public void runBreadthFirstSearch(Node<T> startNode) {
        runBreadthFirstSearch(startNode, node -> {});
    }

    // Fills map of children and parents 'childrenParents'
    // this map is used to find shortest path between nodes in NON-WEIGHTED graphs only
    public void runBreadthFirstSearch(Node<T> startNode, Consumer<Node<T>> consumer) {
        if (isNull(startNode)) return;

        childrenParentsBFS.clear();
        childrenParentsBFS.put(startNode, null);

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(startNode);

        Set<Node<T>> processedNodes = new HashSet<>();
        Set<Node<T>> discoveredNodes = new HashSet<>();

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            if (processedNodes.contains(currentNode)) continue;
            processedNodes.add(currentNode);
            discoveredNodes.add(currentNode);

            consumer.accept(currentNode);

            Set<Node<T>> neighbouringNodes = currentNode.pointsTo
                .stream()
                .map(edge -> edge.destination)
                .collect(Collectors.toSet());

            if (!isDirecred) {
                neighbouringNodes.addAll(currentNode.pointedBy
                    .stream()
                    .map(edge -> edge.source)
                    .collect(Collectors.toSet())
                );
            }
            
            neighbouringNodes.stream()
                .filter(node -> !discoveredNodes.contains(node))
                .forEach(node -> childrenParentsBFS.put(node, currentNode));

            discoveredNodes.addAll(neighbouringNodes);

            queue.addAll(neighbouringNodes);
        }
    }
    
    public List<Node<T>> shortestPathBetween(T startNodeValue, T targetNodeValue) {
        Node<T> startNode = nodes.get(startNodeValue);
        Node<T> targetNode = nodes.get(targetNodeValue);

        if (isWeighted) {
            return null; // TODO Dijkstra's weighted path
        }
        return getShortestPathNonWeighted(startNode, targetNode);
    }

    private List<Node<T>> getShortestPathNonWeighted(Node<T> startNode, Node<T> targetNode) {
        // Return empty
        if (isNull(startNode) || isNull(targetNode)) return emptyList();
        
        List<Node<T>> path = new ArrayList<>();
        path.add(targetNode);

        if (startNode.equals(targetNode)) return path;

        runBreadthFirstSearch(startNode);

        // Gets parent of target node
        Node<T> nextNode = childrenParentsBFS.get(targetNode);

        // While we have not reached start node
        while (true) {
            if (isNull(nextNode)) return emptyList();
            path.add(nextNode);
            if (nextNode.equals(startNode)) {
                reverse(path);
                return path;
            }
            nextNode = childrenParentsBFS.get(nextNode);
        }
    }

    /*
    private Set<Node<T>> getAllNeighbours(Node<T> node) {
        Set<Node<T>> result = new HashSet<>();
        result.addAll(
            node.pointsTo.stream()
                .map(edge -> edge.destination)
                .collect(Collectors.toSet())
        );
        result.addAll(
            node.pointedBy.stream()
                .map(edge -> edge.source)
                .collect(Collectors.toSet())
        );
        return result;
    }
    */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Nodes: " + nodes + "\n");
        nodes.forEach((key, node) -> {
            result.append(node + "\n");
            result.append("Pointed by: " + node.pointedBy + "\n");
            result.append("Points to: " + node.pointsTo + "\n");
        });
        return result.toString();
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

        private Node(Node<T> anotherNode) {
            this.value = anotherNode.value;
            this.pointsTo = new LinkedList<>(anotherNode.pointsTo);
            this.pointedBy = new LinkedList<>(anotherNode.pointedBy);
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

        public boolean isLone() {
            return pointedBy.isEmpty() && pointsTo.isEmpty();
        }

        @Override
        public String toString() {
            return "Node \"" + value + "\"";
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

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {
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

        private Edge<T> getReversedEdge() {
            return new Edge<>(destination, source, isDirecred, weight);
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

        @Override
        public int compareTo(Edge<T> that) {
            return this.weight - that.weight;
        }
    }

    public enum GraphDirection {
        DIRECTED(true),
        NON_DIRECTED(false);
        private boolean value;
        private GraphDirection(boolean value) {
            this.value = value;
        }
        public boolean value() {
            return value;
        }
    }

    public enum GraphWeighting {
        WEIGHTED(true),
        NON_WEIGHTED(false);
        private boolean value;
        private GraphWeighting(boolean value) {
            this.value = value;
        }
        public boolean value() {
            return value;
        }
    }
}
