/* JackerRank Question Mini-Max Sum

Given five positive integers, find the minimum and maximum values that 
can be calculated by summing exactly four of the five integers. 

Then print the respective minimum and maximum values as a single line of two space-separated long integers.

Example: arr = [1, 3, 5, 7, 9]
Output: 16  24

The minimum sum is  1 + 3 + 5 + 7 = 16 
the maximum sum is  3 + 5 + 7 + 9 = 24 
*/

class Result {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
    // Write your code here
        long[] array = new long[5];
        
        for(int i = 0; i < arr.size(); i++){
            array[i] = arr.get(i); 
        } 
        
        Arrays.sort(array);
        long MaxNumber = 0;
        long MinNumber = 0;
        
        for(int j = 1; j < 5; j++){
            MaxNumber += array[j];
        }
        
        for(int j = 0; j < 4; j++){
            MinNumber += array[j];
        }
        
        System.out.println(MinNumber + " " + MaxNumber);
    }

}
