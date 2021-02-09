//package Algo;

import java.util.Comparator;

class av implements Comparator<Node_ladder>{ 
    
    public int compare(Node_ladder s1, Node_ladder s2) { 
        if (s1.distance < s2.distance) 
            return -1; 
        else if (s1.distance > s2.distance) 
            return 1; 
                        return 0; 
        } 
} 