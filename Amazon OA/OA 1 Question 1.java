/* Question 1:
Change a String format be palindrome, need follow the alphabetical order

Example: 
Input: "xyxy"
Output: "xxyy"
*/
//Solution 1: Used bucket to sort and save element
public class MyClass {
    public static void main(String args[]) {
      String str = "xyxy";
      System.out.println(reverse(str));
    }
  
  /*Buckets exactly is an array of Nodes. So single bucket is an instance of class java.util.HashMap.Node. 
  Bucket sort can also be used for partitioning an array into buckets where each bucket is individually sorted.*/
    
    public static String reverse(String s){
        int[] array = new int[26]; // Bucket sort (only small letter: 26; small letter + large letter: 128; sort numbers: 9)
        StringBuilder firstPart = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        
      //Save all the character element from String s into bucket sort
        for(int i = 0; i < s.length(); i++){
            array[s.charAt(i) - 'a']++; // Count the numbers of each character from String
        } //It also used for sort follow the alphabetical order
        
      //Use bucket to sort String to be palindrome 
        for(int i = 0; i < array.length; i++){
            if(array[i] != 0){ //need check whether have this character element
                if(array[i] % 2 == 0){ //if the number of current character is even
                    for(int k = 0; k < (array[i] / 2); k++){
                        firstPart.append((char) (i + 'a')); //add half part of current character into firstPart StringBuilder
                    }
                } else{ //if the number of current character is odd
                    for(int z = 0; z < array[i]; z++){
                        mid.append((char) (i + 'a')); //add all the number of current characters into mid Part
                    }
                }
            }
        }
        //Output be palindrome format
        return firstPart.toString() + mid.toString() + firstPart.reverse().toString();
        
    }
}

//Solution 2: Used HashMap to save every number of each character from string
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class MyClass {
    public static void main(String args[]) {
      String str = "abbbaaa";
      System.out.println(reverse(str));
    }
      
    public static String reverse(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder firstPart = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        
      //Save all the character element from String s into hashmap
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1); // Count the numbers of each character from String
        }
        
      //Use for-loop to sort String to be palindrome 
        for(int i = 0; i < 26; i++){
            if(map.containsKey((char) (i + 'a'))){ //need check whether have this character element
                int number = map.get((char) (i + 'a')); //how many number of curr element
                if(number % 2 == 0){ //if the number of current character is even
                    for(int k = 0; k < (number / 2); k++){
                        firstPart.append((char) (i + 'a')); //add half part of current character into firstPart StringBuilder
                    }
                } else{ //if the number of current character is odd
                    for(int z = 0; z < number; z++){
                        mid.append((char) (i + 'a')); //add all the number of current characters into mid Part
                    }
                }
            }
        }
        //Output be palindrome format
        return firstPart.toString() + mid.toString() + firstPart.reverse().toString();
        
    }
}
