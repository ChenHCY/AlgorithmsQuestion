/* 143 · Sort Colors II
Description
Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

Example1
Input: 
[3,2,2,1,4] 
4
Output: 
[1,2,2,3,4]

Example2
Input: 
[2,1,1,2,2] 
2
Output: 
[1,1,2,2,2]
Challenge
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it O(logk) using extra memory?
*/

public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if(colors == null || colors.length == 0){
            return;
        }
        // 0 - colors.length are the are colors list
        // 1 - k are the how many kinds color we have
        sortMethod(colors, 0, colors.length - 1, 1, k); 
    }

    // used quick sort method (divide conquer method)
    public void sortMethod(int[] colors, int start, int end, int colorFrom, int colorTo){
         if(start >= end || colorFrom == colorTo) {
             return;
         }

         int left = start;
         int right = end;
         int mid = (colorFrom + colorTo) / 2; // find the pivot between 1 with k

         while(left <= right){
             while(left <= right && colors[left] <= mid){
                 left++;
             } 

             while(left <= right && colors[right] > mid){
                 right--;
             }
           
           //move the number less than pivot into left side and the number large than pivot into righ side 
             if(left <= right){
                 int temp = colors[left];
                 colors[left] = colors[right];
                 colors[right] = temp;
                 left++;
                 right--;
             }
         }
      
      //conitue to recursuve left side and right side 
         sortMethod(colors, start, right, colorFrom, mid);
         sortMethod(colors, left, end, mid + 1, colorTo);

    }
}
