/* 179. Largest Number

Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"
 
Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 109
*/

// 题意：重新排列nums的顺序，字符串形式组成一个最大的整数
// 思路: array自定义排序：对于nums[]进行自定义排序，如果nums[]中任意两个数a, b 组成的 ba < ab, 那a应该放在b前面
// ==> 注意要删除前导0
class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] str = new String[n];
        
        //1. 把nums[]改成string[]形式
        for(int i = 0; i < n; i++){
            str[i] = nums[i] + "";
        }

        //2. arrays.sort的自定义排序
        Arrays.sort(str, (a, b) -> {
            String ab = a + b;
            String ba = b + a;
            
            // 比较nums[]中任意两个数a, b 组成的 ba ab
            // 1. 如果 ba < ab, b的字典序比a小，则输出为负值 -1，不用交换, a应该排在b前面
            // 2. 如果 ba > ab, b的字典序比a大，则输出为正值 1，b应该排在a前面
            // 正值表示前面的字符串字典序较大，负值表示后面的字符串字典序较大
            return ba.compareTo(ab);
        });

        //3. 然后把得到的string[] 组成一个总的字符串
        StringBuilder sb = new StringBuilder();
        for(String s : str){
            sb.append(s);
        }

        //4. 消除前导0
        int start = 0;
        while(start < sb.length() - 1 && sb.charAt(start) == '0'){
            start++;
        }

        return sb.substring(start);
    }
}
