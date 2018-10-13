/*
Author: Falguni Das Shuvo.
Recursive Best First Search - popularly known as RBFS is a heuristic (informed) search algorithm. It is a memory
bounded search algorithm.
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

class RBFS{
	
	String recursive_best_first_search(Problem problem){
		return rbfs(problem, makeNode(problem.initialState), java.lang.Integer.MAX_VALUE);
	}
	
	String rbfs(Problem problem, Node node, int f_limit){
		if(problem.goalTest(node.state)) return solution(node);
		
		ArrayList<Node> successors = new ArrayList<>();
		
		String actions[] = problem.actions(node.state);
		System.out.println("Actions for node " + node.state + " are: ");
		for(String action : actions)
			System.out.println(action);
		
		for(String action : actions){
			Node child = childNode(problem, node, action);
			successors.add(child);
			System.out.println("Added to successors: " + child.state);
		}
		if(successors.isEmpty()) return "failure," + java.lang.Integer.MAX_VALUE;
		
		for(Node s : successors)
			s.f = ((s.g + s.h) > node.f) ? (s.g + s.h) : node.f;
		
		System.out.println("this far");
		
		while(true){
			Node best = findLowest_f_valuedNode(successors);
			System.out.println("best: " + best.state);
			System.out.println("best.f: " + best.f);
			if(best.f > f_limit) return "failure," + best.f;
			int alternative = findSecondBest_f_value(best.f, successors);
			System.out.println("alternative: " + alternative);
			try{
				Thread.sleep(2000);
			}catch(Exception e){}
			String str = rbfs(problem, best, ((f_limit < alternative) ? f_limit : alternative));
			String result[] = str.split(",");
			if(!result[0].equals("failure")) return result[0];
			best.f = Integer.parseInt(result[1]);
			//System("pause");
		}
		
	}
	
	private int findSecondBest_f_value(int best, ArrayList<Node> arr){
		Iterator<Node> itr = arr.iterator();
		int secondBest = itr.next().f;
		
		if(secondBest == best && itr.hasNext())
			secondBest = itr.next().f;
		
		Node challenger;
		
		while(itr.hasNext()){
			challenger = itr.next();
			if(secondBest > challenger.f && challenger.f != best)
				secondBest = challenger.f;
				
		}
		
		return secondBest;
	}
	
	private Node findLowest_f_valuedNode(ArrayList<Node> arr){
		
		System.out.println("Inside findLowest_f_valuedNode(ArrayList<Node>) => arr.size() : " + arr.size());
		Iterator<Node> itr = arr.iterator();
		
		Node best = itr.next();
		System.out.println("Inside findLowest_f_valuedNode(ArrayList<Node>)=> itr.next().state, itr.next().f :   " + best.state + ",  " + best.f);
		Node challenger;
		while(itr.hasNext()){
			challenger = itr.next();
			System.out.println("Inside findLowest_f_valuedNode(ArrayList<Node>)=> itr.next().state, itr.next().f :   " + challenger.state + ",  " + challenger.f);
			if(best.f > challenger.f)
				best = challenger;
				
		}
		System.out.println("Inside findLowest_f_valuedNode(ArrayList<Node>) => best, best.f  :  " + best.state + ",  " +  best.f);
		return best;
	}
	
	private Node makeNode(String state){
		Node node = new Node();
		node.state = state;
		node.parent = null;
		node.action = null;
		node.g  = 0;
		node.h = heuristic(node);
		node.f = node.g + node.h;
		
		return node;
	}
	private Node childNode(Problem problem, Node parent, String action){
		Node child = new Node();
		child.state = problem.result(parent.state,action);
		child.parent = parent;
		child.action = action;
		child.g = parent.g + problem.stepCost(parent.state,action);
		child.h = heuristic(child);
		
		System.out.println("Child created with state: " + child.state);
		
		return child;
	}
	
	class Node{
		//public static double totalNodes=0;
		//public Node(){
			//totalNodes++;
		//}
		public String state;
		public Node parent;
		public String action;
		public int g;  // pathcost
		public int h;  // heuristic
		public int f;
	}
	
	private int heuristic(Node n){
		String [] states = {"Arad", "Bucharest", "Craiova", "Drobeta", "Eforie", "Fagaras", "Giurgiu", "Hirsova", "Iasi", "Lugoj", "Mehadia", "Neamt", "Oradea", "Pitesti", "Rimnicu Vilcea", "Sibiu", "Timisoara", "Urziceni", "Vaslui", "Zerind"};
		int distance[] = {366,0,160,242,161,176,77,151,226,244,241,234,380,100,193,253,329,80,199,374};
		
		for(int i=0; i < states.length; i++)
			if(states[i].equals(n.state))
				return distance[i];
			
		return -1;
	}
	
	
	public String solution(Node node){
		String solution = "";
		Stack <String> solutionStates = new Stack <String>();
		while(node != null){
			solutionStates.push(node.state);
			node = node.parent;
		}
		
		while(!solutionStates.empty()){
			//System.out.print(solutionStates.pop() +  " ");
			solution += (solutionStates.pop() + " ");
		}
		
		return solution;
	}
	
	public static void main(String args[]){
		Problem problem = new Problem();
		//GreedyBestFirstSearch bs = new GreedyBestFirstSearch();
		RBFS r = new RBFS();
		
		//String solution = bs.bfs(problem);
		
		String solution = r.recursive_best_first_search(problem);
		
		System.out.println(solution);
	}
}