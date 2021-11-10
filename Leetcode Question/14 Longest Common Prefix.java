/* 14. Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 
Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        
        String curr = strs[0];
        
        // recursive all the string list
        for(int i = 0; i < strs.length; i++){
           curr = longestCommon(curr, strs[i]);
        }
        return curr;
    }
    
    public String longestCommon(String s, String a){
        int i = 0;
        int j = 0;
        //get how many conmmon number is same
        while(i < s.length() && j < a.length() && s.charAt(i) == a.charAt(j)){
            i++;
            j++;
        }
        
        //Substring back to the "String"
        return s.substring(0, i);
    }
}
