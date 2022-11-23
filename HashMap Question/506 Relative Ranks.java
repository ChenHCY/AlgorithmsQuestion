/*Leetcode 506. Relative Ranks
You are given an integer array score of size n, where score[i] is the score of 
the ith athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has 
the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:

The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.

 
Example 1:
Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].

Example 2:
Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

 
Constraints:
n == score.length
1 <= n <= 104
0 <= score[i] <= 106
All the values in score are unique.
*/

class Solution {
    public String[] findRelativeRanks(int[] score) {
      
      //used hashmap with a for-loop to save the element number position
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < score.length; i++){
            map.put(score[i], i);
        }
        
        String[] res = new String[score.length];
        
      //sort the score array
        Arrays.sort(score); /*3 4 8 9 10*/
        
      //travser all the element from score array
        for(int i = score.length - 1; i >= 0; i--){
          //map the element value with the element position 
            int position = map.get(score[i]);
            
            if(i == score.length - 1){
                res[position] = "Gold Medal";
            } else if(i == score.length - 2){
                res[position] = "Silver Medal";
            } else if(i == score.length - 3){
                res[position] = "Bronze Medal";
            } else{
                res[position] = score.length - i + "";
            }
        }
        
        return res;
    }
}
