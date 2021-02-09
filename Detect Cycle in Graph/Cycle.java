//package Algo;


/*
 * @author: Chaitanya Mehta
 * 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cycle {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(
				System.in,
				Charset.forName("UTF-8")));
//		br = new BufferedReader(new InputStreamReader(
//				new FileInputStream("C:\\Users\\13155\\eclipse-new_workspace\\Algo\\src\\Algo\\Node_test.txt"),
//				Charset.forName("UTF-8")));

		String Number = br.readLine();
		int num = Integer.parseInt(Number);
		Node Nodeobjectstore[] = new Node[num + 1];
		HashMap<Integer, Integer> graph = new HashMap<>();
		
		/*
		 * Creating the graph
		 */
		while (br.ready()) {
			String node = br.readLine();

			String NodeStore[] = node.split(":");

			String Connected_value[] = (NodeStore[1].strip()).split(" ");

			String regex = ".*Node\\[(\\d.*)\\]:\\s(.*)";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(node);

			String key = "";
			if (m.find()) {

				key = m.group(1);
			}
			// Value of the curr node
			int main_node_index = Integer.parseInt(key);

			Node main_node = new Node(main_node_index);
			if (Nodeobjectstore[main_node_index] == null) {
				Nodeobjectstore[main_node_index] = main_node;

			}

			for (int i = 0; i < Connected_value.length; i++) {
				int connected_node_index = Integer.parseInt(Connected_value[i]);

				if ((Nodeobjectstore[connected_node_index] == null)) {
					Node connected_node = new Node(connected_node_index);
					Nodeobjectstore[connected_node_index] = connected_node;
				}

				// AddADJ to current node
				Nodeobjectstore[main_node_index].addAdj(Nodeobjectstore[connected_node_index]);

				// Add the reverse edge
				Nodeobjectstore[connected_node_index].addEdge(Nodeobjectstore[main_node_index]);

			}

		}
		/*
		 * Printing the graph
		 */
		
		int print_count = 0;
		System.out.println(num);
		for (Node r : Nodeobjectstore) {
			if (print_count == 0) {
				print_count = 1;
				continue;
			}
			System.out.println(r);
		}
		
		boolean is_cyclic = false;
		HashSet<Node> Red = new HashSet<>();
//		
		HashSet<Node> Blue = new HashSet<>();
//		
		ArrayList<Node> queue = new ArrayList<>();
		ArrayList<Node> Mapstore = new ArrayList<>();
		ArrayList<Node> queuea = new ArrayList<>();
//
//		
//		
//		HashSet<Node> visited = new HashSet<>();

		// HashMap<Node, ArrayList<Node>> Map = new HashMap<>();
		int count = 0;
		int comp = 1;
		HashSet<Node> visited = new HashSet<>();

		for (Node N : Nodeobjectstore) {
			/*
			 * The nodes will be sorted in 2 sets Red and blue. 
			 * If intersection of Red and Blue is Null then they are bipartite else not
			 * 
			 * Mapstore keeps track of the parents of each node
			 */

			Red = new HashSet<>();

			Blue = new HashSet<>();

			queue = new ArrayList<>();
			Mapstore = new ArrayList<>();
			queuea = new ArrayList<>();

			HashMap<Node, ArrayList<Node>> Map = new HashMap<>();

			if (count == 0) {
				count = 1;
				continue;
			}
			if (!visited.contains(N)) {
				System.out.println(" ");

				System.out.println("connected component " + comp);


				comp += 1;
				int index = 0;
				Red = new HashSet<>();
				Blue = new HashSet<>();
				Map.put(N, null);
				Red.add(N);
				queue.add(N);
				while (queue.size() > 0) {
					Node Curr = queue.remove(0);

					if (!visited.contains(Curr)) {
						System.out.print(Curr.id + "(" + Curr.layer + ")" + " ");
					}
					// adding to set set only if its parent is not already present
					if (Map.get(Curr) != null) {
						for (Node s : Map.get(Curr)) {
							if (!Red.contains(s)) {
								Red.add(Curr);
							} else {
								Blue.add(Curr);
							}
						}
					}
					visited.add(Curr);

					Iterator iter = Curr.adj.iterator();
					while (iter.hasNext()) {
						Mapstore = new ArrayList<>();

						Object x = iter.next();

						if (!Map.containsKey((Node) x)) {
							Mapstore.add(Curr);
							Map.put((Node) x, Mapstore);
						} else {
							Mapstore = Map.get((Node) x);
							if (Map.get((Node) x) != null) {
								Mapstore.add(Curr);
								Map.put((Node) x, Mapstore);

							}
						}
						if (!visited.contains(x)) {
							((Node) x).layer = Curr.layer + 1;
							queue.add((Node) x);

						}
						/*
						 * Checking if cycle is present. i.e the node is not its parent and is already
						 * visited
						 */
						if (Map.get(Curr) != null) {
							for (Node s : Map.get(Curr)) {
								if (x != s && visited.contains(x)) {
									is_cyclic = true;
								}
							}
						}

					}

				}

				/*
				 * Computing intersection of Red and blue
				 */
				
				Red.retainAll(Blue);

				if (Red.size() > 0) {
					System.out.println("");
					System.out.println("not bipartite");

				} else {
					System.out.println("");

					System.out.println("bipartite");
				}

					if (is_cyclic) {
						System.out.println("Cycle Exists");
						is_cyclic = false;
					} else {
						System.out.println("Acyclic");

					}
				

			}
		}

	}
}
