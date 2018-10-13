import java.util.*;

class BidirectionalSearch{
	private static HashSet <String> hsFrontierS = new HashSet<String>(); // hashset for frontier
	private static HashSet <String> hsFrontierG = new HashSet<String>(); // hashset for frontier
	
	public Node childNode(Problem problem, Node parent, String action){
		Node child = new Node();
		child.state = problem.result(parent.state,action);
		child.parent = parent;
		child.action = action;
		child.pathCost = parent.pathCost + problem.stepCost(parent.state,action);
		
		return child;
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
	
	public String bss(Problem problem){
		Node node = new Node();
		node.state = problem.initialState;
		node.pathCost = 0;
		
		if(problem.goalTest(node.state)) return solution(node);
		
		ArrayDeque <Node> frontier = new ArrayDeque<Node>();
		//HashSet <String> hsFrontier = new HashSet<String>(); // hashset for frontier
		frontier.addLast(node);
		hsFrontierS.add(node.state);
		
		HashSet <String> explored = new HashSet <String> ();
		
		while(true){
			if(frontier.isEmpty()) return "failure";
			node = frontier.removeFirst();
			hsFrontierS.remove(node.state);
			explored.add(node.state);
			
			String actionSet[] = problem.actions(node.state);
			
			for(String action : actionSet){
				Node child = childNode(problem,node,action);
				if(!explored.contains(child.state) && !hsFrontierS.contains(child.state)){
					if(problem.goalTest(child.state)) return solution(child);
					frontier.addLast(child);
					hsFrontierS.add(child.state);
				}
			}
		}
	}
	
	public String bsg(Problem problem){
		Node node = new Node();
		node.state = problem.initialState;
		node.pathCost = 0;
		
		if(problem.goalTest(node.state)) return solution(node);
		
		ArrayDeque <Node> frontier = new ArrayDeque<Node>();
		//HashSet <String> hsFrontier = new HashSet<String>(); // hashset for frontier
		frontier.addLast(node);
		hsFrontierG.add(node.state);
		
		HashSet <String> explored = new HashSet <String> ();
		
		while(true){
			if(frontier.isEmpty()) return "failure";
			node = frontier.removeFirst();
			hsFrontierG.remove(node.state);
			explored.add(node.state);
			
			String actionSet[] = problem.actions(node.state);
			
			for(String action : actionSet){
				Node child = childNode(problem,node,action);
				if(!explored.contains(child.state) && !hsFrontierG.contains(child.state)){
					if(problem.goalTest(child.state)) return solution(child);
					frontier.addLast(child);
					hsFrontierG.add(child.state);
				}
			}
		}
	}
	
	public static void main(String args[]){
		Problem problem = new Problem();
		//Bide
		
		//String solution = ;
		
		//System.out.println(solution);
	}
}