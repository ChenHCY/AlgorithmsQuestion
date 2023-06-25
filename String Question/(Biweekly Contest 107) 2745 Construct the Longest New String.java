/*Leetcode (Biweekly Contest 107) 2745. Construct the Longest New String
You are given three integers x, y, and z.

You have x strings equal to "AA", y strings equal to "BB", and z strings equal to "AB". 

You want to choose some (possibly all or none) of these strings and concactenate them in some order to form a new string. This new string must not contain "AAA" or "BBB" as a substring.

Return the maximum possible length of the new string.

A substring is a contiguous non-empty sequence of characters within a string.

Example 1:

Input: x = 2, y = 5, z = 1
Output: 12
Explanation: We can concactenate the strings "BB", "AA", "BB", "AA", "BB", and "AB" in that order. Then, our new string is "BBAABBAABBAB". 
That string has length 12, and we can show that it is impossible to construct a string of longer length.

Example 2:

Input: x = 3, y = 2, z = 2
Output: 14
Explanation: We can concactenate the strings "AB", "AB", "AA", "BB", "AA", "BB", and "AA" in that order. Then, our new string is "ABABAABBAABBAA". 
That string has length 14, and we can show that it is impossible to construct a string of longer length.
 

Constraints:

1 <= x, y, z <= 50
*/
//Time: O(1)  Space: O(1）
class Solution {
    //能组成的最大长度字符串，但不能出现"AAA" "BBB"
    public int longestString(int x, int y, int z) {
        int sum = 0;
        // 无论任何情况, 所有"AB"都能被使用

        //如果"AA"的数量 X > "BB"的数量 Y
        if(x > y){
            //表示我们可以使用，全部 Y数量的"BB" 和 （Y + 1）个"AA" 进行交叉排列
            sum += (y * 2);
            sum += 1;
            sum += z;
        } 
        //如果"AA"的数量 X < "BB"的数量 Y
        else if (x < y){
            //表示我们可以使用，全部 X数量的"AA" 和 （X + 1）个"BB" 进行交叉排列
            sum += (x * 2);
            sum += 1;
            sum += z;
        }
        //如果"AA"的数量 X == "BB"的数量 Y
        else{
            //表示我们可以使用，全部 X数量的"AA" 和 全部Y数量的"BB" 进行交叉排列
            sum += (x * 2);
            sum += z;
        }

        return sum * 2; //每个小字符串的长度为2
    }
}
