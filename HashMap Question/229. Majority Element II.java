/* Leetcode 229. Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:

Input: nums = [3,2,3]
Output: [3]

Example 2:

Input: nums = [1]
Output: [1]

Example 3:

Input: nums = [1,2]
Output: [1,2]
 

Constraints:
1 <= nums.length <= 5 * 10^4
-10^9 <= nums[i] <= 10^9
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        //count the freqeunce of every number of nums[]
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int thirdT = n / 3;

        //把每一个num 和 对应的次数 提取出来，找到所有次数大于 n/3 的数字
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int currNum = entry.getKey();
            int currFreq = entry.getValue();

            //check whether curr number's frequence is more than ⌊ n/3 ⌋ times.
            if(currFreq > thirdT){
                res.add(currNum);
            }
        }

        return res;
    }
}
