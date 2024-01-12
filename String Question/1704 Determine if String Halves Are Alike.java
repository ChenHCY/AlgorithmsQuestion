/* 1704. Determine if String Halves Are Alike

You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.

Example 1:
Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.

Example 2:
Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
 
Constraints:
2 <= s.length <= 1000
s.length is even.
s consists of uppercase and lowercase letters.
*/

class Solution {
    public boolean halvesAreAlike(String s) {
        int cut = s.length() / 2; // String s is even length;

        String a = s.substring(0, cut); //左闭右开
        String b = s.substring(cut, s.length()); //左闭右开

        return countVowels(a) == countVowels(b) ? true : false;
    }

    public static int countVowels(String str){
        //The vowels list
        int count = 0; //the numbers of vowels
        for(int i = 0; i < str.length(); i++){
            //使用indexof() != -1检查是不是vowels character
            if("AEIOUaeiou".indexOf(str.charAt(i)) != -1){
                count++;
            }
        }
        return count;
    }
}
