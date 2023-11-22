/* Leetcode 1980. Find Unique Binary String

Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.

Example 1:

Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.

Example 2:

Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.

Example 3:

Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 

Constraints:
n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.
*/

    // 就是在n个长度为n的字符串里找到一个字符串与其他的都不相同
    // 所以我们只需要遍历每一个index对应的字符串中的第index位字符，改变其值 
    // ==> 这样就能保证新字符串和nums中的所有字符串都存在不一样

// Time: O(n)   
// Space Complexity: O(n) ==》 The StringBuilder is used to construct the result binary string.
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // 通过 StringBuilder 来 组成 字符串
        StringBuilder sb = new StringBuilder();
        int n = nums[0].length();

        // 因为二进制的 都是 '0' 和 '1' 来组成的
        for(int i = 0; i < n; i++){
            if(nums[i].charAt(i) == '0'){
                sb.append("1");
            } else{
                sb.append("0");
            }
        }

        return sb.toString();
    }
}
