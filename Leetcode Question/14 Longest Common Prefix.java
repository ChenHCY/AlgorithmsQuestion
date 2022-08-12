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
        if(strs.length == 0){
            return "";
        }
        
        String curr = strs[0];
        
        //traverse all the string element from strs[] list
        for(int i = 0; i < strs.length; i++){
            curr = helper(curr, strs[i]); //check every two strings element
        }
        
        return curr;
    }
    
    //Used to find the longest common prefix in the betweed two strings
    public static String helper(String a, String b){
        int i = 0;
        int j = 0;
        
        while(i < a.length() && j < b.length() && a.charAt(i) == b.charAt(j)){
            i++;
            j++;
        }
    
        return a.substring(0, i); // subString is used to create smaller strings from the bigger one. 
    }
}
