/*Leetcode 131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome

. Return all possible palindrome partitioning of s.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]
 

Constraints:
1 <= s.length <= 16
s contains only lowercase English letters.
*/
//Time complexity: O(N∗2^N)
//Space complexity: O(N)
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        backtracking(0, s, res, list);
        return res;
    }

    //backtracking function：从左往右遍历，判断子字符串是不是回文，然后进行划分
    public static void backtracking(int index, String s, List<List<String>> res, List<String> list){
        //exit case: 到达字符串的最后, 把得到所有的list加入到最后的结果
        if(index == s.length()){
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i < s.length(); i++){
            if(isPalidrome(s, index, i)){
                list.add(s.substring(index, i + 1)); //如果这个子字符串是回文，加入到list中
                backtracking(i + 1, s, res, list); //
                list.remove(list.size() - 1); //回溯删除，进行重新划分
            }
        }
    }

    //function: 检查这个子字符串是不是回文
    public static boolean isPalidrome(String s, int l, int r){
        while(l <= r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
