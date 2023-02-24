/*Leetcode 53 最大子数组的和*/

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int max = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            if(sum < 0){
                sum = 0;
            }
            
            sum += nums[i];
            if(sum > max){
                max = sum;
            }
        }
            
        return max;
    }
}
