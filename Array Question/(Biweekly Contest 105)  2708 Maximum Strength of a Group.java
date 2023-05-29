/* Leetcode (Biweekly Contest 105)  2708. Maximum Strength of a Group

You are given a 0-indexed integer array nums representing the score of students in an exam. The teacher would like to form one non-empty group of students with maximal strength, 

where the strength of a group of students of indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik].

Return the maximum strength of a group the teacher can create.

Example 1:

Input: nums = [3,-1,-5,2,5,-9]
Output: 1350
Explanation: One way to form a group of maximal strength is to group the students at indices [0,2,3,4,5]. Their strength is 3 * (-5) * 2 * 5 * (-9) = 1350, which we can show is optimal.

Example 2:

Input: nums = [-4,-5,-4]
Output: 20
Explanation: Group the students at indices [0, 1] . Then, we’ll have a resulting strength of 20. We cannot achieve greater strength.
 

Constraints:

1 <= nums.length <= 13
-9 <= nums[i] <= 9
*/

//因为要得到这个数组的最大乘积，所以所有正整数都要使用，和使用最大偶数个负数
//Time: O(NlongN)   Space: O(n)
class Solution {
    public long maxStrength(int[] nums) {
        long res = 1;
        int n = nums.length;
        List<Long> negative = new ArrayList<>();
        List<Long> positive = new ArrayList<>();

        //save the negative and positive number into list
        for(int num : nums){
            if(num < 0){
                negative.add((long) num);
            } else if(num > 0){
                positive.add((long) num);
            }
        }
        //剪枝
        if(negative.size() <= 1 && positive.size() == 0){
            //如果只有一个负数。并且没有正数，输出最大值   
            return Arrays.stream(nums).max().getAsInt();
        }

        Collections.sort(negative);
        //开始计算，因为要得到这个数组的最大乘积，所以所有正整数都要使用，和使用最大偶数个负数
        if(negative.size() % 2 == 0){ //如果本身就是偶数个负数
            for(long num : negative){
                res = res * num; //使用全部负数
            }
        } else{
            for(int i = 0; i < negative.size() - 1; i++){ // 如果本身就是奇数个负数
                //System.out.println(res);
                res = res * negative.get(i); // 使用最大偶数个负数
            }
        }

        //所有正整数都要使用
        for(long num : positive){
            res = res * num;
        }

        return res;        
    }
}
