/* Leetcode  287. Find the Duplicate Number

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2

Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
 

Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.
 

Follow up:
How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
*/

//My Solution: Used HashSet to check and find the duplicate number
//Time: O(n)  Space: O(n)
class Solution {
    public int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            if(set.contains(num)){
                return num;
            }
            set.add(num);
        }

        return 0;
    }
}

//The Best time Solution: Time: O(n)  Space: O(1)
//Used a boolean[] array to save and check whether have the duplicate number
class Solution {
    public int findDuplicate(int[] nums) {
       boolean sameNumber[] = new boolean[nums.length];

       for(int i : nums){
           if(sameNumber[i] == true){
               return i;
            } 
            sameNumber[i] = true;
       }

       return -1;
    }
}
