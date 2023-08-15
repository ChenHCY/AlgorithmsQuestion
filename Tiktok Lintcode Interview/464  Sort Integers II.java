/*Lintcode 464 · Sort Integers II

Given an integer array, sort it in ascending order in place. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example1:

Input: [3, 2, 1, 4, 5], 
Output: [1, 2, 3, 4, 5].

Example2:

Input: [2, 3, 1], 
Output: [1, 2, 3].

*/

public class Solution {
    /**
     * @param a: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] a) {
        // write your code here
        if(a == null || a.length == 0){
            return;
        }

        quickSort(a, 0, a.length - 1);
    }

    //quick sort
    private void quickSort(int[] nums, int start, int end){
        if(start >= end){
            return;
        }

        int left = start;
        int right = end;
        int pivot = nums[(left + right) / 2];
        
        //Quick Sort: 取个基准点，把比pivot小的换到基准点左边  比pivot大的换到基准点右边
        while(left <= right){
            //左右指针往中间遍历
            while(nums[left] < pivot){
                left++;
            }

            while(nums[right] > pivot){
                right--;
            }

            if(left <= right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        //第一次交换完毕之后 继续递归交换
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}
