//package Algo;
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

public class WordLadder implements Comparator<Node_ladder>{

	static String start="NotSet";
	static int finish =4;
	
	@Override
	public int compare(Node_ladder o1, Node_ladder o2) {
		
		if(o1.distance>o2.distance) {
			return -1;
			
		}
		else if(o1.distance<o2.distance) {
			return 1;
		}
		return 0;
	}
	 
	@SuppressWarnings("resource")
	public static void create_graph(String start, int finish) throws IOException {
		BufferedReader br = null;
				
		br = new BufferedReader(new InputStreamReader(
				System.in,
				Charset.forName("UTF-8")));
		
		String Number = br.readLine();
		String numstore[] = Number.split(" ");
		int num = Integer.parseInt(numstore[0]);
		Node_ladder Nodeobjectstore[] = new Node_ladder[num + 1];
		Node_ladder Nodeobjectfinalstore[] = new Node_ladder[num + 1];
		//Node_ladder array3[] = new Node_ladder[num + 1];
		HashMap<Node, ArrayList<Node>> graph = new HashMap<>();
		PriorityQueue<Node_ladder> pq=
                new PriorityQueue<Node_ladder>(num+1,new av());
		
		
		while (br.ready()) {
			String node = br.readLine();

			String NodeStore[] = node.split(":",2);
			
			String Connected_value_i[] = (NodeStore[1].strip()).split(" ");
			String word = Connected_value_i[0];
					    
			
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
			
			Node_ladder main_node = new Node_ladder(main_node_index,weight,word);
			
			if (Nodeobjectstore[main_node_index] == null) {
				Nodeobjectstore[main_node_index] = main_node;

			}

			for (int i = 1; i < Connected_value_i.length; i++) {
			
				StringBuffer sb = new StringBuffer();
				sb.append(Connected_value_i[i]);
				String str = sb.toString();
				String Connected_node_final []= str.split(":");
				int connected_node_index = Integer.parseInt(Connected_node_final[0]);
				weight = Integer.parseInt(Connected_node_final[1]);
				Node_ladder connected_node = new Node_ladder(connected_node_index,weight,word);
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
		if(start.equals("NotSet")) {
			start = Nodeobjectstore[1].word;
		}
		
		HashSet<Node_ladder> visited = new HashSet<>();
		
		// ---------------------------------------------------------------------------------------------------
			
			for(int i=1;i<Nodeobjectstore.length;i++) {
				if(Nodeobjectstore[i].word.equals(start)) {
					Nodeobjectstore[i].distance = 0;
					pq.add(Nodeobjectstore[i]);
				}
				else {
					Nodeobjectstore[i].distance =  Integer.MAX_VALUE;
					//pq.add(Nodeobjectstore[i]);
				}
				
			}
			Node_ladder smallest = null;
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
				
				
				for(Node_ladder N : smallest.adj) {
					
					int Candidate = smallest.distance + N.weight;
					
					if(Candidate < Nodeobjectstore[N.id].distance) {
						
						Nodeobjectstore[N.id].distance = Candidate;
						Nodeobjectstore[N.id].parent = smallest;
						pq.add(N);
						
					}
				}
				}
			
				int count=0;
				int startcount=0;
				int dist=0;
				HashSet<String> visited2 = new HashSet<>();
				String finalstore[] = new String[num];
				for(Node_ladder N: Nodeobjectfinalstore) {
					
					visited2 = new HashSet<>();
					if(N == null && count!=0) {
						continue;
					}
					if (count == 0) {
						count = 1;
						continue;
					}
					if(!N.word.equals(start) && startcount ==0 ) {
						
						continue;
					}
					if(N.word.equals(start) && startcount ==0) {
						startcount=1;
					}
					smallest = N;
					int  finalstorecount=0;
					while(smallest!=null) {
					if(smallest != null) {	
						if(!visited2.contains(smallest.word)) {
						finalstore[finalstorecount] = smallest.word;
						visited2.add(smallest.word);
						finalstorecount+=1;
						}
				}
					
					smallest = smallest.parent;
					
					}
					
					System.out.print(finalstorecount-1+":");
					for(String S:finalstore) {
						if(S != null) {
						
						System.out.print(S+"<");
						}
						else {
							break;
						}
					}
					
					System.out.println("");
					finalstore = new String[num];
					
				}
				
				
				
			}
				
		
			
			
		
	
	
	public static void main(String args[]) throws IOException {
		if(args.length>0) {
			start = args[0];
			}
		
		create_graph(start ,finish);
		
		
	}

	

}
