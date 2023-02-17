/* Leetcode (Contest 332) 2564 Substring XOR Queries
You are given a binary string s, and a 2D integer array queries where queries[i] = [firsti, secondi].

For the ith query, find the shortest substring of s whose decimal value, val, yields secondi when bitwise XORed with firsti. 
In other words, val ^ firsti == secondi.

The answer to the ith query is the endpoints (0-indexed) of the substring [lefti, righti] or [-1, -1] if no such substring exists. 
If there are multiple answers, choose the one with the minimum lefti.

Return an array ans where ans[i] = [lefti, righti] is the answer to the ith query.

A substring is a contiguous non-empty sequence of characters within a string.


Example 1:

Input: s = "101101", queries = [[0,5],[1,2]]
Output: [[0,2],[2,3]]
Explanation: For the first query the substring in range [0,2] is "101" which has a decimal value of 5, and 5 ^ 0 = 5, 
hence the answer to the first query is [0,2]. In the second query, the substring in range [2,3] is "11", and has a decimal value of 3, 
and 3 ^ 1 = 2. So, [2,3] is returned for the second query. 

Example 2:

Input: s = "0101", queries = [[12,8]]
Output: [[-1,-1]]
Explanation: In this example there is no substring that answers the query, hence [-1,-1] is returned.

Example 3:

Input: s = "1", queries = [[4,5]]
Output: [[0,0]]
Explanation: For this example, the substring in range [0,0] has a decimal value of 1, and 1 ^ 4 = 5. So, the answer is [0,0].
 

Constraints:

1 <= s.length <= 104
s[i] is either '0' or '1'.
1 <= queries.length <= 105
0 <= firsti, secondi <= 109
*/

/*
题意：此题是给一个二进制的String s，和一个十进制数区间组成的queries ==》 queries[i] = [firsti, secondi].

要求根据queries每个区间，查找对应字符串s中形成的二进制数值val，满足 val ^ firsti == secondi.

最后返回这个val在String s的对应区间


解题思路：

1.首先根据XOR的特性： A ^ B = C, 那么我们可以得到 A ^ C = B, B ^ C = A
  ==》 所以我们可以根据first ^ second来查找和得到val的值 
  ==》 val ^ firsti == secondi. ==> firsti ^ secondi. == val

2. 那么我们可以使用哈希表, 来记录每一个String s中能形成的数值，和这个值在字符串S中第一次出现的坐标

3. 通过第一个for-loop进行遍历： 

   情况1：如果当前开头字符为0 且没有储存过, 那么就将0放入到哈希表中
   
   情况2：如果已经储存过0，则跳过 ==》因为统计时, 当前二进制不能有前导0.
 
 4. 然后通过i, j开始遍历和查找字符串形成的数值 ==》 val ^ firsti == secondi. i和j表示形成val的字符串的区间
 
 5. 第二个for-loop： 因为int类型最多也就31位, 所以j <= 31 && i + j <= s.length()
 
 ==》 int number = Integer.valueOf(s.substring(i, i + j), 2); ==> 将一个string区间形成的的二进制数值转化为Integer十进制
 
 ==》 如果出现过了就跳过, 反之将坐标储存进HashMap中
 
 6. 然后遍历queries所有的值 queries[i] = [firsti, secondi], 最后通过哈希表查找每一个queries区间对应的val值
 
 ==》存入到int[][] res （结果） 中

*/

class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        //** Integer.valueOf(s.substring(i, i + j), 2); 
        //==> 将一个string形式的二进制转化为Integer十进制

        //used hashmap to save the val with the string position ith 
        //那么我们可以使用哈希表, 记录每一个value第一次出现的坐标
        HashMap<Integer, int[]> map = new HashMap<>();
        int[][] res = new int[queries.length][2]; // the array result: ans[i] = [lefti, righti]

        //travser all the element from string s ==> 我们尝试以每一个坐标为开头
        for(int i = 0; i < s.length(); i++){
            // 如果当前开头字符为0 且没有储存过, 那么就将0放入到哈希表中
            if(s.charAt(i) == '0'){
                if(!map.containsKey(0)){
                    map.put(0, new int[]{i, i});
                }
                continue; //如果已经储存过0，则跳过 ==》因为统计时, 当前二进制不能有前导0.
            }

            //然后通过i, j开始遍历和查找字符串形成的数值 ==》 val ^ firsti == secondi.
            //因为int类型最多也就31位, 所以j <= 31 && i + j <= s.length()
            for(int j = 1; j <= 31 && i + j <= s.length(); j++){
                int number = Integer.valueOf(s.substring(i, i + j), 2);

                //substring是左闭右开区间，number的值是 i 到 j-1
                if(!map.containsKey(number)){
                    map.put(number, new int[]{i, i + j - 1});
                } else{
                    continue;
                }
            }
        }

        /*  首先A ^ B = C, 那么A ^ C = B, B ^ C = A
            所以我们可以根据first ^ second来查找和得到val的值 
            ==》 val ^ firsti == secondi. ==> firsti ^ secondi. == val
        */

        //然后遍历queries所有的值 queries[i] = [firsti, secondi], 最后通过哈希表查找每一个queries区间对应的val值
        //==》存入到int[][] res （结果） 中
        for(int k = 0; k < queries.length; k++){
            int first = queries[k][0]; //get the frist value
            int second = queries[k][1]; //get the second value

            res[k] = map.getOrDefault(first ^ second, new int[]{-1, -1});
            // if no such substring exists, return [-1, -1];
        }

        return res;
    }
}
