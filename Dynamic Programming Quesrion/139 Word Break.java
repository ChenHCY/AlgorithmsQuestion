/* Leetcode 139. Word Break
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/
//Thought: Used HashSet to save all the string elements from wordDict
//        Then traversal all the character elements in s whether contained in HashSet, 
//        Used Dynamic Programming Memorize hold the boolean value in every pointer

//Time: O(n^3) ===> Used two for-loop and substring computation at each iteration
//Space: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        //used DP method
        //Dp State: use dp[i] to represent the boolean value in every character element
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict); // use hashset save the string from wordDict
        
        //Dp Initialize: 
        dp[0] = true;
        
        //Dp function: 
        //travser all the string element from string s and compare with hashset
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        
        //Dp return:
        return dp[s.length()]; //return finally one dp[]
    }
}
