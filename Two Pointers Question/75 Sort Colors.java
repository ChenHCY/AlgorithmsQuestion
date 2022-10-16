/* Leetcode 75. Sort Colors
Given an array nums with n objects colored red, white, or blue, 
sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]
 
Constraints:
n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.
*/
//Time: O(n)   Space: O(1)

class Solution {
    public void sortColors(int[] nums) {
        /*Used Two Pointer Method
        1. Used First pointer p0 start at 0 position
        2. Used Second Pointer p2 start at nums.length - 1;
        3. Used current pointer travser in the nums[] arry to find the element 0 and 2
        4. Then swap it. 
        a. If nums[curr] = 0 : swap currth and p0th elements and move both pointers to the right.
        b. If nums[curr] = 2 : swap currth and p2th elements. Move pointer p2 to the left.
        c. If nums[curr] = 1 : move pointer curr to the right.
        */
        
        int p0 = 0;
        int p2 = nums.length - 1;
        int cur = 0;
        
        //travser all the elemnet from nums[] array
        while(cur <= p2){ //cur pointer can not move outside p2
            if(nums[cur] == 2){
                swap(nums, cur, p2);
                p2--;
            } else if(nums[cur] == 0){
                swap(nums, cur, p0);
                p0++;
                cur++;
            } else{
                cur++;
            }
        }
    }
    
    //Small function: Swap two number element
    public void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
