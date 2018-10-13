import java.util.*;

public class BestFirstFrontier implements Comparator<Node>{
	public PriorityQueue<Node> frontier = new PriorityQueue<Node>(1,this);
	public HashSet<String> hsFrontier = new HashSet<String>();
	
	boolean isEmpty(){
		return frontier.isEmpty();
	}
	
	public void insert(Node node){
		this.frontier.add(node);
		this.hsFrontier.add(node.state);
	}
	
	public boolean contains(Node node){
		return frontier.contains(node);
	}
	
	public boolean contains(String state){
		return hsFrontier.contains(state);
	}
	
	public int compare(Node n1, Node n2){
		return heuristic(n1) - heuristic(n2);
	}
	
	public int heuristic(Node n){
		 String [] states = {"Arad", "Bucharest", "Craiova", "Drobeta", "Eforie", "Fagaras", "Giurgiu", "Hirsova", "Iasi", "Lugoj", "Mehadia", "Neamt", "Oradea", "Pitesti", "Rimnicu Vilcea", "Sibiu", "Timisoara", "Urziceni", "Vaslui", "Zerind"};
		int distance[] = {366,0,160,242,161,176,77,151,226,244,241,234,380,100,193,253,329,80,199,374};
		
		for(int i=0; i < states.length; i++)
			if(states[i].equals(n.state))
				return distance[i];
			
		return -1;
	}
	
	public void replaceIfLowerPathCost(Node replaceNode){
		Iterator <Node> itr = frontier.iterator();
	
		while(itr.hasNext()){
			Node node = itr.next();
			if(node.state.equals(replaceNode.state) && node.pathCost > replaceNode.pathCost){	
				itr.remove();
				frontier.add(replaceNode);
				break;
			}
		}
	}

	public Node pop(){
		Node node = frontier.poll();
		hsFrontier.remove(node.state);
		return node;
	}
	
	
}