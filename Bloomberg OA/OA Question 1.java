/* Question:

012345678
bloomberg
  ...
 ...
       .
         .
         
-> bbr

 func('bloomberg', [
     [2,4],
     [1,3],
     [6,6],
     [8,8],
 ]) {
     
     
 }

*/

/* 1. Used boolean[] to make sure the remove character
   2. add the element did not need remove into StringBuilder
   ==> StrungBuilder sb.add(s.charAt(i)) ==> Used for-loop
   3. return output ==> sb.toString()
Example:
s    = bloomberg 
flex = tfffftftf
     
*/

import java.util.*;
public class Main{
    
    public static String quesion1(String s, int[][] array){
        StringBuilder sb = new StringBuilder();
        boolean[] flex = new boolean[s.length()];
        
        for(int z = 0; z < flex.length; z++){
            flex[z] = true;
        }
        
        //used the while loop to get the delete range
        for(int i = 0; i < array.length; i++){
            //get the remove range
            int start = array[i][0]; //the first
            int end = array[i][1]; //the second
            
            for(int k = start; k <= end; k++){
                flex[k] = false;
            }
        }
        
        for(int j = 0; j < flex.length; j++){
            if(flex[j]){
                sb.append(s.charAt(j));
            }
        }
        
        return sb.toString();
    }
    
  //Main function
    public static void main(String[] args){
        String s = "bloomberg";
        int[][] arr = new int[][]{{2, 4}, {1, 3}, {6, 6}, {8, 8}};
        
        String res = quesion1(s, arr);
        
        System.out.print(res);
    }
}
