/* HackerRank Question: Plus Minus

Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero. 
Print the decimal value of each fraction on a new line with  places after the decimal.

Note: This challenge introduces precision problems. 
The test cases are scaled to six decimal places, though answers with absolute error of up to  are acceptable.

Example arr = [1, 1, 0, -1, -1]
There are  elements, two positive, two negative and one zero. Their ratios are 2/5 = 0.4000,  2/5 = 0.4000, and 1/5 = 0.2000. Results are printed as:
0.400000
0.400000
0.200000

Function Description
Complete the plusMinus function in the editor below. plusMinus has the following parameter(s): int arr[n]: an array of integers

Print
Print the ratios of positive, negative and zero values in the array. 
Each value should be printed on a separate line with  digits after the decimal. The function should not return a value.

Input Format
The first line contains an integer, , the size of the array.
The second line contains  space-separated integers that describe .

Constraints
Output Format
Print the following  lines, each to  decimals:
proportion of positive values
proportion of negative values
proportion of zeros
Sample Input

STDIN           Function
-----           --------
6               arr[] size n = 6
-4 3 -9 0 4 1   arr = [-4, 3, -9, 0, 4, 1]
Sample Output
0.500000
0.333333
0.166667

Explanation
There are  positive numbers,  negative numbers, and  zero in the array.
The proportions of occurrence are positive: , negative:  and zeros: .
*/

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
    // Write your code here
        double opsitiveNumber = 0;
        double negativeNumber = 0;
        double zeroNumber = 0;
        int arraySize = arr.size();
        
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i) > 0){
                opsitiveNumber += 1;
            }else if (arr.get(i) < 0){
                negativeNumber += 1;
            } else{
                zeroNumber += 1;
            }
        } 
        
        System.out.println(opsitiveNumber / arraySize);
        System.out.println(negativeNumber / arraySize);
        System.out.println(zeroNumber / arraySize);
    }

}
