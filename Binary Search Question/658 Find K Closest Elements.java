/* Leetcode 658. Find K Closest Elements && Lintcode 460 · Find K Closest Elements
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 
Example 1:
Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]

Example 2:
Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]

Constraints:
1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int[] closet = new int[k];
        int left = 0;
        int right = arr.length - 1;
        
        //find the two closed element of target in the arr[] list
        //must + 1, Avoid falling into an infinite loop when there are only two numbers left
        while(left + 1 < right){ 
            int mid = left + (right - left) / 2;
            if(arr[mid] >= x){
                right = mid;
            } else{
                left = mid;
            }
        }
        
        int start = left;
        int end = right;
        
        //Compare the value of two pointer, and add into closet[] array
        for(int i = 0; i < k; i++){
            if(start < 0){ // if the start-pointer was out of bounds
                closet[i] = arr[end++];
            } else if(end >= arr.length){ // if the end-pointer was out of bounds
                closet[i] = arr[start--];
            } else{ //if both of the two pointer are in the bounds
                if(x - arr[start] <= arr[end] - x){
                    closet[i] = arr[start--]; //compare the value, add the small one into closet[] array
                } else{
                    closet[i] = arr[end++]; //compare the value, add the small one into closet[] array
                }
            }
        }
        
        Arrays.sort(closet); //The result should also be sorted in ascending order.
        
        //change the interger array to be a Arraylist
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < closet.length; i++){
            res.add(closet[i]);
        }
        
        return res;
    }
}
