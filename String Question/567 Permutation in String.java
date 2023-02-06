/* Leetcode 567. Permutation in String

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false
 

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
*/

/*

思路：Bucket Sort[] + Sliding windows 滑动窗口

首先根据题意，我们要判断String s2 是否包含任意排列形式的 String s1 （都是小字母）

所以Bucket Sort就是最好的选择，Bucket Sort可以根据26个字母的顺序来存储字符串中字母的组成

==》可以帮助我们来判断两个string是否组成一样

然后因为要String s2 是否包含任意排列形式的 String s1 ==》 使用一个长度为s1.length的滑动窗口

==> 然后在String s2上进行滑动判断，检查能否找到一个和string s1组成字母一样的区域

//Travser all the element with sliding window in the String s2
for(int i = 1; i <= s2.length() - s1.length(); i++){
    bucket2[s2.charAt(i - 1) - 'a']--;  //删除滑动窗口里面的第一个字母
    bucket2[s2.charAt(i + s1.length() - 1) - 'a']++; //把滑动窗口外面的第一个字母加入到窗口里面

    //然后判断新的的滑动窗口组成是否和String s1的组成一样
    if(checkFrequency(bucket1, bucket2)){
         return true; //if find any one is same, it means s2 contains a permutation of s1
    }
}

===》最后：找到了 return ture,  一个都没有，return false

*/

class Solution {
    //Bucket sort + Sliding window (滑动窗口)
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }

        //用bucket sort 可以来表示字符串中字母的组成（包括每个字母的出现的次数）
        int[] bucket1 = new int[26]; //saved the all the character from s1 into bucket sort
        int[] bucket2 = new int[26]; //saved the all the character from s2 into bucket sort

        //Save all the character frequency from string s1, and first Sliding window from string s2 into bucket sort[]
        for(int i = 0; i < s1.length(); i++){
            bucket1[s1.charAt(i) - 'a']++;  //s1中的字母组成
            bucket2[s2.charAt(i) - 'a']++; //s2中第一个窗口范围中的字母组成
        }

        //check wheher the first sliding windows from string s2 is same with string s1
        if(checkFrequency(bucket1, bucket2)){
            return true;
        }

        //Travser all the element with sliding window in the String s2
        for(int i = 1; i <= s2.length() - s1.length(); i++){
            bucket2[s2.charAt(i - 1) - 'a']--;  //删除滑动窗口里面的第一个字母
            bucket2[s2.charAt(i + s1.length() - 1) - 'a']++; //把滑动窗口外面的第一个字母加入到窗口里面

            //然后判断新的的滑动窗口组成是否和String s1的组成一样
            if(checkFrequency(bucket1, bucket2)){
                return true; //if find any one is same, it means s2 contains a permutation of s1
            }
        }
        //if there is did not have any slidng windows character is same with string s1
        //==> it means false
        return false;
    }

    //boolean function to check whether two range all characters has same frequency 
    //用bucket sort来表示字符串中字母的组成，所以这个子程序是来检查这两个字符串是否在不同的排列组合下都是一样的
    public static boolean checkFrequency(int[] bucket1, int[] bucket2){
        for(int i = 0; i < 26; i++){
            if(bucket1[i] != bucket2[i]){ //if find one character frequency is difference, return false
                return false;
            }
        }
        //if all the characters frequency is same, return true
        return true;
    }
}
