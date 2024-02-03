/* 1291. Sequential Digits

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:

Input: low = 100, high = 300
Output: [123,234]

Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:
10 <= low <= high <= 10^9
*/

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();

        //travser all the number 1 - 9, then count as number
        for(int i = 1; i <= 9; i++){
            //来组成一个连续的数字
            int num = i;
            int nextDight = i + 1;

            // 单个dight不超过9
            while(num <= high && nextDight <= 9){
                num = num * 10 + nextDight;
                if(low <= num && num <= high){
                    res.add(num);
                }
                nextDight++;
            }
        }

        Collections.sort(res);
        return res;
    }
}
