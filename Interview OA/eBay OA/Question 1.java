/* eBay OA Question 1
Example: 
Input: 8
Output: 
********
*      *
*      *
*      *
*      *
*      *
*      *
********
*/

import java.util.*;
public class Main
{
	public static void main(String[] args) {
	    int n = 8;
	    String[] arr = solution(n);
	    for(String str : arr){
	        System.out.println(str);
	    }
	}
	
	private static String[] solution(int n) {
	   List<String> list = new ArrayList<>();
	   int i = 0;
	   
	   while(i < n){
	       String temp = "";
	       if(i == 0 || i == n - 1){
	           for(int j = 0; j < n; j++){
	               temp += "*";
	           }
	       } else{
	           temp += "*";
	           for(int z = 0; z < n - 2; z++){
	               temp += " ";
	           }
	           temp += "*";
	       }
	       
	       list.add(temp);
	       i++;
	   }
	   
	   String[] res = new String[list.size()];
	   for(int k = 0; k < list.size(); k++){
	       res[k] = list.get(k);
	   }
	   
	   return res;
	}
    
}
