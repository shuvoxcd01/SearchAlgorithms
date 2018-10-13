import java.util.*;

class IteratorTest{
	public static void main(String args[]){
		ArrayList<String> arrl = new ArrayList<>();
		
		arrl.add("hello");
		arrl.add("world");
		arrl.add("welcome");
		
		Iterator <String> itr = arrl.iterator();

		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}
}