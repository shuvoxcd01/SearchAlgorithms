class StateSpace{
	public String indexToNodeMap[];
	public int ss[][];
	
	public StateSpace(){
		indexToNodeMap = new String[20];
		
		indexToNodeMap[0] = "Oradea";
		indexToNodeMap[1] = "Zerind";
		indexToNodeMap[2] = "Arad";
		indexToNodeMap[3] = "Timisoara";
		indexToNodeMap[4] = "Lugoj";
		indexToNodeMap[5] = "Mehadia";
		indexToNodeMap[6] = "Drobeta";
		indexToNodeMap[7] = "Sibiu";
		indexToNodeMap[8] = "Rimnicu Vilcea";
		indexToNodeMap[9] = "Craiova";
		indexToNodeMap[10] = "Fagaras";
		indexToNodeMap[11] = "Pitesti";
		indexToNodeMap[12] = "Neamt";
		indexToNodeMap[13] = "Bucharest";
		indexToNodeMap[14] = "Giurgiu";
		indexToNodeMap[15] = "Iasi";
		indexToNodeMap[16] = "Vaslui";
		indexToNodeMap[17] = "Urziceni";
		indexToNodeMap[18] = "Hirsova";
		indexToNodeMap[19] = "Eforie";
		
		
		ss = new int[20][20];
		
		for(int i=0; i < 20; i++)
			for(int j=0; j < 20; j++)
				ss[i][j] = 0;
			
		ss[0][1] = 71;
		ss[0][7] = 151;
		ss[1][0] = 71;
		ss[1][2] = 75;
		ss[2][1] = 75;
		ss[2][3] = 118;
		ss[2][7] = 140;
		ss[3][2] = 118;
		ss[3][4] = 111;
		ss[4][3] = 111;
		ss[4][5] = 70;
		ss[5][4] = 70;
		ss[5][6] = 75;
		ss[6][5] = 75;
		ss[6][9] = 120;
		ss[7][0] = 151;
		ss[7][2] = 140;
		ss[7][10] = 99;
		ss[7][8] = 80;
		ss[8][7]= 80;
		ss[8][11] = 97;
		ss[8][9] = 146;
		ss[9][8] = 146;
		ss[9][11] = 138;
		ss[9][6] = 120;
		ss[10][7] = 99;
		ss[10][13] = 211;
		ss[11][8] = 97;
		ss[11][13] = 101;
		ss[11][9] = 138;
		ss[12][15] = 87;
		ss[13][10] = 211;
		ss[13][17] = 85;
		ss[13][14] = 90;
		ss[13][11] = 101;
		ss[14][13] = 90;
		ss[15][12] = 87;
		ss[15][16] = 92;
		ss[16][15] = 92;
		ss[16][17] = 142;
		ss[17][16] = 142;
		ss[17][13] = 85;
		ss[17][18] = 98;
		ss[18][17] = 98;
		ss[18][19] = 86;
		ss[19][18] = 86;
		
	}
	
	public void show(){
		int k=1;
		for(int i=0; i < 20; i++)
			for(int j=0; j < 20; j++)
				if(ss[i][j] != 0){
					System.out.println(k++ + ". from " + indexToNodeMap[i] + " to " + indexToNodeMap[j] + " is " + ss[i][j] + " kilometers.");
				}
	}
	
	
	public int getIndexOfState(String state){  // returns -1 if state not found.
		for(int i=0; i < indexToNodeMap.length; i++)
			if(indexToNodeMap[i].equals(state)) 
				return i;
			
		return -1;
	}
	
	public int stepCost(String stateFrom, String stateTo){
		int stateFromIndex = getIndexOfState(stateFrom);
		int stateToIndex = getIndexOfState(stateTo);
		
		if(stateFromIndex == -1 || stateToIndex == -1)
			return -1;
		
		else return ss[stateFromIndex][stateToIndex];
	}
	
	public static void main(String args[]){
		//(new StateSpace()).show();
		
		StateSpace ss = new StateSpace();
		
		ss.show();
		System.out.println("Index of Arad is " + ss.getIndexOfState("Arad"));
		System.out.println("Step cost from Arad to Sibiu is : " + ss.stepCost("Arad", "Sibiu"));
		System.out.println("Step cost from Sibiu to Arad is : " + ss.stepCost("Sibiu", "Arad"));
	}
	
}

