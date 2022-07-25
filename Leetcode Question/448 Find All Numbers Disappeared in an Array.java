/* Leetcode 448. Find All Numbers Disappeared in an Array

Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.

 
Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

Example 2:
Input: nums = [1,1]
Output: [2]
 

Constraints:
n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
 

Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
*/
//Solution 1: Used HashSet  Run Time: O(n^2)
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashSet<Integer> set =  new HashSet<>(); 
        
        //HashSet did not allowed have same number
        for(int c : nums){ 
            set.add(c); //So use HashSet to remove duplicate numbers
        }
        
        //traverse all the elements[1-n] and check which numbers do not appear in nums
        for(int i = 1; i <= nums.length; i++){
            if(!set.contains(i)){ //check which numbers do not appear in nums
                res.add(i); //add in to ArrayList res
            }
        }
        
        return res;
    }
}

//
