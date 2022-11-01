/* Roblox OA OA 2 Question 4 */

import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    int[] houses = new int[]{1, 2, 3, 6, 7, 9};
	    int[] queries = new int[]{6, 3, 7, 2, 9, 1};
	    int[] res = solution(houses, queries);
	    for(int num : res){
	        System.out.println(num);
	    }
	}
	
	public static int[] solution(int[] houses, int[] queries) {
        List<Integer> list = new ArrayList<>();
        int segments = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
    
        //get the how many segments of houses before queries
        for(int i = 1; i < houses.length; i++){
            if(houses[i] - 1 != houses[i - 1]){
                segments += 1;
            } 
        }
    
        for(int z = 0; z < houses.length; z++){
            map.put(houses[z], z);
        }
    
        for(int i = 0; i < queries.length; i++){
            int temp = queries[i];
            int posistion = map.get(temp);
            
            if(posistion != 0 && posistion != houses.length - 1){
                if(houses[posistion] - 1 == houses[posistion - 1] && houses[posistion] + 1 == houses[posistion + 1]){
                    segments += 1;
                } else if (houses[posistion] - 1 != houses[posistion - 1] && houses[posistion] + 1 != houses[posistion + 1]){
                    segments -= 1;
                }
            } else{
                if(posistion == 0){
                  if(houses[posistion] + 1 != houses[posistion + 1]){
                        segments -= 1;
                   } 
                } else if(posistion == houses.length - 1) {
                   if(houses[posistion] - 1 != houses[posistion - 1]){
                        segments -= 1;
                   } 
                }
            }
            
            houses[posistion] = -1;
            list.add(segments);
        }
    
        //change list to be the int[] array
        int[] res = new int[list.size()];
        for(int j = 0; j < list.size(); j++){
            res[j] = list.get(j);
        }
    
        return res;
    }
}
