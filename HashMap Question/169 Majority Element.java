/* Leetcode 169. Majority Element

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. 
You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
 
Constraints:
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
 
Follow-up: Could you solve the problem in linear time and in O(1) space?
*/

//Solution 1: Used arrays.sort() function ==> return the number value in the ⌊n / 2⌋ position
//Time: O(logn)  Space: O(1)
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length / 2;
        Arrays.sort(nums);
        return nums[n];
    }
}

//Solution 2: Used HashMap to save the number element occur times And find the maxtimes value with number
//Time: O(n^2)  Space: O(n)
class Solution {
    public int majorityElement(int[] nums) {
        //map<key, value> 
        //key is the number for nums[] array || value is the times of every number
        HashMap<Integer, Integer> map = new HashMap<>();
        int number = 0;
        int maxTimes = 0;
        
        //save every number and the occur times into HashMap
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        for(int key : map.keySet()){
            //if the times more than perivous times
            if(map.get(key) > maxTimes){
                maxTimes = map.get(key); //save new max times
                number = key; //save the major elment number
            }
        }
        
        return number;
    }
}
