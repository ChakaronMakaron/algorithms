package structures.graphs;

public interface DirectedGraph<T extends Comparable<T>> extends Graph<T> {
    
    int shortestPathTo();
}
