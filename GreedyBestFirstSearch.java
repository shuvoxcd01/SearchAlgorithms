import java.util.*;

class GreedyBestFirstSearch{
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
	
	public String bestFirst(Problem problem){
		Node node = new Node();
		node.state = problem.initialState;
		node.pathCost = 0;
		
		BestFirstFrontier frontier = new BestFirstFrontier();
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
		GreedyBestFirstSearch bs = new GreedyBestFirstSearch();
		
		//String solution = bs.bfs(problem);
		
		String solution = bs.bestFirst(problem);
		
		System.out.println(solution);
	}
}