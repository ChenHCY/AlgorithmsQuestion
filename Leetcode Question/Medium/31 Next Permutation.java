/*Leetcode 31. Next Permutation

A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, 
if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation 
of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged 
as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

Example 1:
Input: nums = [1,2,3]
Output: [1,3,2]

Example 2:
Input: nums = [3,2,1]
Output: [1,2,3]

Example 3:
Input: nums = [1,1,5]
Output: [1,5,1]
 
Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 100
*/

//Time: O(n) ==> Optimal solution (just need traverse one time for the numbers[] list)
//Space: O(1)  ==> Did not create any new thing

class Solution {
    public void nextPermutation(int[] nums) {
        //Operation process:
        // 1 2* 7 4 3  1  ==> Find the first number that it is smaller than the next element from nums[] list
        // 1 2* 7 4 3* 1  ==> Find the first number that it is large than firstSamll number from nums[] list
        // 1 2* 7 4 3* 1  ==> Swap the firstSmall number with firstLarge number
        // 1 3 (7 4 2  1) ==> Reverse the request area: [FirstSmall number ~ nums.length - 1]
        // 1 3  1 2 4  7 (Final result)
        
        if(nums == null || nums.length == 0){
            return;
        }
        
        int firstSmall = -1;
        int firstLarge = -1;
        
        // Find the first number that it is smaller than the next element from nums[] list
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] < nums[i + 1]){
                firstSmall = i;
                break; //stop the for-loop
            }
        }
        
        //Special case (pruning):
        //if the nums[] list is descending sequence, so the firstSmall will not change
        //For Example: Input: nums = [3,2,1] => Output: [1,2,3]
        //so just reverse all the list that can get answer
        if(firstSmall == -1){
            reverse(nums, 0, nums.length - 1);
            return; //Stop all of them, and output the result
        }
        
        //Find the first number that it is large than firstSamll number from nums[] list
        for(int j = nums.length - 1; j > firstSmall; j--){
            if(nums[j] > nums[firstSmall]){
                firstLarge = j;
                break; //stop the for-loop
            }
        }
        
        //Swap the firstSmall with firstLarge: 1 2* 7 4 3* 1 => 1 3 (7 4 2 1)
        swap(nums, firstSmall, firstLarge);
        //Reverse the request area 1 3 (7 4 2 1) ==> 1 3 1 2 4 7 (Final Result)
        reverse(nums, firstSmall + 1, nums.length - 1); 
        return;
    }
    
    //Small Function to Reverse the request area list
    public void reverse(int[] nums, int l, int r){
        while(l < r){
            swap(nums, l, r);
            l++;
            r--;
        }
    }
    
    //Small Function to Swap the "firstSmall" number with the "firstLarge" number
    public void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }   
}
