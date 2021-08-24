/* 846 · Multi-keyword Sort
Description
Given n students and their test scores, expressed as (student number, test scores), sort by test scores in descending order, if the test scores are the same, sort the student number in ascending order.

Example1

Input: array = [[2,50],[1,50],[3,100]]
Output: [[3,100],[1,50],[2,50]]

Example2

Input: array = [[2,50],[1,50],[3,50]]
Output: [[1,50],[2,50],[3,50]]
*/

public class Solution {
    /**
     * @param array: the input array
     * @return: the sorted array
     */
    public int[][] multiSort(int[][] array) {
        // Write your code here
        for(int i = 0; i < array.length; i++){
            for(int j = i+1; j < array.length; j++){
                if(!compare(array[i], array[j])){ //if return false, change the 2 students place
                    int[] temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

  //this small function is for Judgment condition
    boolean compare(int[] a, int[] b){
        if(a[1] > b[1]){ // judgment the scores (sort by test scores in descending order)
            return true;
        }
        if(a[1] == b[1] && a[0] < b[0]){ // jugement the number
            return true; 
        } //  if the test scores are the same, sort the student number in ascending order.
        return false;
    }
}
