/*Tiktok OA 6 Question 3   53. Maximum Subarray

Given an integer array nums, find the subarray with the largest sum, and return its sum.
*/
class Result {

    /*
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static long maximumSum(List<Integer> arr) {
        long currSum = arr.get(0);
        long maxSum = arr.get(0);
        
        //travser all the element from arr list
        for(int i = 1; i < arr.size(); i++){
            //compare the sum of every subarray
           currSum = Math.max(arr.get(i), arr.get(i) + currSum);
           maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }

}
