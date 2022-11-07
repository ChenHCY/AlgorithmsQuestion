/*

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

/* 1. Ues hashmap to save the character with char position
   2. need remove string range
   ==> stringbuilder delete(start, end);
   3. for-loop to travser the string
  
  
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
    
    public static void main(String[] args){
        String s = "bloomberg";
        int[][] arr = new int[][]{{2, 4}, {1, 3}, {6, 6}, {8, 8}};
        
        String res = quesion1(s, arr);
        
        System.out.print(res);
    }
}
