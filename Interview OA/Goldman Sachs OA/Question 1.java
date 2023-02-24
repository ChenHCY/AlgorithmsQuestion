 //testcase: ArrayList<List<String>> input = [["2"], ["3"], ["P1:a","P3:b","P5:x"], ["P1:b","P2:q","P5:x"]];
//output: b b x q

import java.util.*;


public class Main
{
	public static void main(String[] args) {
    
		int resrcCount = Integer.valueOf(input.get(0).get(0)); //n
		int paramCount = Integer.valueOf(input.get(1).get(0)); //p
		
		ArrayList<String> orderList = new ArrayList<>();    // record order of arriva
		HashMap<String, String> map = new HashMap<>();
    
    //add the first time value and change the value
		for (int i = 2; i < input.size(); i++) {
		    for (String s : input.get(i)) {
		        String[] temp = s.split(":"); //"P1:a"
		        String name = temp[0]; //"p1"
		        String param = temp[1];//"a"
		        map.put(name, param); // change the value
		        orderList.add(name);
		    }
		}
		
		//add the value into res
		ArrayList<String> res = new ArrayList<>();
		HashSet<String> visited = new HashSet<>();
		for (int i = 0; i < orderList.size(); i++) {
		    if (visited.add(orderList.get(i))) {
		        res.add(map.get(orderList.get(i)));
		    }
		}
		
		for (String s : res) {
		    System.out.println(s);
		}
	}
}
