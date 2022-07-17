/*Leetcode 345. Reverse Vowels of a String
Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.

 
Example 1:
Input: s = "hello"
Output: "holle"

Example 2:
Input: s = "leetcode"
Output: "leotcede"

Constraints:
1 <= s.length <= 3 * 105
s consist of printable ASCII characters.
*/

class Solution {
    public String reverseVowels(String s) {
        char[] c = s.toCharArray(); //change String s be a char[] array
        int left = 0;//left-pointer, star at 0
        int right = c.length - 1; // right-pointer, start at end of char[] array

        //find the vowels in the Strings ('a', 'e', 'i', 'o', and 'u')
        Set<Character> charSet = new HashSet<>(Arrays.asList('a','e','i','o','u', 'A', 'E', 'I', 'O', 'U'));
        
        
        while(left < right){
            //If the element of left-pointer is not vowels, and the left pointer is not larger than right-pointer
            while(!charSet.contains(c[left]) && left < right){
                left++; // Move left-pointer into next element
            }
            
            //If the element of right-pointer is not vowels, and the left pointer is not larger than right-pointer
            while(left < right && !charSet.contains(c[right])){
                right--; // Move right-pointer into next element
            }
            
            //if the element of right-pointer and left-pointer are vowels, reverse the position
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        
        return String.valueOf(c); //change the char[] array back to String
    }
}
