package structures.graphs;

public interface Graph<T extends Comparable<T>> {

    Node<T> getNode(T value);
    void removeNode(T value);
    
    public interface Node<T extends Comparable<T>> {

    }

    public interface Edge<T extends Comparable<T>> {

    }
}
