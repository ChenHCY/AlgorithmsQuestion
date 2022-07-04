/* Leetcode 169. Majority Element

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

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
*/

//Solution 1：The majority element is the element that appears more than ⌊n / 2⌋ times. just find the [n/2] value in the nums
// Time complexity O(nlogn)  Space complexity O(1)
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length / 2; // Beacause The majority element is the element that appears more than ⌊n / 2⌋ times.
        Arrays.sort(nums);
        return nums[n];       
    }
}

//Solution 2：Used the Hashmap method
//Time complexity O(n)   Space complexity O(n)
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int major = 0; // the result major
        int ElementTimes = 0; // the element times
        
        //num is the keys value in this map
        //ElementTimes is the mapped values in this map
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1); // check whether the hashmap has the same number
            //if have it, the ElementTimes + 1
            //if not have it, just return defaultvalue 0
        }
        
        //To find the major element by comparing the ElementTimes
        for(int key : map.keySet()){  //check the hashmap kay-value (num) one by one
            if(map.get(key) > ElementTimes){ // if this element times more than pre times
                ElementTimes = map.get(key); // save the max times 
                major = key; //Get the max times element 
            }
        }
        
        return major; // return the major element
    }
}
