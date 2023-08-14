/*Lintcode 484 · Swap Two Integers in Array

Given an array and two indexes, swap the integers on the two indices.

Example 1:

Input: `[1,2,3,4]` and index1 = `2`, index2 = `3`
Output:The array will change to `[1,2,4,3]` after swapping. You don't need return anything, just swap the integers in-place.
Explanation: You don't need return anything, just swap the integers in-place.

Example 2:

Input: `[1,2,2,2]` and index1 = `0`, index2 = `3`
Output:The array will change to `[2,2,2,1]` after swapping. You don't need return anything, just swap the integers in-place.
Explanation: You don't need return anything, just swap the integers in-place.
*/

public class Solution {
    /**
     * @param a: An integer array
     * @param index1: the first index
     * @param index2: the second index
     * @return: nothing
     */
    public void swapIntegers(int[] a, int index1, int index2) {
        // write your code here
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
}
