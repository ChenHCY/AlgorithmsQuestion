/*Leetcode (Biweekly Contest 112) 2839 Check if Strings Can be Made Equal With Operations II

You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.

You can apply the following operation on any of the two strings any number of times:

    · Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at those indices in the string.

Return true if you can make the strings s1 and s2 equal, and false otherwise.

Example 1:

Input: s1 = "abcdba", s2 = "cabdab"
Output: true
Explanation: We can apply the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.

Example 2:

Input: s1 = "abe", s2 = "bea"
Output: false
Explanation: It is not possible to make the two strings equal.
 

Constraints:

n == s1.length == s2.length
1 <= n <= 105
s1 and s2 consist only of lowercase English letters.
*/

class Solution {
    //String s1 和 s2 的长度是 n 没有限制的
    //可以交换 String s1 i 和 j的位置（j-i = even  之间差距是偶数） 看S1 能否 和 s2一样
    //所以我们 bucket sort 统计 index odd 和 even 位置上的字母组成，
    // ==》如果字母组成一样，则表示可以通过交换 使得 String s1 和 s2 一样
  
    public boolean checkStrings(String s1, String s2) {
        //使用bucket sort 统计 index odd 和 even 位置上的字母组成
        int[] odd = new int[26];
        int[] even = new int[26];

        for(int i = 0; i < s1.length(); i++){
            //even index
            if(i % 2 == 0){
                even[s1.charAt(i) - 'a']++;
                even[s2.charAt(i) - 'a']--;
            } else{ //odd index
                odd[s1.charAt(i) - 'a']++;
                odd[s2.charAt(i) - 'a']--;
            }
        }

        //然后检查里面字母的组成是否一样，如果一样 =》则表示可以通过交换 使得 String s1 和 s2 一样
        for(int j = 0; j < odd.length; j++){
            if(odd[j] != 0 || even[j] != 0){
                return false;
            }
        }

        return true;
    }
}
