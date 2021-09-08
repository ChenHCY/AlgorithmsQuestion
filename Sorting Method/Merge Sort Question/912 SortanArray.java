/* 912. Sort an Array
Given an array of integers nums, sort the array in ascending order.

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 
Constraints:
1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104
*/

class Solution {
    public int[] sortArray(int[] nums) {
        //if the nums only have 1 or 0 number in the list, just return and output.
        if(nums.length <= 1) 
            return nums;
        
        int mid = nums.length >> 1; // right move 1 is Divide by 2 右移一位相当于除2，右移n位相当于除以2的n次方。
        
        int[] left = Arrays.copyOfRange(nums, 0, mid); // divide 2 samll array from nums list
        int[] right = Arrays.copyOfRange(nums, mid, nums.length); // divide 2 samll array from nums list
        
        return mergeSort(sortArray(left), sortArray(right)); // recursive method to sort every number in the list
    }
    
    public int[] mergeSort(int[] arr1, int[] arr2){
        int i = 0, j = 0, k = 0; // used for position in the arr1[], arr2[], and result[]
        //define a list to get all the list value sort 申请额外的空间存储合并之后的数组
        int[] result = new int[arr1.length + arr2.length];
        
        while (i < arr1.length && j < arr2.length){ // compare every value in the arr1[] and arr2[]
            if(arr1[i] <= arr2[j]){ 
                //if arr1[] value is smaller, save the arr1[i] into the result list
                result[k] = arr1[i];
                k++;
                i++;
            } else {
                //if arr2[] value is smaller, save the arr2[j] into the result list
                result[k] = arr2[j];
                k++;
                j++;
            }
        }
        
        while (i < arr1.length){ 
            //After arr2[] end it, if there is still have number in the arr1 list, save them into result list
            result[k] = arr1[i];
            i++;
            k++;
        }
        
        while (j < arr2.length){
            //After arr1[] end it, if there is still have number in the arr2 list, save them into result list
            result[k] = arr2[j];
            j++;
            k++;
        }
        
        //Finally, return and output the result List 
        return result;
    }
}
