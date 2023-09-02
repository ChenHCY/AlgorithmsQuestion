/*Leetcode (Biweekly Contest 112)  2839. Check if Strings Can be Made Equal With Operations I

You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.

You can apply the following operation on any of the two strings any number of times:

      · Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.

Return true if you can make the strings s1 and s2 equal, and false otherwise.

Example 1:

Input: s1 = "abcd", s2 = "cdab"
Output: true
Explanation: We can do the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbad".
- Choose the indices i = 1, j = 3. The resulting string is s1 = "cdab" = s2.

Example 2:

Input: s1 = "abcd", s2 = "dacb"
Output: false
Explanation: It is not possible to make the two strings equal.
 

Constraints:
s1.length == s2.length == 4
s1 and s2 consist only of lowercase English letters.
*/

//Solution 1: 使用string charAt() 比较各种情况
//time: O(1)   Space: O(1)
class Solution {
    //因为 string s1 和 s2 的长度都是4个字母
    //然后交换条件的 i j ==> j - i = 2 ==> 中间距离为 2
    public boolean canBeEqual(String s1, String s2) {
        //因为i和j的距离必须等于2，所以只有两种情况
        //第一种：i = 0  j = 2 => 检查index = 0 和 2 的字母情况，如果交换之后不一样 输出false
        if(s1.charAt(0) != s2.charAt(0) && s1.charAt(2) != s2.charAt(2)){
            if(s1.charAt(0) != s2.charAt(2) || s1.charAt(2) != s2.charAt(0)){
                return false;
            }
        } else if(s1.charAt(0) == s2.charAt(0) && s1.charAt(2) != s2.charAt(2)){
            return false; //如果index 0字母相等  index 2字母不相等 =》 交换之后 一定不一样
        } else if(s1.charAt(0) != s2.charAt(0) && s1.charAt(2) == s2.charAt(2)){
            return false; //如果index 0字母不相等  index 2字母相等 =》 交换之后 一定不一样
        }

        //第二种： i = 1  j = 3 => 检查index = 1 和 3 的字母情况，如果交换之后不一样 输出false
        if (s1.charAt(1) != s2.charAt(1) && s1.charAt(3) != s2.charAt(3)){
            if(s1.charAt(1) != s2.charAt(3) || s1.charAt(3) != s2.charAt(1)){
                return false;
            }
        } else if(s1.charAt(1) == s2.charAt(1) && s1.charAt(3) != s2.charAt(3)){
            return false; //如果index 1字母相等  index 3字母不相等 =》 交换之后 一定不一样
        } else if(s1.charAt(1) != s2.charAt(1) && s1.charAt(3) == s2.charAt(3)){
            return false; //如果index 1字母不相等  index 3字母相等 =》 交换之后 一定不一样
        }

        return true;
    }
}

//Solution 2: for-loop 遍历 
//time: O(n)  space:O(n) ==> n is the length of string s1
class Solution {
    public boolean canBeEqual(String s1, String s2) {
       char[] ch = s1.toCharArray(); //得到s1的char[] array

       if(s1.equals(s2)){
           return true;
       }

       //因为 string s1 和 s2 的长度都是4个字母
       //然后交换条件的 i j ==> j - i = 2 ==> 中间距离为 2, 所以只需要检查 index 0 和 1
       for(int i = 0; i < 2; i++){
           //检查index i 和 index i+2 对应的字母是否一样
           //如果一样进行交换
           if(ch[i] == s2.charAt(i + 2)){
               char temp = ch[i];
               ch[i] = ch[i + 2];
               ch[i + 2] = temp;
           }
            //如果交换之后，新的string s1 和 s2一样  输出true
           if(String.valueOf(ch).equals(s2)){
               return true;
           }
       }

       return false;
    }
}
