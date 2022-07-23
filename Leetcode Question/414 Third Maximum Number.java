/* Leetcode 414. Third Maximum Number
Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.

Example 1:
Input: nums = [3,2,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2.
The third distinct maximum is 1.

Example 2:
Input: nums = [1,2]
Output: 2
Explanation:
The first distinct maximum is 2.
The second distinct maximum is 1.
The third distinct maximum does not exist, so the maximum (2) is returned instead.

Example 3:
Input: nums = [2,2,3,1]
Output: 1
Explanation:
The first distinct maximum is 3.
The second distinct maximum is 2 (both 2's are counted together since they have the same value).
The third distinct maximum is 1.
 

Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
*/

class Solution {
    public int thirdMax(int[] nums) {
        int res = 0;        
        HashSet<Integer> set = new HashSet<>(); //HashSet did not allowed same number, so can use Hashset to remove duplicate numbers
        
        for(int i : nums){ // traverse and add all the number from nums[] into Hashset
            set.add(i); //HashSet did not allowed same number, so can use Hashset to remove duplicate numbers
        }
        
        List<Integer> list = new ArrayList<Integer>(set); // use list to find and get Third Maximum Number through position
        Collections.sort(list); // Sort the list by number
        
        if(list.size() < 3){ // If the third maximum does not exist, return the maximum number.
            res = list.get(list.size() - 1);
        } else{ // return the third distinct maximum number in this array. 
            res = list.get(list.size() - 3);
        }
        
        return res;
    }
}
