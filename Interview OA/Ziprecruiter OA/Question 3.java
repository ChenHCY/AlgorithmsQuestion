import java.util.*;

public class Main
{
	public static void main(String[] args) {
	   // 102
	   // 120
	   // 020
	    int[][] matrix = new int[][]{{1, 0, 2}, {1, 2, 0}, {0, 2, 0}};
		System.out.println(solution(matrix));
	}
	
	public static int solution(int[][] m) {
	    HashMap<Integer, Integer> yMap = new HashMap<>();
	    HashMap<Integer, Integer> outsideMap = new HashMap<>();
	    int indent = 0, len = m.length;
      int max = 0;
    
	    for (int i = 0; i < len; i++) {
	        for (int j = 0; j < len; j++) {
	            int num = m[i][j];
	            // in Y
	            if (j == indent || j == len - indent - 1) {
	                yMap.put(num, yMap.getOrDefault(num, 0) + 1);
	            } else { // outside of Y
	                System.out.println(num);
	                outsideMap.put(num, outsideMap.getOrDefault(num, 0) + 1);
	            }
	        }
	        if (indent != len / 2) indent ++;
	    }
    
	    for (int i : yMap.keySet()) {
	        for (int j : outsideMap.keySet()) {
	            if (i != j) {
	                max = Math.max(max, yMap.get(i) + outsideMap.get(j));
	            }
	        }
	    }
	    return len * len - max;
	}
}
