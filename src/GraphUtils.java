

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
			return breadthFirstSearch.marked.size() / 2 == 1 ? breadthFirstSearch.marked.size() - 1 : breadthFirstSearch.marked.size() / 2;
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

		Set<Node> allNodeSet = graph.getAllNodes();
		Iterator<Node> iterator = allNodeSet.iterator();
		while (iterator.hasNext()){
			Node currNode = iterator.next();
			int minedges = minDistance(graph, src, currNode.getElement());
			if(minedges > 0 && minedges <= distance) {
				if (!currNode.getElement().equalsIgnoreCase(src))
					nodeSetWithMinDist.add(currNode.getElement());
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

		boolean flag = false;
		int visited = 0;
		for (int i = 1; i < values.size() - 1; i++) {
			Node childNode = g.getNode(values.get(i));
			Node childNextNode = g.getNode(values.get(i+1));
			Set<Node> neighbours = g.getNodeNeighbors(childNode);
			if(neighbours.contains(childNextNode))
				flag = true;
			else return false;
			visited++;
		}

		values.remove(0);
		values.remove(values.size() - 1);

		return flag && visited + 1 == g.adjacencySets.size();
	}
	
}
