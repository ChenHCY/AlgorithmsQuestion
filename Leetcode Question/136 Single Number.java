/*Leetcode 136. Single Number
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1
*/

// My solution: use the hashset. HashSet did not allowed same number in the set.
// Time complexity：O(n)         Space complexity: O(n)
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        //HashSet did not allowed same number save twice 
        for(int num : nums){ //check every number for nums list is same or not
            if(!set.contains(num)){
                set.add(num);
            } else{
                set.remove(num);
            }
        } 
        return (int)set.iterator().next(); // return all number fro HashSet      
    }
}

// Best Solution: Used Bitsie Operator in Java ******  Bistwise XOR (^=) => it can check whether have same number (Twice) in the number list
// Time complexity：O(n)         Space complexity: O(1)
class Solution {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }
}

