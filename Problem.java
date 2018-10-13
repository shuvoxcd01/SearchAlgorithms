import java.util.Scanner;
import java.util.ArrayDeque;

class Problem{
	public String initialState;
	public StateSpace ss;
	public String goalState;
	
	public Problem(){
		ss = new StateSpace();
		boolean checker = false;
		Scanner scanner = new Scanner(System.in);
		while(!checker){
			System.out.println("Enter initial state:");
			initialState = scanner.next();
			int initialStateIndex = ss.getIndexOfState(initialState);
			if(initialStateIndex != -1){
				break;
			}
			System.out.println("Unacceptable input. Please try again.");
		}
		
		while(!checker){
			System.out.println("Enter goal state:");
			goalState = scanner.next();
			int goalStateIndex = ss.getIndexOfState(goalState);
			if(goalStateIndex != -1){
				break;
			}
			System.out.println("Unacceptable input. Please try again.");
		}
		
		
		
	}
	
	public String[] actions(String state){
		int stateIndex = ss.getIndexOfState(state);
		if(stateIndex == -1){
			System.out.println("Given state does not exist");
			return null;
		}
		
		//String actionSet = "";
		
		ArrayDeque <String> actionSet = new ArrayDeque<String>();
		
		for(int i=0; i < ss.indexToNodeMap.length; i++){
			if(ss.ss[stateIndex][i] != 0)
				actionSet.addLast("Go(" + ss.indexToNodeMap[i] + ")");
		}
		
		return actionSet.toArray(new String[0]);
	}
	
	public String result(String state, String action){
		String resultState = action.substring(3, action.length()-1);
		
		//System.out.println(resultState);
		
		int resultStateIndex = ss.getIndexOfState(resultState);
		int initialStateIndex = ss.getIndexOfState(state);
		
		
		if(resultStateIndex == -1){
			System.out.println("Such Action does not exist in state " + state);
			return "Invalid Action";
		}
		
		else if(initialStateIndex == -1){
			System.out.println("State " + state + " does not exist.");
			return "Invalid State";
		}
		
		else if(ss.ss[initialStateIndex][resultStateIndex] == 0){
			System.out.println(action + " does not apply to state " + state);
			return "Invalid Action";
		}
		
		else{
			return resultState;
		}
	}
	
	public int stepCost(String state, String action){  // returns -1 if state not found
		String stateTo = result(state, action);
		
		return ss.stepCost(state, stateTo);
		
	}
	
	public boolean goalTest(String state){
		if(goalState == null){
			System.out.println("Goal has not been set yet.");
			return false;
		}
		return goalState.equals(state);
	}
	
	public static void main(String args[]){
		Problem problem = new Problem();
		
		Scanner scanner = new Scanner(System.in);
		
		String str = scanner.next();
		
		String actionSet[] = problem.actions(str);
		
		for(int i=0; i < actionSet.length; i++)
			System.out.println(actionSet[i]);
		
		
		String action = "Go(" + str + ")";
		
		System.out.println(problem.result(str,action));
		System.out.println(problem.result("Sibiu",action));
		
		System.out.println(problem.stepCost("Sibiu",action));
		
		System.out.println(problem.goalTest("Sibiu"));
		
		
		
	}
}