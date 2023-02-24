/*SIG OA Question 2*/

import java.util.HashMap;

public class ChemicalReactionBalance {
    public static void main(String[] args) {
        String test1 = "aabb"; //output is 27
        //ababa 
        String test2 = "abbb"; //output is 2
        //abbba  bbabb
    
        System.out.println(palindromeWays(test1));
        System.out.println(palindromeWays(test2));
    }
    
    public static int palindromeWays(String s){
        int res = 0;
        int oddNumber = 0; 
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        for(char key : map.keySet()){
            if(map.get(key) % 2 != 0){
                oddNumber += 1;
            }
        }
        
        if(oddNumber == 0){
            res = 27;
        } else{
            res = oddNumber;
        }
        
        return res;
    }
}
