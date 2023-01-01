/* Leetcode 299. Bulls and Cows
You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. 
When your friend makes a guess, you provide a hint with the following info:

1. The number of "bulls", which are digits in the guess that are in the correct position.
2. The number of "cows", which are digits in the guess that are in your secret number but are located in 
the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.

Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. 
Note that both secret and guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
  |
"7810"


Example 2:

Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
  |      or     |
"0111"        "0111"
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 

Constraints:

1 <= secret.length, guess.length <= 1000
secret.length == guess.length
secret and guess consist of digits only.
*/

//Time: O(n + m)  Space: O(n)

class Solution {
    public String getHint(String secret, String guess) {
        int numA = 0; // bulls: correct Digits with correct position
        int numB = 0; //cows: correct Digits with wrong position
        String res = "";
        HashMap<Character, Integer> map = new HashMap<>();
        
         //For-loop to find the how many bulls(numA)
        for(int i = 0; i < secret.length(); i++){
            if(secret.charAt(i) != guess.charAt(i)){
                map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
            } else{ // The digits in the guess that are in the correct position.
                numA += 1;
            }
        }
        
        //For-loop to find the how many cows(numB)
        for(int j = 0; j < guess.length(); j++){
            char g = guess.charAt(j);
            char s = secret.charAt(j);
            if(g != s){
                if(map.containsKey(g)){
                    numB += 1; //correct value are located in the wrong position.
                    map.put(g, map.get(g) - 1); //remove once the find digits occur times
                    //if all the correct value element was found
                    if(map.get(g) == 0){
                        map.remove(g);
                    }
                }
            }
        }

        res = "" + numA + "A" + numB + "B";
        return res;
    }
}
