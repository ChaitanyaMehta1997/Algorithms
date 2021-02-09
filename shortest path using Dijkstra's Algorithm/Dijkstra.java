//package Algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dijkstra implements Comparator<Node> {
	static HashMap<Node, Integer> distance = new HashMap<>();
	
	 
	
	static int start =1;
	static int finish =4;
	
	@Override
	public int compare(Node o1, Node o2) {
		
		if(o1.distance>o2.distance) {
			return -1;
			
		}
		else if(o1.distance<o2.distance) {
			return 1;
		}
		return 0;
	}
	 
	@SuppressWarnings("resource")
	public static void create_graph(int start, int finish) throws IOException {
		BufferedReader br = null;
//				br = new BufferedReader(new InputStreamReader(
//				new FileInputStream("C:\\Users\\13155\\eclipse-new_workspace\\Algo\\src\\Algo\\dijkstratest.txt"),
//				Charset.forName("UTF-8")));

		br = new BufferedReader(new InputStreamReader(
				System.in,
				Charset.forName("UTF-8")));
		
		String Number = br.readLine();
		String numstore[] = Number.split(" ");
		int num = Integer.parseInt(numstore[0]);
		Node Nodeobjectstore[] = new Node[num + 1];
		Node Nodeobjectfinalstore[] = new Node[num + 1];
		HashMap<Node, ArrayList<Node>> graph = new HashMap<>();
		PriorityQueue<Node> pq=
                new PriorityQueue<Node>(num+1,new Dijkstra_compare());
		
		
		while (br.ready()) {
			String node = br.readLine();

			String NodeStore[] = node.split(":",2);
			
			//String Connected_value_i[] = (NodeStore[1].strip()).split(":");
			
			String Connected_value_i[] = (NodeStore[1].strip()).split(" ");
			
			
			String regex = ".*Node\\[(\\d.*)\\]:\\s(.*)";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(node);

			String key = "";
			if (m.find()) {

				key = m.group(1);
			}
			// Value of the curr node
			
			int main_node_index = Integer.parseInt(key);
			int weight = 0;

			Node main_node = new Node(main_node_index,weight);
			if (Nodeobjectstore[main_node_index] == null) {
				Nodeobjectstore[main_node_index] = main_node;

			}

			for (int i = 0; i < Connected_value_i.length; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append(Connected_value_i[i]);
				String str = sb.toString();
				String Connected_node_final []= str.split(":");
				if(Connected_node_final[0].equals("") || Connected_node_final[0].equals("#") ){
					continue;
				}
				int connected_node_index = Integer.parseInt(Connected_node_final[0]);
				weight = Integer.parseInt(Connected_node_final[1]);
				Node connected_node = new Node(connected_node_index,weight);
	//			if ((Nodeobjectstore[connected_node_index] == null)) {
		//			Node connected_node = new Node(connected_node_index,weight);
			//		Nodeobjectstore[connected_node_index] = connected_node;
				//}

				// AddADJ to current node
				Nodeobjectstore[main_node_index].addAdj(connected_node);

				// Add the reverse edge
				//Nodeobjectstore[connected_node_index].addEdge(Nodeobjectstore[main_node_index]);

			}
	

		}
		int print_count = 0;
	
		
		HashSet<Node> visited = new HashSet<>();
		
		// ---------------------------------------------------------------------------------------------------
			
			for(int i=1;i<Nodeobjectstore.length;i++) {
				if(Nodeobjectstore[i].id == start) {
					Nodeobjectstore[i].distance = 0;
					pq.add(Nodeobjectstore[i]);
				}
				else {
					Nodeobjectstore[i].distance =  Integer.MAX_VALUE;
					//pq.add(Nodeobjectstore[i]);
				}
				
			}
			Node smallest = null;
			while(pq.size()>0) {
				if(visited.size()==Nodeobjectstore.length-1) {
				break;
				}
				if(pq.peek()==null) {
					pq.poll();
				}
				smallest = Nodeobjectstore[pq.poll().id];
				
				visited.add(smallest);
				Nodeobjectfinalstore[smallest.id] = smallest; 
				
				
				for(Node N : smallest.adj) {
					
					
					
					int Candidate = smallest.distance + N.weight;
					
					if(Candidate < Nodeobjectstore[N.id].distance) {
						
						Nodeobjectstore[N.id].distance = Candidate;
						Nodeobjectstore[N.id].parent = smallest;
						pq.add(N);
						
					}
				}
				}
			
				int count=0;
				
				for(Node N: Nodeobjectfinalstore) {
					
					if (count == 0) {
						count = 1;
						continue;
					}
					if(N!=null) {
					if(N.parent != null) {	
					System.out.println(N.id +" dist: "+ N.distance+" path: "
							+N.parent.id);
				}
					else {
						System.out.println(N.id +" dist: "+ N.distance+" path: "
								+"null");
						
						}
					}
				}
			}
				
				
			
				
		
			
			
		
	
	
	public static void main(String args[]) throws IOException {
		if(args.length>0) {
			start = Integer.parseInt(args[0]);
			}
		create_graph(start ,finish);
		
		
	}

	

}
