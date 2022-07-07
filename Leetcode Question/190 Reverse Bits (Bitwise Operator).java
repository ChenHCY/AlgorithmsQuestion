/* Leetcode 190. Reverse Bits  NEED use Bitwise Operator

Reverse bits of a given 32 bits unsigned integer.

Note:
Note that in some languages, such as Java, there is no unsigned integer type. 
In this case, both input and output will be given as a signed integer type. 
They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.

In Java, the compiler represents the signed integers using 2's complement notation. 
Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 

Example 1:
Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, 
so return 964176192 which its binary representation is 00111001011110000010100101000000.

Example 2:
Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, 
so return 3221225471 which its binary representation is 10111111111111111111111111111111.
*/

// First Solution: 
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int times = 32;
        int res = 0;
        List<Integer> bits = new ArrayList<>();
        
        while(times > 0){
            int temp = n & 1;
            bits.add(temp);
            n = n >> 1;
            times--;
        }
        
        for(int i = 0; i < bits.size(); i++){
            res = res << 1;
            res = res | bits.get(i);
        }
        
        return res;
    }
}

// Second Solution:
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int times = 32;
        int res = 0;
       
        for(int i = 0; i < times; i++){
            res = res << 1;
            res |= (n & 1);
            n = n >> 1;
        }
        return res;
    }
}
