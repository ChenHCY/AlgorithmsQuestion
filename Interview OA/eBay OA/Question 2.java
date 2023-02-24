/* eBay OA Question 2 
Example 1: 
Input: 66644319333
Output: 26328

Example 2: 
Input: 44886
Output: 84

*/

import java.util.*;
public class Main
{
	public static void main(String[] args) {
		System.out.println(solution("44886"));
	}
	
	private static String solution(String s) {
	    String next = helper(s);
	    
	    while (!next.equals(s)) {
	        s = next;
	        next = helper(next);
	    }
	    
	    return next;
	}
	
	private static String helper(String s) {
	    char[] arr = s.toCharArray();
	    int[] intArr = new int[arr.length];
	    for (int i = 0; i < arr.length; i++) {
	        intArr[i] = arr[i] - '0';
	    }
	    
	    String res = "";
	    for (int i = 0; i < intArr.length; i++) {
	        int stop = i + 1;
	        while (stop < intArr.length && intArr[stop] == intArr[i]) {
	            stop++;
	        }
	        res += "" + ((stop - i) * intArr[i]);
	        i = stop - 1;
	    }
	    
	    return res;
	}
    
}
