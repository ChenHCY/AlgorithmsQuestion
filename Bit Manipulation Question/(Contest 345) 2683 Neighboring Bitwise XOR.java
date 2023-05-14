/* Leetcode (Contest 345) 2683 Neighboring Bitwise XOR
A 0-indexed array derived with length n is derived by computing the bitwise XOR (⊕) of adjacent values in a binary array original of length n.

Specifically, for each index i in the range [0, n - 1]:

If i = n - 1, then derived[i] = original[i] ⊕ original[0].
Otherwise, derived[i] = original[i] ⊕ original[i + 1].
Given an array derived, your task is to determine whether there exists a valid binary array original that could have formed derived.

Return true if such an array exists or false otherwise.

A binary array is an array containing only 0's and 1's
 
Example 1:

Input: derived = [1,1,0]
Output: true
Explanation: A valid original array that gives derived is [0,1,0].
derived[0] = original[0] ⊕ original[1] = 0 ⊕ 1 = 1 
derived[1] = original[1] ⊕ original[2] = 1 ⊕ 0 = 1
derived[2] = original[2] ⊕ original[0] = 0 ⊕ 0 = 0

Example 2:

Input: derived = [1,1]
Output: true
Explanation: A valid original array that gives derived is [0,1].
derived[0] = original[0] ⊕ original[1] = 1
derived[1] = original[1] ⊕ original[0] = 1
Example 3:

Input: derived = [1,0]
Output: false
Explanation: There is no valid original array that gives derived.
 

Constraints:

n == derived.length
1 <= n <= 10^5
The values in derived are either 0's or 1's
*/

//==> Bitwise XOR(^)： 二进制运算符，在coding中用"^"表示。返回输入值的异位。
//==> 一样数字的返回 '0' / 不一样的数字返回 '1'

//Solution 1: 首先通过相邻位置的异运算XOR，根据derived[]生成对应的original[]
//然后在验证这个original[] XOR异运算之后 得到的derived是否一样 ==》一样 return true / 不一样 return false
//Time: O(n) Space: O(n)
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int[] original = new int[n];
        
        //首先通过相邻位置的异运算XOR，根据derived[]生成对应的original[]
        for(int i = 0; i < n - 1; i++){
            original[i + 1] = original[i] ^ derived[i];
        }
        
        //然后在验证这个original[] XOR异运算之后 得到的derived是否一样
        for(int i = 0; i < n; i++){
            int next = (i + 1) % n;
            if((original[i]^original[next]) != derived[i]){
                return false;
            }
        }
        
        return true;
    }
}


//Solution 2: 
//首先假设 original[] = [a,b,c,d,e], 所以t derived[] = [a ^ b, b ^ c, c ^ d, d ^ e, e ^ a]
//因为 Bitwise XOR(^)： 二进制运算符,  一样数字的返回 '0' / 不一样的数字返回 '1'
//所以对于 derived[]  全部数字进行XOR异运算， a ^ b ^ b ^ c ^ c ^ d ^ d ^ e ^ e ^ a = 0
// ==》 所以如果我们对于 derived[]  全部数字， 使用XOR的进行异运算，总的结果应该为0
//Time: O(1)  Space: O(1)
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int sum = 0;
        for(int derivedNumber : derived){
            sum ^= derivedNumber;
        }

        return sum == 0;
    }
}


