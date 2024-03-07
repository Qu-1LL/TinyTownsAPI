package tt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Town {
    private Map<Integer,Tile> vertices;

    public Town () {
        vertices = new HashMap<>();
    }
    public void add (E value) {
        vertices.put(value,new Vertex<>(value));
    }
    public boolean contains (E value) {
        return vertices.containsKey (value);
    }
    public int size () {
        return vertices.size();
    }
    @Override
    public void connectDirected(E a, E b) {
        Vertex<E> vA = vertices.get(a);
        Vertex<E> vB = vertices.get(b);
        vA.connect(vB);   
    }
    @Override
    public void connectUndirected(E a, E b) {
        Vertex<E> vA = vertices.get(a);
        Vertex<E> vB = vertices.get(b);
        vA.connect(vB);
        vB.connect(vA);
    }
    @Override
    public boolean connected(E a, E b) {
        return vertices.get(a).connected(vertices.get(b));
    }
    @Override 
    public String toString () {
        return vertices.toString();
    }
    public static void main (String[] Args) {
        AdjacencyGraph<String> graph = new AdjacencyGraph<>();
        String S = null;
        String T = null;
        graph.add(S);
        graph.add(T);
        System.out.println(graph);
    }
}
