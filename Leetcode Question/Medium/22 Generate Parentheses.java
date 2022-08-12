/* Leetcode 22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]
 
Constraints:
1 <= n <= 8
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        //Used back tracking method
        //Time: O(n!) ===> The calculation process of this problem conforms to the law of the Cattelan number
        //Space: O(n) ===> divide and conquer idea and back tracking method
        List<String> res = new ArrayList<>();
        if(n == 0){
            return res;
        }
        backtracking(res, "", n, n); //the scale is n, so both of left and right value are n
        return res;
    }
    
    public static void backtracking(List<String> res, String str, int left, int right){
        //If the number of left and right parentheses is not the same, 
        // it is an error condition and should not be added to the result list.
        if(left > right){
            return;
        }
        //when left and right equal 0, means this is a well-formed parentheses combinations
        if(left == 0 && right == 0){
            res.add(str); //So add this combinations into res list
            return;
        }
        
        if(left > 0){ //if left large than 0, backtracking to add a left Parentheses
            backtracking(res, str + "(", left - 1, right);
        }
        
        if(right > 0){  //if right large than 0, backtracking to add a right Parentheses
            backtracking(res, str + ")", left, right - 1);
        }
    }
}

/*CS's understanding of the Cattelan number: 
If there is a problem, its scale is n, and the divide and conquer 
method can be used to solve this problem. If in a constraint, one of the problems can be solved by one method, 
and the other (n-1) problems can also be solved by this method.*/

/*Like, The nth method is solved by this method, And all of (0, n-1), (1, n-2), (2, n-3)...(n-1, 0) can use 
this method solved this way. This means that this problem belongs to the solution rule of the Cattelan number. 
So The time complexity is O(n!)*/
