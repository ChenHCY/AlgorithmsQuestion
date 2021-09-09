/* 235 · Prime Factorization
Description
Prime factorize a given integer.

You should sort the factors in ascending order.

Example 1:
Input: 10
Output: [2, 5]

Example 2:
Input: 660
Output: [2, 2, 3, 5, 11]
*/

public class Solution {
    /**
     * @param num: An integer
     * @return: an integer array
     */
    public List<Integer> primeFactorization(int num) {
        // write your code here
        List<Integer> result = new ArrayList<Integer>();

        for(int i = 2; i * i <= num; i++){
            while (num % i == 0){
                num = num / i;
                result.add(i);
            }
        }
        
        if (num != 1) {
            result.add(num);
        }   

        return result;
    }
}
