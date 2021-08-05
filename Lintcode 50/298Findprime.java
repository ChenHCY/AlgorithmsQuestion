/* 298 · Find prime
Description
Output all prime numbers within n.

We promise that n is an integer within 100.

Example
Example 1:

Input：5
Output：[2, 3, 5]
*/

public class Solution {
    /**
     * @param n: an integer
     * @return: return all prime numbers within n.
     */
    public List<Integer> prime(int n) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        for(int i = 2; i < n+1; i++){
            if(checkNumber(i)){
                result.add(i);
            }
        }
        return result;
    }

    private boolean checkNumber(int n){
        for(int j = 2; j < n; j++){
            if(n % j == 0){
                return false;
            }
        }
        return true;
    }
}
