/*Lintcode 463 · Sort Integers

Given an integer array, sort it in ascending order. Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.


Example 1:
	Input:  [3, 2, 1, 4, 5]
	Output: [1, 2, 3, 4, 5]
	
	Explanation: 
	return the sorted array.

Example 2:
	Input:  [1, 1, 2, 1, 1]
	Output: [1, 1, 1, 1, 2]
	
	Explanation: 
	return the sorted array.

*/

//Solution 1: Quick Sort: 快速排序，左右指针向中间遍历，取中间值为基准，小于基准的放入左边，大于基准的放入右边
public class Solution {
    /**
     * @param a: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] a) {
        // write your code here
        if(a == null || a.length == 0){
            return;
        }

        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(int[] nums, int start, int end){
        if(start >= end){
            return;
        }

        int left = start;
        int right = end;
        int piovt = nums[(left + right) / 2]; //取个中间基点

        while(left <= right){
            //左右指针向中间遍历，把小于piovt的数值放到基点左边，大于pivot的数值放入基点右边
            //如果存在不满足的数值，左右指针进行交换
            while(nums[left] < piovt){
                left++;
            }

            while(nums[right] > piovt){
                right--;
            }

            //如果存在不满足的数值，交换左右指针对应的值
            if(left <= right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        //继续递归，直到完成所有排序
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}


//Solution 2: Bubble Sort 冒泡排序，从左往右 遍历所有数字，如果当前数字大于下一个数字，则进行交换swap
//==> 保证数值大的数字 一定在后面
public class Solution {
    /**
     * @param a: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] a) {
        // write your code here
        int n = a.length;

        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(a[i] > a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
