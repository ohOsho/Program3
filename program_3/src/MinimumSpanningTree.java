import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MinimumSpanningTree {

	public void kruskalsAlgorithm(UndirectedGraph graph) {
		
		HashSet<Edge> mst = new HashSet<Edge>();
		//ccm = connected component marker (used to detect cycles)
		HashMap<Integer, String> ccm = new HashMap<Integer, String>();
		
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();
		for(Edge edge: graph.getEdges()) {
			edgeQueue.add(edge);
		}
		
		while(!edgeQueue.isEmpty()) {
			
			Edge check = edgeQueue.poll();
			
			if(!ccm.containsValue(check.getMovie())) {
				ccm.put(0, check.getMovie());
			}
			
			if(ccm[check.getVertices()[0]] != ccm[check.getVertices()[1]]) {
				
				mst.add(check);
				
				int newMarker = ccm[check.getVertices()[0]];
				int oldMarker = ccm[check.getVertices()[1]];
				
				for(int i =0; i < ccm.length; i++) {
					
					if(ccm[i]  == oldMarker) {
						ccm[i] = newMarker;
					}
				}	
			}
			
		}

		for(Edge edge: mst) {
			System.out.println(edge);
		}
	}
}
