package UCSFrontier;

import java.util.*;


public class Frontier implements Comparator<Node>{
	public PriorityQueue<Node> frontier = new PriorityQueue<Node>(1,this);
	public HashSet<String> hsFrontier = new HashSet<String>();
	
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
	
	public void replace(Node node){
		Iterator <Node> itr = frontier.iterator();
		
		while(itr.hasNext()){
			if(itr.next().state == node.state){
				itr.remove();
				frontier.add(node);
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