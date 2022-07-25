/* Leetcode 434. Number of Segments in a String

Given a string s, return the number of segments in the string.
A segment is defined to be a contiguous sequence of non-space characters.

Example 1:
Input: s = "Hello, my name is John"
Output: 5
Explanation: The five segments are ["Hello,", "my", "name", "is", "John"]

Example 2:
Input: s = "Hello"
Output: 1
 

Constraints:

0 <= s.length <= 300
s consists of lowercase and uppercase English letters, digits, or one of the following characters "!@#$%^&*()_+-=',.:".
The only space character in s is ' '.
*/

class Solution {
    public int countSegments(String s) {
        if(s.isEmpty()){
            return 0;
        }
        //The method split() splits a String into multiple Strings given the delimiter that separates them. 
        String[] str = s.split(" "); 
        int res = 0;
        
        for(String i : str){
            if(!i.isEmpty()){ // Example: ", , , ,        a, eaefa" 
                res++; //Should be noted that there may be " " in the middle of the string
            }
        }
        
        return res;
    }
}
