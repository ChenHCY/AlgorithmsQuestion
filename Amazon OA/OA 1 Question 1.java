/* Question 1:
Change a String format be palindrome, need follow the alphabetical order

Example: 
Input: "xyxy"
Output: "xxyy"
*/

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
