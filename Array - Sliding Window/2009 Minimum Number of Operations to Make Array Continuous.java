/* Leetcode 2009. Minimum Number of Operations to Make Array Continuous

You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

  · All elements in nums are unique.
  · The difference between the maximum element and the minimum element in nums equals nums.length - 1.

For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.

Example 1:

Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.

Example 2:

Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.

Example 3:

Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/
//优化版本 ==》 减少了hashset部分运算
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int uniqueLength = 1; //去掉重复数字后剩余的array长度
        Arrays.sort(nums); //按照升序排列

        //去重，去掉重复的数字
        for (int i = 1; i < n; i++) {
            if(nums[i - 1] != nums[i]){
                nums[uniqueLength] = nums[i];
                uniqueLength++;
            }
        }
        
        int left = 0;
        int maxSave = 0;
        //规则：区域的最大值-最小值不能超过nums.length - 1，我们已经按照升序排列，所以right指针指向的都是最大值
        for(int right = 0; right < uniqueLength; right++){
            while(nums[right] - nums[left] > n - 1){
                left++;
            } 
            maxSave = Math.max(maxSave, right - left + 1);
        }

        return n - maxSave;
    }
}

//基础版本**************************************
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
         //去重，去掉重复的数字
        for (int x : nums) {
            set.add(x);
        }

        //形成一个新数组
        int[] unique = new int[set.size()];
        int i = 0;
        for (int x : set) {
            unique[i++] = x;
        }
        
        int left = 0;
        int len = unique.length;
        int maxSave = 0;
        Arrays.sort(unique); //按照升序排列

        for(int right = 0; right < len; right++){
            //规则：区域的最大值-最小值不能超过nums.length - 1，我们已经按照升序排列，所以right指针指向的都是最大值
            while(unique[right] - unique[left] > n - 1){
                left++;
            }
            maxSave = Math.max(maxSave, right - left + 1);
        }

        return n - maxSave;
    }
}
