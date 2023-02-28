/* Leetcode 76. Minimum Window Substring

Given two strings s and t of lengths m and n respectively, return the minimum window 
substring of s such that every character in t (including duplicates) is included in the window. 
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

/* 思路：

这个题意是找到String S中最短的字符串 可以 包含所有的String t字母 的最短长度

首先用一个HashMap 把 String t的所有字母存起来

然后开始遍历String S ==> 双指针的滑动窗口（同向双指针） ==>  for(int left = 0, right = 0; right < s.length(); right++)

Step 1. 查找右指针的字母是否存在于String t(hashmap containsKey()) ==》 直到找到所有String t的字母

==》每次right+=1，这样可以找到一个窗口能拥有所有String t字母的长度

Step 2：开始优化这个窗口的长度 ==》  while(len <= 0) len代表窗口中存在所有的String t的字母种类数量 
    ==》 len <= 0 表示这个窗口内有所有string t的字母

查找左指针的字母是否存在于String t(hashmap containsKey())

==》如果存在， map中的字母 + 1， len+1

==》如果不存在， 更新窗口的长度 

这样可以找到当前窗口的最小长度。可以包含String t所有字母

Step 3: 在这个for-loop中 是不断重复 Step 1 和 Step 2的，直到右指针遍历完所有String S

===》 最后因为题目要求的是一个字符串，使用substring function

*/

//Time: O(
class Solution {
    public String minWindow(String s, String t) {
        //base case
        if (s.length() < t.length() || t.length() == 0) {
            return "";
        }

        //First hashmap to save all the characters from String t
        HashMap<Character, Integer> map = new HashMap<>();

        //used for loop to save the character from String t
        for(int i = 0; i < t.length(); i++){
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        //the start position area of sliding window that have minimum length
        int start = 0;
       //the length and the res
        int len = map.size();
        int res = Integer.MAX_VALUE;

        //after find the first sliding windows that have all the characters from string t
        //但找到第一个含有所以string t所有字母的滑动窗口之后 ==》开始移动两个指针 去 遍历考虑最短长度的问题
        for(int left = 0, right = 0; right < s.length(); right++){
            //find the first length that sliding windows have all the characters from string t
            char currR = s.charAt(right); // get the character of right pointer
            if(map.containsKey(currR)){ //检查是否有String t的字母
                map.put(currR, map.get(currR) - 1);
                if(map.get(currR) == 0){
                    len -= 1; //找到了第一个能含有所以string t 字母的窗口长度
                }
            }

            while(len <= 0){ //当表示这个窗口内有所有string t的字母时，缩小左边窗口，来找到最小的长度
                char currL = s.charAt(left); // get the character of left pointer
                //如果缩小的左指针对应的字母是存在于String t当中，则加回对应字母的数量
                if(map.containsKey(currL)){ //if string t also have currL character
                    map.put(currL, map.get(currL) + 1); //则加回对应字母的数量
                    if(map.get(currL) >= 1) {
                        len += 1; //则加回对应字母的数量，和存在的字母种类
                    }
                }

                //count the length of window
                //如果缩小的左指针对应的字母不存在于String t中，则计算新的长度
                if(right - left + 1 < res){
                    start = left;
                    res = right - left + 1;
                }
                left++;
            }
        }

        //used the area to get the substring that minimum length
        return res == Integer.MAX_VALUE ? "" : s.substring(start, start + res);
    }
}
