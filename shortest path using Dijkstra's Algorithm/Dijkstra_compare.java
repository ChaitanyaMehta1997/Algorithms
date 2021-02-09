//package Algo;

import java.util.Comparator;

class Dijkstra_compare implements Comparator<Node>{ 
    
    public int compare(Node s1, Node s2) { 
        if (s1.distance < s2.distance) 
            return -1; 
        else if (s1.distance > s2.distance) 
            return 1; 
                        return 0; 
        }

	 
} 