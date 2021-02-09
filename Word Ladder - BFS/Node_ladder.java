//package Algo;

import java.util.*;

public class Node_ladder {

    public int id;             // node number
    public int distance;          // BFS layer number
    public Set<Node_ladder> adj;      // adjacent nodes
    public  int weight;
    public Node_ladder parent;
    public String word;
    public Node_ladder( int id ,int weight,String word) {
        this.id = id;
        this.weight = weight;
        this.word = word;
        adj = new LinkedHashSet<Node_ladder>();
        distance = Integer.MAX_VALUE;   
        parent = null;
    }

    // add the given node to the adjacency list of this node
    // @ param node  the node to add
    public void addAdj( Node_ladder node ) {
        if ( this.id == node.id )
            throw new RuntimeException("loops not allowed");
        adj.add( node );
    }

    // add the given node to the adjacency set of this node
    //   and add this node to the adjacency set of the given node
    // @param node  the node to add
    public void addEdge( Node_ladder node ) {
        addAdj( node );      // add node to this adjacency set
        node.addAdj( this ); // and this to node's adjacency set
    }

    public String toString() {
        String str = "Node["+id+"]:";
        for (Node_ladder node: adj)
            str += " "+node.id;
        return str;
    }

}
