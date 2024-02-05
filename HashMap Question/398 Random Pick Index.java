/* 398. Random Pick Index

Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

  · Solution(int[] nums) Initializes the object with the array nums.
  · int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 
Example 1:
Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 
Constraints:
1 <= nums.length <= 2 * 10^4
-231 <= nums[i] <= 2^31 - 1
target is an integer from nums.
At most 104 calls will be made to pick.
*/
//Time: Solution: O(n) pick: O(1)，其中 n 是 nums 的长度。
//Space: O(n) 开设了一个hashmap储存每个数字的index
class Solution {
    HashMap<Integer, List<Integer>> map;
    Random random; //随机一个数字

    // Solution(int[] nums) Initializes the object with the array nums
    // 使用一个HashMap储存每个元素和其出现的index
    public Solution(int[] nums) {
        map = new HashMap<>();
        random = new Random(); 

        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new ArrayList<Integer>());
            }
            map.get(nums[i]).add(i); //把出现位置加入到对应的num列表中
        }
    }

    // int pick(int target) Picks a random index i from nums where nums[i] == target
    // 如果有多个target值的数字，按照平等概率返回任意一个
    public int pick(int target) {
        List<Integer> indexList = map.get(target); //首先提取target出现的所有位置
        //每个index位置的概率一样，所以在list的范围内，random生成一个数字
        return indexList.get(random.nextInt(indexList.size())); 
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
