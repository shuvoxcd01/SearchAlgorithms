import java.util.*;

class BlindSearch{
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
	
	public String bfs(Problem problem){
		Node node = new Node();
		node.state = problem.initialState;
		node.pathCost = 0;
		
		if(problem.goalTest(node.state)) return solution(node);
		
		ArrayDeque <Node> frontier = new ArrayDeque<Node>();
		HashSet <String> hsFrontier = new HashSet<String>(); // hashset for frontier
		frontier.addLast(node);
		hsFrontier.add(node.state);
		
		HashSet <String> explored = new HashSet <String> ();
		
		while(true){
			if(frontier.isEmpty()) return "failure";
			node = frontier.removeFirst();
			hsFrontier.remove(node.state);
			explored.add(node.state);
			
			String actionSet[] = problem.actions(node.state);
			
			for(String action : actionSet){
				Node child = childNode(problem,node,action);
				if(!explored.contains(child.state) && !hsFrontier.contains(child.state)){
					if(problem.goalTest(child.state)) return solution(child);
					frontier.addLast(child);
					hsFrontier.add(child.state);
				}
			}
		}
	}
	
	public String ucs(Problem problem){
		Node node = new Node();
		node.state = problem.initialState;
		node.pathCost = 0;
		
		Frontier frontier = new Frontier();
		frontier.insert(node);
		
		HashSet <String> explored = new HashSet <String> ();
		
		while(true){
			if(frontier.isEmpty()) return "failure";
			
			node = frontier.pop();
			
			if(problem.goalTest(node.state)) return solution(node);
			explored.add(node.state);
			
			String actionSet[] = problem.actions(node.state);
			
			for(String action : actionSet){
				Node child = childNode(problem,node,action);
				if(!explored.contains(child.state) && !frontier.contains(child.state))
					frontier.insert(child);
				else if(frontier.contains(child.state)){
					frontier.replaceIfLowerPathCost(child);
				}
			}
		}
	}
	
	public static void main(String args[]){
		Problem problem = new Problem();
		BlindSearch bs = new BlindSearch();
		
		//String solution = bs.bfs(problem);
		
		String solution = bs.ucs(problem);
		
		System.out.println(solution);
	}
}