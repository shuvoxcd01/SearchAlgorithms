import java.util.*;

public class Frontier implements Comparator<Node>{
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
		return n1.pathCost - n2.pathCost;
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