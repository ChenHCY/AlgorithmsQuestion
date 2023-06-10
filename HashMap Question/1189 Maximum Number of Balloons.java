/* Leetcode 1189. Maximum Number of Balloons

Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

Example 1:

Input: text = "nlaebolko"
Output: 1

Example 2:
Input: text = "loonbalxballpoon"
Output: 2

Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 10^4
text consists of lower case English letters only.
*/

//My Solution: Time: O(n^2)  Space: O(n)
class Solution {
    // "balloon" 
    public int maxNumberOfBalloons(String text) {
        String str = "balloon";
        int res = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();

        //count the frequence of text;
        for(char c : text.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(char c : str.toCharArray()){
            if(!map.containsKey(c)){
                return 0;
            }
            int fre = map.get(c);
            if(c == 'b' || c == 'a' || c == 'n'){
                fre /= 1;
            } 
            if(c == 'l' || c == 'o'){
                fre /= 2;
            }
            res = Math.min(res, fre);
        }
        return res;
    }
}


//The Best Solution: O(n)  Space: O(1)
class Solution {
    // "balloon" 
    public int maxNumberOfBalloons(String text) {
        int res = Integer.MAX_VALUE;
        int b = 0, a = 0, l = 0, o = 0, n = 0;
        for(char c : text.toCharArray()){
            if(c == 'b'){
                b++;
            } else if(c == 'a'){
                a++;
            } else if(c == 'l'){
                l++;
            } else if(c == 'o'){
                o++;
            } else if(c == 'n'){
                n++;
            }
        }
        res = Math.min(res, Math.min(b, Math.min(a, Math.min(l/2, Math.min(o/2, n)))));
        return res;
    }
}
