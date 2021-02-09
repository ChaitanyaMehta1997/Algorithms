//package Algo;

import java.util.*;

public class Node {

    public int id;             // node number
    public int distance;          // BFS layer number
    public Set<Node> adj;      // adjacent nodes
    public  int weight;
    public Node parent;
    public Node( int id ,int weight) {
        this.id = id;
        this.weight = weight;
        adj = new LinkedHashSet<Node>();
        distance = Integer.MAX_VALUE;   
        parent = null;
    }

    // add the given node to the adjacency list of this node
    // @ param node  the node to add
    public void addAdj( Node node ) {
        if ( this.id == node.id )
            throw new RuntimeException("loops not allowed");
        adj.add( node );
    }

    // add the given node to the adjacency set of this node
    //   and add this node to the adjacency set of the given node
    // @param node  the node to add
    public void addEdge( Node node ) {
        addAdj( node );      // add node to this adjacency set
        node.addAdj( this ); // and this to node's adjacency set
    }

    public String toString() {
        String str = "Node["+id+"]:";
        for (Node node: adj)
            str += " "+node.id;
        return str;
    }

}
