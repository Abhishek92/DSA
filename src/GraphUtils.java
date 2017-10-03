

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {

		/* IMPLEMENT THIS METHOD! */
		if(null == graph)
			return -1;
		if(src == null || src.isEmpty())
			return -1;
		if(dest == null || dest.isEmpty())
			return -1;

		Node startNode = graph.getNode(src);
		Edge edge = new Edge(startNode, graph.getNode(dest));
		if(!graph.containsNode(startNode))
			return -1;
		if(!graph.containsElement(dest))
			return -1;
		if(src.equalsIgnoreCase(dest))
			return 0;

		if(graph.adjacencySets.get(startNode).contains(edge))
			return 1;

		BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(graph);
		boolean bfsFound = breadthFirstSearch.bfs(startNode, dest);

		if(bfsFound){
			return breadthFirstSearch.marked.size() / 2;
		}
		else
			return -1;
		
		 // this line is here only so this code will compile if you don't modify it
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		/* IMPLEMENT THIS METHOD! */
		Set<String> nodeSetWithMinDist = new HashSet<>();
		if(null == graph)
			return null;
		if(distance < 1)
			return null;
		if(null == src || src.isEmpty())
			return null;
		Node srcNode = graph.getNode(src);
		if(!graph.containsNode(srcNode))
			return null;

		Node rootNode = graph.getStartingNode();
		int minedges = minDistance(graph, rootNode.getElement(), src);
		if(distance <= minedges)
		{
			BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(graph);
			breadthFirstSearch.bfs(rootNode, src);
			Iterator<Node> iterator = breadthFirstSearch.marked.iterator();
			while (iterator.hasNext()){
				Node childNode = iterator.next();
				if(!childNode.getElement().equalsIgnoreCase(src))
					nodeSetWithMinDist.add(childNode.getElement());
			}
		}

		
		return nodeSetWithMinDist; // this line is here only so this code will compile if you don't modify it
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/* IMPLEMENT THIS METHOD! */
		if(null == g)
			return false;
		if(null == values || values.isEmpty())
			return false;

		Node startNode = g.getNode(values.get(0));
		BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(g);
		breadthFirstSearch.bfs(values.get(values.size() - 1), startNode);
		values.remove(0);
		values.remove(values.size() - 1);

		if(breadthFirstSearch.marked.size() == values.size()){
			int count = 0;
			Iterator<Node> iterator = breadthFirstSearch.marked.iterator();
			while (iterator.hasNext()){
				Node childNode = iterator.next();
				values.remove(childNode.getElement());
			}
		}
		
		return values.isEmpty(); // this line is here only so this code will compile if you don't modify it
	}
	
}
